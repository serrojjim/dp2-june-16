package acme.features.authenticated.task;
//package acme.features.anonymous.task;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import acme.entities.task.Task;
//import acme.features.anonymous.userAccount.AnonymousUserAccountRepository;
//import acme.framework.components.Errors;
//import acme.framework.components.Model;
//import acme.framework.components.Request;
//import acme.framework.entities.Anonymous;
//import acme.framework.services.AbstractCreateService;
//
//@Service
//public class AnonymousTaskCreateService implements AbstractCreateService<Anonymous, Task> {
//
//	
//	
//	@Autowired
//	protected AnonymousUserAccountRepository repository;
//
//	
//	
//	@Override
//	public boolean authorise(final Request<Task> request) {
//		assert request != null;
//
//		return true;
//	}
//	
//	
//	
//	@Override
//	public void bind(final Request<Task> request, final Task entity, final Errors errors) {
//		assert request != null;
//		assert entity != null;
//		assert errors != null;
//
//		request.bind(entity, errors);
//	}
//
//	@Override
//	public void unbind(final Request<Task> request, final Task entity, final Model model) {
//		
//	}
//
//	@Override
//	public Task instantiate(final Request<Task> request) {
//		return null;
//	}
//
//	@Override
//	public void validate(final Request<Task> request, final Task entity, final Errors errors) {
//		
//	}
//
//	@Override
//	public void create(final Request<Task> request, final Task entity) {
//		
//	}
//
//	
//
//}
