
package acme.features.manager.workplan;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.Spam.Spam1;
import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.entities.workplan.Workplan;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.features.manager.task.ManagerTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.repositories.AuthenticationRepository;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerWorkplanCreateService implements AbstractCreateService<Manager, Workplan> {

	@Autowired
	protected ManagerWorkplanRepository	workplanRepository;

	@Autowired
	protected AuthenticationRepository	authRepository;

	@Autowired
	protected ManagerTaskRepository		taskRepository;

	@Autowired
	private AdministratorSpamRepository	spamRepository;


	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;

		final String rol = request.getPrincipal().getActiveRole().getSimpleName();
		return rol.equals("Manager");
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

		model.setAttribute("allTasks", this.taskRepository.findAllMyTask(request.getPrincipal().getAccountId()));
		model.setAttribute("suggestedExecutionPeriod", entity.getSuggestedExecutionPeriod());

		request.unbind(entity, model, "title", "executionPeriod.finalDate", "executionPeriod.initialDate", "isPrivate", "task");
	}

	@Override
	public Workplan instantiate(final Request<Workplan> request) {
		assert request != null;

		final Workplan result = new Workplan();

		result.setTitle("New workplan");
		result.setIsPrivate(false);
		result.setTask(new HashSet<Task>());
		result.setExecutionPeriod(result.getSuggestedExecutionPeriod());

		final int id = request.getPrincipal().getAccountId();
		result.setUserAccount(this.authRepository.findOne(id));

		return result;

	}

	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		entity.getTask().clear(); //El framework añade sin motivo alguno el id de la task en forma de string
		final Boolean condition0 = entity.getExecutionPeriod().getInitialDate().isBefore(entity.getExecutionPeriod().getFinalDate());
		final boolean condition1 = entity.getIsPrivate() || !Spam1.isSpam(entity.getTitle(), this.spamRepository.findSpam());

		errors.state(request, condition0, "executionPeriod.initialDate", "manager.workplan.form.error.initialDate");
		errors.state(request, condition0, "executionPeriod.finalDate", "manager.workplan.form.error.finalDate");
		errors.state(request, condition1, "title", "Un workplan no puede contener palabras spam en su titulo");

		if (errors.hasErrors()) {
			request.getModel().setAttribute("allTasks", this.taskRepository.findAllMyTask(request.getPrincipal().getAccountId()));
			request.getModel().setAttribute("suggestedExecutionPeriod", entity.getSuggestedExecutionPeriod());
		}
	}

	@Override
	public void create(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;
		
		this.workplanRepository.save(entity);
	}

}
