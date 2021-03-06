package acme.features.manager.workplan;

import java.util.NoSuchElementException;
import java.util.Optional;

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
public class ManagerWorkplanDeleteTaskService implements AbstractDeleteService<Manager, Workplan>  {

	@Autowired
	protected ManagerWorkplanRepository repository;
	
	@Autowired
	protected ManagerTaskRepository taskRepository;
	
	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;

		final int userAcountId = request.getPrincipal().getAccountId();
		final int taskId = request.getModel().getInteger("id");
		
		final Optional<Workplan> workplan = this.repository.findOneWorkplanByIdAndUA(taskId, userAcountId);
		
		return workplan.isPresent();
	}

	@Override
	public void bind(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		//Estos metodos están vacios puesto que no se utilizan. 
		//Para encontrar su definicion previa se puede acudir a un commit anterior
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		//Estos metodos están vacios puesto que no se utilizan. 
		//Para encontrar su definicion previa se puede acudir a un commit anterior
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
		
	}

	@Override
	public void delete(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;

		final int taskId = request.getModel().getInteger("taskId");
		final Task task = this.taskRepository.findTaskById(taskId);
		
		entity.getTask().remove(task);
		task.getWorkplan().remove(entity);
		this.repository.save(entity);
		this.taskRepository.save(task);
	}

}
