package acme.features.anonymous.task;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.task.Task;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousTaskListService implements AbstractCreateService<Anonymous, Task> {

	
	
	@Autowired
	protected AnonymousTaskRepository taskRepository;

	
	
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
		
		request.unbind(entity, model, "author", "text", "moment");
	
	}

	public Collection<Task> findMany(final Request<Task> request){
		assert request != null;
		
		Collection<Task> result;
		
		result = this.taskRepository.findMany();
		

		return result;
	}

	@Override
	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
		
	}

	@Override
	public Task instantiate(final Request<Task> request) {
		return null;
	}

	@Override
	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
		
	}

	@Override
	public void create(final Request<Task> request, final Task entity) {
		
	}

}
