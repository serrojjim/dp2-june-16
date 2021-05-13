package acme.components.Spam;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.spam.Spam;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

public class AnonymousSpamService implements AbstractListService<Anonymous, Spam>{

	@Autowired
	AnonymousSpamRepository anonymousSpamRepository;
	
	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "threshold", "spamWords");
	
	}
	
	@Override
	public Collection<Spam> findMany(final Request<Spam> request){
		assert request != null;
		
		return this.anonymousSpamRepository.findSpam();
	}
	
	
	
}
