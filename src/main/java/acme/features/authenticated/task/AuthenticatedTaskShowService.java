/*
 * AdministratorUserAccountShowService.java

 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.task.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedTaskShowService implements AbstractShowService<Authenticated, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedTaskRepository repository;

	// AbstractShowService<Administrator, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<Task> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

//		StringBuilder buffer;
//		Collection<UserRole> roles;

		request.unbind(entity, model, "title", "executionPeriod.finalDate", "executionPeriod.initialDate","workload","description","url");
  
//		roles = entity.getRoles();
//		buffer = new StringBuilder();
//		for (final UserRole role : roles) {
//			buffer.append(role.getAuthorityName());
//			buffer.append(" ");
//		}
//
//		model.setAttribute("roleList", buffer.toString());
//
//		if (entity.isEnabled()) {
//			model.setAttribute("status", UserAccountStatus.ENABLED);
//		} else {
//			model.setAttribute("status", UserAccountStatus.DISABLED);
//		}
//
//		if (entity.hasRole(Administrator.class) || entity.hasRole(Anonymous.class)) {
//			model.setAttribute("canUpdate", false);
//		} else {
//			model.setAttribute("canUpdate", true);
//		}
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

}
