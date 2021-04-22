package acme.features.manager.workplan;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Manager;
import acme.entities.workplan.Workplan;
import acme.features.manager.task.ManagerTaskRepository;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class ManagerWorkplanShowService implements AbstractShowService<Manager, Workplan> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ManagerWorkplanRepository repository;

	@Autowired
	protected ManagerTaskRepository taskRepository;

	// AbstractShowService<Administrator, UserAccount> interface --------------


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
	public Workplan findOne(final Request<Workplan> request) {
		assert request != null;

		final int id = request.getModel().getInteger("id");
		return this.repository.findById(id).get();
	}

}






	

