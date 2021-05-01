package acme.features.manager.task;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.Spam.Spam1;
import acme.datatypes.ExecutionPeriod;
import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.entities.workplan.Workplan;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerTaskCreateService implements AbstractCreateService<Manager, Task> {
	
	@Autowired
	protected ManagerTaskRepository taskRepository;
	

	@Autowired
	private AdministratorSpamRepository	spamRepository;
	
	
	
	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		request.bind(entity, errors);
	}
	
	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "executionPeriod.finalDate", "executionPeriod.initialDate","workload","description","url","isFinished");
		final String rol =request.getPrincipal().getActiveRole().getSimpleName();


		
	}
	
	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;
		
		Task result;
		final Workplan workplan;
		final ExecutionPeriod execution;
		final int userAcountId = request.getPrincipal().getAccountId();
		final UserAccount userAccount = this.taskRepository.findOneUserAccountById(userAcountId);

		execution = new ExecutionPeriod();
		execution.setInitialDate( LocalDateTime.now().plusMinutes(5));
		execution.setFinalDate( LocalDateTime.now().plusDays(10));
 
		workplan = new Workplan();
		workplan.setTitle("Execution prueba");
		workplan.setExecutionPeriod(execution);
		
		result = new Task();
		result.setTitle("New task");
		result.setDescription("New task");
		result.setIsPrivate(false);
		result.setIsFinished(false);
		result.setUrl("http://google.es/");
		result.setExecutionPeriod(execution);
		result.setUserAccount(userAccount);
		return result;
		
	}
	
	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		final boolean condition1 = !Spam1.isSpam(entity.getTitle(), this.spamRepository.findSpam());
		final boolean condition2 = !Spam1.isSpam(entity.getDescription(), this.spamRepository.findSpam());

		errors.state(request, condition1, "title", "Una task no puede contener palabras spam en su titulo");
		errors.state(request, condition2, "description", "Una task no puede contener palabras spam en la descripción");

		
		
		
		
	}
	
	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		boolean conditionToSave = true;
	

        final LocalDateTime initialDate = entity.getExecutionPeriod().getInitialDate();
        final LocalDateTime finalDate = entity.getExecutionPeriod().getFinalDate();
 
        
		if(initialDate.isBefore(LocalDateTime.now())) {
			conditionToSave = false;
		}
		
		
		if(finalDate.isBefore(initialDate)) {
			conditionToSave = false;
		}
		
		final double dur = Duration.between(initialDate, finalDate).toMinutes()/60;
		
		if(entity.getWorkload()>dur || entity.getWorkload() < 0) {
			conditionToSave = false;
		}
		
		if(conditionToSave) {
			this.taskRepository.save(entity);

		}
		
	}
	
	

}
