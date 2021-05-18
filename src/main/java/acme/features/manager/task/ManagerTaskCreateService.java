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

		request.unbind(entity, model, "title", "executionPeriod.finalDate", "executionPeriod.initialDate","workload","description","url","isPrivate");


		
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
		result.setIsPrivate(false);
		result.setExecutionPeriod(execution);
		result.setUserAccount(userAccount);
		return result;
		
	}
	
	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors)  {
		assert request != null;
		assert entity != null;
		assert errors != null;
		  
		final LocalDateTime initialDate = entity.getExecutionPeriod().getInitialDate();
	    final LocalDateTime finalDate = entity.getExecutionPeriod().getFinalDate();
		final double dur = Duration.between(initialDate, finalDate).toMinutes();
		
		final boolean condition7 = entity.getWorkload()==null || entity.getWorkload().toString().isEmpty() || entity.getWorkload()<0;
		if(condition7) {
			errors.state(request, !(condition7), "workload", "Workload no puede estar vacio ni ser negativo");

		}else {
			final int minutos2 = (int) ((double)entity.getWorkload());
			

			final double minutosC = entity.getWorkload() - minutos2;
			
			final int minutos = (int) (entity.getWorkload() -minutosC);

			
			final double minutos_totales = (minutos*60 +minutosC*100);
			
			if(entity.getWorkload()!=null) {
				final boolean condition5 = minutos_totales>dur;
				final boolean condition6 = minutos_totales<0;
				errors.state(request, !(condition5 || condition6), "workload", "El workload tiene que ser menor que el tiempo de ejecucion y mayor que 0");

			}
		}
	

		final boolean condition1 = !Spam1.isSpam(entity.getTitle(), this.spamRepository.findSpam());
		final boolean condition2 = !Spam1.isSpam(entity.getDescription(), this.spamRepository.findSpam());
		final boolean condition3 = !(initialDate.isBefore(LocalDateTime.now()) && !entity.getExecutionPeriod().getInitialDate().equals(this.taskRepository.findTaskById(entity.getId()).getExecutionPeriod().getInitialDate()));
		final boolean condition4 = !finalDate.isBefore(initialDate);
	
		errors.state(request, condition1, "title", "Una task no puede contener palabras spam en su titulo");
		errors.state(request, condition2, "description", "Una task no puede contener palabras spam en la descripción");
		errors.state(request, condition3, "executionPeriod.initialDate", "Una task no puede empezar antes de hoy");
		errors.state(request, condition4, "executionPeriod.finalDate", "Una task no puede terminar antes de empezar");

	
		
		
	}
	
	@Override
	public void create(final Request<Task> request, final Task entity) {
		assert request != null;
		assert entity != null;

		
		this.taskRepository.save(entity);

		
		
	}
	
	

}
