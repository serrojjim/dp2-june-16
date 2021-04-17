
package acme.features.administrator.task;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.task.Task;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorTaskService implements AbstractListService<Administrator, Task> {

	@Autowired
	AdministratorTaskRepository repository;


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

		request.unbind(entity, model, "title", "description", "workload", "executionPeriod", "url", "isPrivate");

	}

	@Override
	public List<Task> findMany(final Request<Task> request) {
		assert request != null;
		return this.repository.findMany();
	}

	public void statistics(final Request<Task> request, final Model model) {
		assert request != null;

		final LocalDateTime today = LocalDateTime.now();

		final List<Task> l = this.findMany(request);
		final Integer size = l.size();

		// First statistics
		Integer finished = 0;
		Integer nonFinished = 0;
		Integer isPublic = 0;
		Integer isPrivate = 0;

		// Second statistics
		Double minWorkload = 0.;
		Double maxWorkload = 0.;
		final Double avgWorkload = l.stream().mapToDouble(Task::getWorkload).average().getAsDouble();
		Double tempWorkload = 0.;

		Long minExecutionPeriod = 0L;
		Long maxExecutionPeriod = 0L;
		final Double avgExecutionPeriod = l.stream()
			.mapToLong(x -> ChronoUnit.DAYS.between(x.getExecutionPeriod().getFinalDate(), x.getExecutionPeriod().getInitialDate()))
			.average().getAsDouble();
		Double tempExecutionPeriod = 0.;

		for (final Task t : l) {
			if (t.getIsPrivate().booleanValue())
				isPrivate++;
			else
				isPublic++;

			if (today.compareTo(t.getExecutionPeriod().getFinalDate()) > 0)
				finished++;
			else
				nonFinished++;

			final Double workload = t.getWorkload();

			if (workload < minWorkload)
				minWorkload = workload;
			if (workload > maxWorkload)
				maxWorkload = workload;

			final Long days = ChronoUnit.DAYS.between(t.getExecutionPeriod().getInitialDate(), t.getExecutionPeriod().getFinalDate());

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

}
