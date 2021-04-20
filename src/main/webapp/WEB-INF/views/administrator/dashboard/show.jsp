<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<h2>
	<acme:message code="administrator.dashboard.entities.title.general-indicators"/>
</h2>

<table class="table table-sm">
	<caption>
		<acme:message code="administrator.dashboard.entities.title.general-indicators"/>
	</caption>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfPublicTasks"/>
		</th>
		<td>
			<acme:print value="${numberOfPublicTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfPrivateTasks"/>
		</th>
		<td>
			<acme:print value="${numberOfPrivateTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfFinishedTasks"/>
		</th>
		<td>
			<acme:print value="${numberOfFinishedTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfNonFinishedTasks"/>
		</th>
		<td>
			<acme:print value="${numberOfNonFinishedTasks}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.averageOfTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${averageOfTaskExecutionPeriods}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.deviationOfTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${deviationOfTaskExecutionPeriods}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.minOfTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${minOfTaskExecutionPeriods}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.maxOfTaskExecutionPeriods"/>
		</th>
		<td>
			<acme:print value="${maxOfTaskExecutionPeriods}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.averageOfTaskWorkloads"/>
		</th>
		<td>
			<acme:print value="${averageOfTaskWorkloads}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.deviationOfTaskWorkloads"/>
		</th>
		<td>
			<acme:print value="${deviationOfTaskWorkloads}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.minOfTaskWorkloads"/>
		</th>
		<td>
			<acme:print value="${minOfTaskWorkloads}"/>
		</td>
	</tr>
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.maxOfTaskWorkloads"/>
		</th>
		<td>
			<acme:print value="${maxOfTaskWorkloads}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfPublicWorkPlans"/>
		</th>
		<td>
			<acme:print value="${numberOfPublicWorkPlans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfFinishedWorkPlans"/>
		</th>
		<td>
			<acme:print value="${numberOfFinishedWorkPlans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfNonFinishedWorkPlans"/>
		</th>
		<td>
			<acme:print value="${numberOfNonFinishedWorkPlans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.averageOfWorkPlanPeriods"/>
		</th>
		<td>
			<acme:print value="${deviationOfWorkPlanPeriods}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.minOfWorkPlanPeriods"/>
		</th>
		<td>
			<acme:print value="${minOfWorkPlanPeriods}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.averageOfWorkplanWorkloads"/>
		</th>
		<td>
			<acme:print value="${averageOfWorkplanWorkloads}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.deviationOfWorkplanWorkloads"/>
		</th>
		<td>
			<acme:print value="${deviationOfWorkplanWorkloads}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.minOfWorkplanWorkloads"/>
		</th>
		<td>
			<acme:print value="${minOfWorkplanWorkloads}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.maxOfWorkplanWorkloads"/>
		</th>
		<td>
			<acme:print value="${maxOfWorkplanWorkloads}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfWorkPlans"/>
		</th>
		<td>
			<acme:print value="${numberOfWorkPlans}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfPublishedWorkPlan"/>
		</th>
		<td>
			<acme:print value="${numberOfPublishedWorkPlan}"/>
		</td>
	</tr>	
	<tr>
		<th scope="row">
			<acme:message code="administrator.dashboard.entities.label.numberOfNonPublishedWorkPlans"/>
		</th>
		<td>
			<acme:print value="${numberOfNonPublishedWorkPlans}"/>
		</td>
	</tr>										
</table>
<h2>
	<acme:message code="administrator.dashboard.entities.title.application-statuses"/>
</h2>

<div>
	<canvas id="canvas"></canvas>
</div>  