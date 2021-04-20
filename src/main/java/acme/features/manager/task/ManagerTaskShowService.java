


package acme.features.manager.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.task.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerTaskShowService implements AbstractShowService<Manager, Task> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerTaskRepository repository;

	// AbstractShowService<Administrator, UserAccount> interface --------------


	@Override
	public boolean authorise(final Request<Task> request) {
		

		final int uaId = request.getPrincipal().getAccountId();
		final int taskId = request.getModel().getInteger("id");
		final int userRequestId;
		try {
			 userRequestId =this.repository.findTaskById(taskId).getUserAccount().getId();

		}catch(final NullPointerException e) {
			return false;
		}
		

		return uaId==userRequestId;
	}

	@Override
	public void unbind(final Request<Task> request, final Task entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "executionPeriod.finalDate", "executionPeriod.initialDate","workload","description","url","isFinished","isPrivate");
		final String rol =request.getPrincipal().getActiveRole().getSimpleName();
		final int userAcountId = request.getPrincipal().getAccountId();
		final int taskId = entity.getId();
		
		final Task task = this.repository.findOneTaskByIdAndUA(taskId, userAcountId);
		
		if (rol.equals("Manager") && task!=null) {
			model.setAttribute("canUpdate", true);
		} else {
			model.setAttribute("canUpdate", false);
		}
		
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






	

