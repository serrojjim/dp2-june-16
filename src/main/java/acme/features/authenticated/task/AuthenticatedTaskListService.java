package acme.features.authenticated.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.task.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedTaskListService implements AbstractListService<Authenticated, Task> {

	
	
	@Autowired
	protected AuthenticatedTaskRepository taskRepository;

	
	
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
		
		request.unbind(entity, model, "title", "executionPeriod.finalDate", "executionPeriod.initialDate","workload" );
	
	}

	@Override
	public Collection<Task> findMany(final Request<Task> request){
		assert request != null;
		
		Collection<Task> result;
		
		result = this.taskRepository.findMany();
	
		return result;
	}
	
	


	

}
