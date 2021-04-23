/*
 * AuthenticatedWorkplanUpdateService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.manager.workplan;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.entities.workplan.Workplan;
import acme.features.manager.task.ManagerTaskRepository;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class ManagerWorkplanUpdateService implements AbstractUpdateService<Manager, Workplan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkplanRepository repository;

	@Autowired
	protected ManagerTaskRepository taskRepository;

	// AbstractUpdateService<Authenticated, Workplan> interface -------------


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

		model.setAttribute("workload", entity.getTotalWorkload());
		
		request.unbind(entity, model, 
			"title", 
			"executionPeriod.finalDate",
			"executionPeriod.initialDate",
			"isPrivate",
			"task");
	}

	@Override
	public Workplan findOne(final Request<Workplan> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		final Workplan result = this.repository.findById(id).get();
		return result;
	}

	@Override
	public void validate(final Request<Workplan> request, final Workplan entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Object taskDebug = request.getModel().getAttribute("task");
		System.out.println(taskDebug);
		final Task parsedTask = this.taskRepository.findTaskById(Integer.parseInt(taskDebug.toString()));
		entity.getTask().clear();
		final Set<Task> set = new HashSet<>();
		set.add(parsedTask);
		entity.setTask(set);
		
		final Set<Workplan> set2 = parsedTask.getWorkplan();
		set2.add(entity);
		parsedTask.setWorkplan(set2);
		
		System.out.println(entity.getTask());
		final boolean errorCondition1 = !entity.getIsPrivate().booleanValue();
		final boolean errorCondition2 =	entity.getTask().stream().anyMatch(x -> x.getIsPrivate().equals(true));
		
		errors.state(request, !(errorCondition1 && errorCondition2), "isPrivate", "Un workplan publico no puede contener tareas privadas");
	}

	@Override
	public void update(final Request<Workplan> request, final Workplan entity) {
		assert request != null;
		assert entity != null;
		
		System.out.println("Antes del save: " + entity.getTask());
		this.repository.save(entity);

	}

	
	
	
}
