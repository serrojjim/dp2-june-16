
package acme.features.administrator.workplan;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

	public void statistics(final Model model) {

		final LocalDateTime today = LocalDateTime.now();

		final List<Workplan> l = this.repository.findMany();
		final Integer size = l.size();

		// First statistics
		Integer finished = 0;
		Integer nonFinished = 0;
		Integer isPublic = 0;
		Integer isPrivate = 0;

		// Second statistics
		Double minWorkload = 0.;
		Double maxWorkload = 0.;
		final Double avgWorkload = l.stream().mapToDouble(Workplan::getTotalWorkload).average().orElse(0.);
		Double tempWorkload = 0.;

		Long minExecutionPeriod = 0L;
		Long maxExecutionPeriod = 0L;
		final Double avgExecutionPeriod = l.stream()
			.mapToLong(x -> ChronoUnit.DAYS.between(x.getExecutionPeriod().getFinalDate(), x.getExecutionPeriod().getInitialDate()))
			.average().orElse(0.);
		Double tempExecutionPeriod = 0.;
		
		for (final Workplan w : l) {
			if (w.getIsPrivate().booleanValue())
				isPrivate++;
			else
				isPublic++;

			if (today.compareTo(w.getExecutionPeriod().getFinalDate()) > 0)
				finished++;
			else
				nonFinished++;

			final Double workload = w.getTotalWorkload();

			if (workload < minWorkload)
				minWorkload = workload;
			if (workload > maxWorkload)
				maxWorkload = workload;

			final Long days = ChronoUnit.DAYS.between(w.getExecutionPeriod().getInitialDate(), w.getExecutionPeriod().getFinalDate());

			if (days < minExecutionPeriod)
				minExecutionPeriod = days;
			if (days > maxExecutionPeriod)
				maxExecutionPeriod = days;

			tempWorkload += Math.pow(workload - avgWorkload, 2);
			tempExecutionPeriod += Math.pow(days - avgExecutionPeriod, 2);

		}

		model.setAttribute("finished", finished);
		model.setAttribute("nonFinished", nonFinished);
		model.setAttribute("isPublic", isPublic);
		model.setAttribute("isPrivate", isPrivate);

		model.setAttribute("minWorkload", minWorkload);
		model.setAttribute("maxWorkload", maxWorkload);
		model.setAttribute("avgWorkload", avgWorkload);
		model.setAttribute("dvtWorkload", Math.sqrt(tempWorkload / size));

		model.setAttribute("minExecutionPeriod", minExecutionPeriod);
		model.setAttribute("maxExecutionPeriod", maxExecutionPeriod);
		model.setAttribute("avgExecutionPeriod", avgExecutionPeriod);
		model.setAttribute("dvtExecutionPeriod", Math.sqrt(tempExecutionPeriod / size));
	}
	
	public void chart(final Model model) {
		
		final List<Workplan> l = this.repository.findMany();
		final Long published = l.stream().filter(Workplan::getIsPrivate).count();
		
		model.setAttribute("size", l.size());
		model.setAttribute("published", published);
		model.setAttribute("nonPublished", l.size() - published);
		
		
		
	}
	

}
