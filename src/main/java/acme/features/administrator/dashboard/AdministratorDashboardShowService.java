package acme.features.administrator.dashboard;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.dashboard.Dashboard;
import acme.entities.task.Task;
import acme.entities.workplan.Workplan;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	protected AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, 
			"numberOfPublicTasks", 
			"numberOfPrivateTasks", 
			"numberOfFinishedTasks", 
			"numberOfNonFinishedTasks",
		
			"numberOfPublicWorkplans",
			"numberOfPrivateWorkplans",
			"numberOfFinishedWorkplans",
			"numberOfNonFinishedWorkplans",
		
			"minWorkloadTasks",
			"maxWorkloadTasks",
			"avgWorkloadTasks",
			"dvtWorkloadTasks",
			
			"minExecutionPeriodTasks",
			"maxExecutionPeriodTasks",
			"avgExecutionPeriodTasks",
			"dvtExecutionPeriodTasks",
		
			"minWorkloadWorkplans",
			"maxWorkloadWorkplans",
			"avgWorkloadWorkplans",
			"dvtWorkloadWorkplans",
			
			"minExecutionPeriodWorkplans",
			"maxExecutionPeriodWorkplans",
			"avgExecutionPeriodWorkplans",
			"dvtExecutionPeriodWorkplans",
		
			"totalNumberOfWorkplans",
			"totalNumberOfPublishedWorkplans");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		final Dashboard result = this.repository.getDashboard();

		final List<Task> allTasks = this.repository.findAllTasks();
		final Integer numberOfTasks = allTasks.size();

		final List<Workplan> allWorkplans = this.repository.findAllWorkplans();
		final Integer numberOfWorkplans = allWorkplans.size();

		final LocalDateTime today = LocalDateTime.now();

		Integer numberOfPublicTasks = 0;
		Integer numberOfPrivateTasks = 0;
		Integer numberOfFinishedTasks = 0;
		Integer numberOfNonFinishedTasks = 0;

		Integer numberOfPublicWorkplans = 0;
		Integer numberOfPrivateWorkplans = 0;
		Integer numberOfFinishedWorkplans = 0;
		Integer numberOfNonFinishedWorkplans = 0;

		Double minWorkloadTasks = 0.;
		Double maxWorkloadTasks = 0.;
		final Double avgWorkloadTasks = allTasks.stream().mapToDouble(Task::getWorkload).average().orElse(0.);
		Double dvtWorkloadTasks = 0.;
		
		Long minExecutionPeriodTasks = 0L;
		Long maxExecutionPeriodTasks = 0L;
		final Double avgExecutionPeriodTasks = allTasks.stream().mapToLong(x -> x.getExecutionPeriod().getWorkloadHours()).average().orElse(0.);
		Double dvtExecutionPeriodTasks = 0.;

		Double minWorkloadWorkplans = 0.;
		Double maxWorkloadWorkplans = 0.;
		final Double avgWorkloadWorkplans = allWorkplans.stream().mapToDouble(Workplan::getTotalWorkload).average().orElse(0.);
		Double dvtWorkloadWorkplans = 0.;

		Long minExecutionPeriodWorkplans = 0L;
		Long maxExecutionPeriodWorkplans = 0L;
		final Double avgExecutionPeriodWorkplans = allWorkplans.stream().mapToLong(x -> x.getExecutionPeriod().getWorkloadHours()).average().orElse(0.);
		Double dvtExecutionPeriodWorkplans = 0.;

		for (final Workplan w : allWorkplans) {
			if (w.getIsPrivate().booleanValue())
				numberOfPrivateWorkplans++;
			else
				numberOfPublicWorkplans++;

			if (today.compareTo(w.getExecutionPeriod().getFinalDate()) > 0)
				numberOfFinishedWorkplans++;
			else
				numberOfNonFinishedWorkplans++;

			final Double workload = w.getTotalWorkload();

			if (workload < minWorkloadWorkplans)
				minWorkloadWorkplans = workload;
			if (workload > maxWorkloadWorkplans)
				maxWorkloadWorkplans = workload;

			final Long days = ChronoUnit.DAYS.between(w.getExecutionPeriod().getInitialDate(), w.getExecutionPeriod().getFinalDate());

			if (days < minExecutionPeriodWorkplans)
				minExecutionPeriodWorkplans = days;
			if (days > maxExecutionPeriodWorkplans)
				maxExecutionPeriodWorkplans = days;

			dvtWorkloadWorkplans += Math.pow(workload - avgWorkloadWorkplans, 2);
			dvtExecutionPeriodWorkplans += Math.pow(days - avgExecutionPeriodWorkplans, 2);
		}

		dvtWorkloadWorkplans = Math.sqrt(dvtWorkloadWorkplans / numberOfWorkplans);
		dvtExecutionPeriodWorkplans = Math.sqrt(dvtExecutionPeriodWorkplans / numberOfWorkplans);

		for (final Task t : allTasks) {
			if (t.getIsPrivate().booleanValue())
				numberOfPrivateTasks++;
			else
				numberOfPublicTasks++;

			if (today.compareTo(t.getExecutionPeriod().getFinalDate()) > 0)
				numberOfFinishedTasks++;
			else
				numberOfNonFinishedTasks++;

			final Double workload = t.getWorkload();

			if (workload < minWorkloadTasks)
				minWorkloadTasks = workload;
			if (workload > maxWorkloadTasks)
				maxWorkloadTasks = workload;

			final Long days = ChronoUnit.DAYS.between(t.getExecutionPeriod().getInitialDate(), t.getExecutionPeriod().getFinalDate());

			if (days < minExecutionPeriodTasks)
				minExecutionPeriodTasks = days;
			if (days > maxExecutionPeriodTasks)
				maxExecutionPeriodTasks = days;

			dvtWorkloadTasks += Math.pow(workload - avgWorkloadTasks, 2);
			dvtExecutionPeriodTasks += Math.pow(days - avgExecutionPeriodTasks, 2);
		}

		dvtWorkloadTasks = Math.sqrt(dvtWorkloadTasks / numberOfTasks);
		dvtExecutionPeriodTasks = Math.sqrt(dvtExecutionPeriodTasks / numberOfTasks);

		result.setNumberOfPublicTasks(numberOfPublicTasks);
		result.setNumberOfPrivateTasks(numberOfPrivateTasks);
		result.setNumberOfFinishedTasks(numberOfFinishedTasks);
		result.setNumberOfNonFinishedTasks(numberOfNonFinishedTasks);

		result.setNumberOfPublicWorkplans(numberOfPublicWorkplans);
		result.setNumberOfPrivateWorkplans(numberOfPrivateWorkplans);
		result.setNumberOfFinishedWorkplans(numberOfFinishedWorkplans);
		result.setNumberOfNonFinishedWorkplans(numberOfNonFinishedWorkplans);

		result.setMinWorkloadTasks(minWorkloadTasks);
		result.setMaxWorkloadTasks(maxWorkloadTasks);
		result.setAvgWorkloadTasks(avgWorkloadTasks);
		result.setDvtWorkloadTasks(dvtWorkloadTasks);

		result.setMinExecutionPeriodTasks(minExecutionPeriodTasks);
		result.setMaxExecutionPeriodTasks(maxExecutionPeriodTasks);
		result.setAvgExecutionPeriodTasks(avgExecutionPeriodTasks);
		result.setDvtExecutionPeriodTasks(dvtExecutionPeriodTasks);

		result.setMinWorkloadWorkplans(minWorkloadWorkplans);
		result.setMaxWorkloadWorkplans(maxWorkloadWorkplans);
		result.setAvgWorkloadWorkplans(avgWorkloadWorkplans);
		result.setDvtWorkloadWorkplans(dvtWorkloadWorkplans);

		result.setMinExecutionPeriodWorkplans(minExecutionPeriodWorkplans);
		result.setMaxExecutionPeriodWorkplans(maxExecutionPeriodWorkplans);
		result.setAvgExecutionPeriodWorkplans(avgExecutionPeriodWorkplans);
		result.setDvtExecutionPeriodWorkplans(dvtExecutionPeriodWorkplans);

		result.setTotalNumberOfWorkplans(numberOfWorkplans);
		result.setTotalNumberOfPublishedWorkplans(1);

		return result;
	}

}
