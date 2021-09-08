package acme.entities.dashboard;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {
	
	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	Integer numberOfPublicTasks;
	Integer numberOfPrivateTasks;
	Integer numberOfFinishedTasks;
	Integer numberOfNonFinishedTasks;
	
	Integer numberOfPublicWorkplans;
	Integer numberOfPrivateWorkplans;
	Integer numberOfFinishedWorkplans;
	Integer numberOfNonFinishedWorkplans;
	
	Double 	minWorkloadTasks;
	Double 	maxWorkloadTasks;
	Double 	avgWorkloadTasks;
	Double 	dvtWorkloadTasks;
	
	Long 	minExecutionPeriodTasks;
	Long 	maxExecutionPeriodTasks;
	Double 	avgExecutionPeriodTasks;
	Double 	dvtExecutionPeriodTasks;
	
	Double 	minWorkloadWorkplans;
	Double 	maxWorkloadWorkplans;
	Double 	avgWorkloadWorkplans;
	Double 	dvtWorkloadWorkplans;
	
	Long 	minExecutionPeriodWorkplans;
	Long 	maxExecutionPeriodWorkplans;
	Double 	avgExecutionPeriodWorkplans;
	Double 	dvtExecutionPeriodWorkplans;
	
	Integer totalNumberOfWorkplans;
	Long totalNumberOfPublishedWorkplans;
	Long totalNumberOfNonPublishedWorkplans;
	
	Double ratioFlaggedAsImportant;
	Double 	avgSergiolo1_1;
	Double 	avgSergiolo1_2;
	Double 	avgSergiolo1_3;
	Double 	dvtSergiolo1_1;
	Double 	dvtSergiolo1_2;
	Double 	dvtSergiolo1_3;	
	Double 	ratioZero;
}