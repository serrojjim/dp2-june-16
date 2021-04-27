package acme.features.administrator.spamWord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.SpamWord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorSpamWordDeleteService implements AbstractDeleteService<Administrator, SpamWord>{
	
	@Autowired
	protected AdministratorSpamWordRepository repository;
	
	@Override
	public boolean authorise(final Request<SpamWord> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<SpamWord> request, final SpamWord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		System.out.println("bind");
		
		request.bind(entity, errors);
		
	}

	@Override
	public void unbind(final Request<SpamWord> request, final SpamWord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		System.out.println(entity.getWord());
		
		request.unbind(entity, model, "word");
	}

	@Override
	public SpamWord findOne(final Request<SpamWord> request) {
		assert request != null;

		
		
		SpamWord result;
		int id;
		
		id = request.getModel().getInteger("id");
		System.out.println(id);
		result = this.repository.findSpamWordById(id);

		return result;
	}

	@Override
	public void validate(final Request<SpamWord> request, final SpamWord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<SpamWord> request, final SpamWord entity) {
		assert request != null;
		assert entity != null;

		System.out.println("deleting");
		
		this.repository.delete(entity);
	}
}
