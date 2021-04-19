package acme.features.authenticated.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.datatypes.ExecutionPeriod;
import acme.entities.task.Task;
import acme.entities.workplan.Workplan;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedTaskCreateService implements AbstractCreateService<Authenticated, Task> {
	
	@Autowired
	protected AuthenticatedTaskRepository taskRepository;
	
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
	}
	
	@Override
	public Task instantiate(final Request<Task> request) {
		assert request != null;
		
		Task result;
		Workplan workplan;
		ExecutionPeriod execution;
		execution = new ExecutionPeriod();
		execution.setInitialDate(new Date(2021,5,10));
		execution.setFinalDate(new Date(2021,5,12));

		workplan = new Workplan();
		workplan.setTitle("Execution prueba");
		workplan.setExecutionPeriod(execution);
		
		result = new Task();
		result.setTitle("Prueba de instanciar1");
		result.setDescription("Prueba instanciar1");
		result.setIsPrivate(false);
		result.setIsFinished(false);
		result.setUrl("http://google.es/");
		result.setExecutionPeriod(execution);
		
		return result;
		
	}
	
	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}
	
	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;
		
//		final Date moment;
		
//		moment = new Date(System.currentTimeMillis() - 1);
//		entity.setMoment(moment);
		this.taskRepository.save(entity);
		
	}


}
