
package acme.features.manager.workplan;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.entities.workplan.Workplan;
import acme.features.manager.task.ManagerTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractDeleteService;

@Service
public class ManagerWorkplanChangePrivacyService implements AbstractDeleteService<Manager, Workplan> {

	@Autowired
	protected ManagerWorkplanRepository	repository;

	@Autowired
	protected ManagerTaskRepository		taskRepository;


	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;

		final String rol = request.getPrincipal().getActiveRole().getSimpleName();
		final int userAcountId = request.getPrincipal().getAccountId();
		final int taskId = request.getModel().getInteger("id");

		final Optional<Workplan> workplan = this.repository.findOneWorkplanByIdAndUA(taskId, userAcountId);

		return (rol.equals("Manager") && workplan.isPresent());
	}

	@Override
	public void bind(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("workload", Workplan.getTotalWorkload(entity));

		request.unbind(entity, model, "title", "executionPeriod.finalDate", "executionPeriod.initialDate", "isPrivate", "task");
	}

	@Override
	public Workplan findOne(final Request<Workplan> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return this.repository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		final Boolean condition1 = entity.getTaskList().stream().filter(Task::getIsPrivate).anyMatch(t -> t.getIsPrivate() && entity.getIsPrivate().equals(true));
		errors.state(request, !condition1, "isPrivate", "Un workplan publico no puede contener tareas privadas"); // Para cambiar de privado a publico no puede tener tareass privadas
		
		if(errors.hasErrors()) {
			request.getModel().setAttribute("workload", Workplan.getTotalWorkload(entity));
			
			request.getModel().setAttribute("isPrivate", entity.getIsPrivate());
			
			final List<Task> myTasks = this.taskRepository.findAllMyTask(request.getPrincipal().getAccountId());
			
			if (entity.getIsPrivate().booleanValue()) {
				final List<Task> l = this.taskRepository.findAllMyTask(request.getPrincipal().getAccountId())
					.stream()
					.filter(x -> !x.getWorkplan().contains(entity))
					.collect(Collectors.toList());
				
				request.getModel().setAttribute("allTasksAvailable", l);
			} else {
				final List<Task> l = this.taskRepository.findAllMyTaskOnlyPublic(request.getPrincipal().getAccountId())
					.stream()
					.filter(x -> !x.getWorkplan().contains(entity))
					.collect(Collectors.toList());
				
				request.getModel().setAttribute("allTasksAvailable", l);
			}
			
			request.getModel().setAttribute("allTasksAlreadySelected", 
				myTasks.stream().filter(x -> x.getWorkplan().contains(entity)).collect(Collectors.toList()));
			
			request.getModel().setAttribute("suggestedExecutionPeriod", entity.getSuggestedExecutionPeriod());
			
			
		}

	}

	@Override
	public void delete(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;

		final Boolean condition1 = entity.getTaskList().stream().filter(Task::getIsPrivate).anyMatch(t -> t.getIsPrivate() && entity.getIsPrivate().equals(true));

		if (!condition1.booleanValue()) {
			entity.setIsPrivate(!entity.getIsPrivate());
			this.repository.save(entity);
		}

	}

}
