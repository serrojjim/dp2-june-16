/*
 * AuthenticatedTaskUpdateService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.manager.task;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.Spam.Spam1;
import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerTaskUpdateService implements AbstractUpdateService<Manager, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskRepository repository;
	
	@Autowired
	private AdministratorSpamRepository	spamRepository;

	// AbstractUpdateService<Authenticated, Task> interface -------------


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		final String rol =request.getPrincipal().getActiveRole().getSimpleName();
		final int userAcountId = request.getPrincipal().getAccountId();
		final int taskId = request.getModel().getInteger("id");
		
		final Task task = this.repository.findOneTaskByIdAndUA(taskId, userAcountId);
		
		return (rol.equals("Manager") && task != null);
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


		request.unbind(entity, model, "title", "executionPeriod.finalDate", "executionPeriod.initialDate","workload","description","url");
	
	}

	@Override
	public Task findOne(final Request<Task> request) {
		assert request != null;

		Task result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findTaskById(id);

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
	public void update(final Request<Task> request, final Task entity) {
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
			this.repository.save(entity);

		}
	}

	
	
	
}
