
package acme.features.administrator.workplan;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.workplan.Workplan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorWorkplanService implements AbstractListService<Administrator, Workplan> {

	@Autowired
	AdministratorWorkplanRepository repository;


	@Override
	public boolean authorise(final Request<Workplan> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Workplan> request, final Workplan entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "workload", "executionPeriod", "url", "isPrivate");

	}

	@Override
	public List<Workplan> findMany(final Request<Workplan> request) {
		assert request != null;
		return this.repository.findMany();
	}

	public void chart(final Model model) {
		
		final List<Workplan> l = this.repository.findMany();
		final Long published = l.stream().filter(Workplan::getIsPrivate).count();
		
		model.setAttribute("size", l.size());
		model.setAttribute("published", published);
		model.setAttribute("nonPublished", l.size() - published);
		
		
		
	}
	

}
