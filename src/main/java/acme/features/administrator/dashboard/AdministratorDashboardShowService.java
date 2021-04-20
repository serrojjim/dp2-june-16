package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.dashboard.Dashboard;
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
		
		request.unbind(entity, model, "numberOfPublicTasks", "numberOfPrivateTasks", "numberOfFinishedTasks", "numberOfNonFinishedTasks",
		
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
//		final List<Task> allTasks = this.repository.findAllTasks();
//		final List<Workplan> allWorkplans = this.repository.findAllWorkplans();
//
//		final LocalDateTime today = LocalDateTime.now();
//
//		final Integer size = allWorkplans.size();
//
//		// First statistics
//		Integer finished = 0;
//		Integer nonFinished = 0;
//		Integer isPublic = 0;
//		Integer isPrivate = 0;
//
//		
//		final Double avgExecutionPeriodTasks = allWorkplans.stream()
//			.mapToLong(x -> ChronoUnit.DAYS.between(x.getExecutionPeriodTasks().getFinalDate(), x.getExecutionPeriodTasks().getInitialDate()))
//			.average().orElse(0.);
//		Double tempExecutionPeriodTasks = 0.;
//		
//		for (final Workplan w : allWorkplans) {
//			if (w.getIsPrivate().booleanValue())
//				isPrivate++;
//			else
//				isPublic++;
//
//			if (today.compareTo(w.getExecutionPeriodTasks().getFinalDate()) > 0)
//				finished++;
//			else
//				nonFinished++;
//
//			final Double workload = w.getTotalWorkloadTasks();
//
//			if (workload < this.minWorkloadTasks)
//				this.minWorkloadTasks = workload;
//			if (workload > this.maxWorkloadTasks)
//				this.maxWorkloadTasks = workload;
//
//			final Long days = ChronoUnit.DAYS.between(w.getExecutionPeriodTasks().getInitialDate(), w.getExecutionPeriodTasks().getFinalDate());
//
//			if (days < this.minExecutionPeriodTasks)
//				this.minExecutionPeriodTasks = days;
//			if (days > this.maxExecutionPeriodTasks)
//				this.maxExecutionPeriodTasks = days;
//
//			this.tempWorkloadTasks += Math.pow(workload - this.avgWorkloadTasks, 2);
//			tempExecutionPeriodTasks += Math.pow(days - avgExecutionPeriodTasks, 2);
//
//		}
//		
		return result;
	}
	
	
	public void statistics(final Model model) {
//
//	//l.stream().mapToDouble(Workplan::getTotalWorkloadTasks).average().orElse(0.);
//	
//		
//
//		model.setAttribute("finished", finished);
//		model.setAttribute("nonFinished", nonFinished);
//		model.setAttribute("isPublic", isPublic);
//		model.setAttribute("isPrivate", isPrivate);
//
//		model.setAttribute("minWorkloadTasks", minWorkloadTasks);
//		model.setAttribute("maxWorkloadTasks", maxWorkloadTasks);
//		model.setAttribute("avgWorkloadTasks", avgWorkloadTasks);
//		model.setAttribute("dvtWorkloadTasks", Math.sqrt(tempWorkloadTasks / size));
//
//		model.setAttribute("minExecutionPeriodTasks", minExecutionPeriodTasks);
//		model.setAttribute("maxExecutionPeriodTasks", maxExecutionPeriodTasks);
//		model.setAttribute("avgExecutionPeriodTasks", avgExecutionPeriodTasks);
//		model.setAttribute("dvtExecutionPeriodTasks", Math.sqrt(tempExecutionPeriodTasks / size));
	}

}