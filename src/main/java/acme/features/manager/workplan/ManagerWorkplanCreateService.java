package acme.features.manager.workplan;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.datatypes.ExecutionPeriod;
import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.entities.workplan.Workplan;
import acme.features.manager.task.ManagerTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.repositories.AuthenticationRepository;
import acme.framework.services.AbstractCreateService;

@Service
public class ManagerWorkplanCreateService implements AbstractCreateService<Manager, Workplan> {
	
	@Autowired
	protected ManagerWorkplanRepository workplanRepository;
	
	@Autowired
	protected AuthenticationRepository authRepository;
	
	@Autowired
	protected ManagerTaskRepository taskRepository;
	
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

		model.setAttribute("workload", entity.getTotalWorkload());
		model.setAttribute("allTasks", this.taskRepository.findAllTask());

		request.unbind(entity, model, 
			"title", 
			"executionPeriod.finalDate",
			"executionPeriod.initialDate",
			"isPrivate",
			"task");
	}
	
	@Override
	public Workplan instantiate(final Request<Workplan> request) {
		assert request != null;
		
		final Workplan result = new Workplan();;
		final ExecutionPeriod execution;

		execution = new ExecutionPeriod();
		execution.setInitialDate(LocalDateTime.now().plusMinutes(5));
		execution.setFinalDate(LocalDateTime.now().plusDays(10));
 
		result.setTitle("New task");
		result.setIsPrivate(false);
		result.setExecutionPeriod(execution);
		result.setTask(new HashSet<Task>());
		
		final int id = request.getPrincipal().getAccountId();
		result.setUserAccount(this.authRepository.findOne(id));
		
		return result;
		
	}
	
	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		entity.getTask().clear();
	}
	
	@Override
	public void create(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;
		
		final Object taskDebug = request.getModel().getAttribute("task");
		System.out.println(taskDebug);
		final Task parsedTask = this.taskRepository.findTaskById(Integer.parseInt(taskDebug.toString()));
		entity.addTask(parsedTask);
		parsedTask.addWorkplan(entity);
		
		System.out.println("Antes del save: " + entity.getTask());
		
		this.workplanRepository.save(entity);
		
	}
	
}
