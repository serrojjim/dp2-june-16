package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.task.Task;
import acme.entities.workplan.Workplan;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository{

//	@Query("select count(t) from Task t where t.isPublic='True'")
//	Integer numberOfPublicTasks();
//	
//	@Query("select count(t) from Task t where t.isPublic='False'")
//	Integer numberOfPrivateTasks();
//	
//	@Query("select count(t) from Task t where t.executionPeriod.finishDateTime < CURRENT_TIMESTAMP")
//	Integer numberOfFinishedTasks();
//
//	@Query("select count(t) from Task t where t.executionPeriod.finishDateTime > CURRENT_TIMESTAMP")
//	Integer numberOfNonFinishedTasks();
	
//	@Query("select count(w) from Workplan w where w.isPrivate='False'")
//	Integer numberOfPublicWorkplans();
//	
//	@Query("select count(w) from Workplan w where w.isPrivate='True'")
//	Integer numberOfPrivateWorkplans();
//	
//	@Query("select count(w) from Workplan w where w.executionPeriod.finalDate < CURRENT_TIMESTAMP")
//	Integer numberOfFinishedWorkplans();
//
//	@Query("select count(w) from Workplan w where w.executionPeriod.finalDate > CURRENT_TIMESTAMP")
//	Integer numberOfNonFinishedWorkplans();
	
	@Query("select t from Task t")
	List<Task> findAllTasks();
	
	@Query("select w from Workplan w")
	List<Workplan> findAllWorkplans();

//	@Query("select d from Dashboard d where d.id = 1")
//	Dashboard getDashboard();


}