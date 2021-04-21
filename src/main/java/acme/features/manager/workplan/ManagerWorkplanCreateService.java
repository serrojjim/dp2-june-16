package acme.features.manager.workplan;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.datatypes.ExecutionPeriod;
import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.entities.workplan.Workplan;
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

		request.unbind(entity, model, 
			"title", 
			"executionPeriod.finalDate",
			"executionPeriod.initialDate",
			"isPrivate",
			"task",
			"userAccount");
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
		result.setTask(new ArrayList<Task>());
		
		final int id = request.getPrincipal().getAccountId();
		result.setUserAccount(this.authRepository.findOne(id));
		
		return result;
		
	}
	
	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}
	
	@Override
	public void create(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;
		
//		if (entity.getIsPrivate().booleanValue() ||
//				(!entity.getIsPrivate().booleanValue() &&
//					entity.getTask().stream().noneMatch(x -> x.getIsPrivate().equals(true))))
			this.workplanRepository.save(entity);
		
	}
	
}
