<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:form>
	<acme:form-textbox code="manager.workplan.form.label.title" path="title" readonly="false"/> 
	<acme:form-checkbox code="manager.workplan.form.label.isPrivate" path="isPrivate" readonly="false"/>
	
	<acme:message code="manager.workplan.form.label.suggested_execution_period_initial_date"/>
		<jstl:out value="${suggestedExecutionPeriod.initialDate}" />
	<acme:form-textbox code="manager.workplan.form.label.execution_period_initial_date" path="executionPeriod.initialDate" readonly="false"/>
	
	<acme:message code="manager.workplan.form.label.suggested_execution_period_final_date"/>
		<jstl:out value="${suggestedExecutionPeriod.finalDate}" />
	<acme:form-textbox code="manager.workplan.form.label.execution_period_final_date" path="executionPeriod.finalDate" readonly="false"/>
	
	<jstl:choose>
		<jstl:when test="${command != 'create'}">
			<table class="table table-sm">
				<tr>
    				<th>
    				<acme:message code="manager.workplan.form.label.tareasseleccionadas"/> 
    				<jstl:out value="${workload}"></jstl:out> 
    				<acme:message code="manager.workplan.form.label.horas"/>
    				</th>
  				</tr>
				<jstl:forEach items="${allTasksAlreadySelected}" var="tsk">
				<tr>
					<td><jstl:out value="${tsk.title}"></jstl:out></td>
					<td><acme:form-submit code="manager.task.form.button.delete" action="/manager/workplan/delete_task_workplan?workplanId=${id}&taskId=${tsk.id}"/></td>
				</tr>
				</jstl:forEach>
			</table>
			
			<acme:form-select code="manager.workplan.form.label.taskSelect" path="task">
				<acme:form-option code="" value=""/>
				<jstl:forEach items="${allTasksAvailable}" var="tsk">
					<acme:form-option code="${tsk.title}" value="${tsk.id}"/>
				</jstl:forEach>
			</acme:form-select>
			
		</jstl:when>
		
		<jstl:otherwise>
			<acme:form-select code="manager.workplan.form.label.taskSelect" path="task">
			<acme:form-option code="" value=""/>
				<jstl:forEach items="${allTasks}" var="tsk">
					<acme:form-option code="${tsk.title}" value="${tsk.id}"/>
			</jstl:forEach>
			</acme:form-select>
		</jstl:otherwise>
	</jstl:choose>
	
	<acme:form-submit test="${command != 'create'}" code="manager.workplan.form.button.update" action="/manager/workplan/update"/>
	<acme:form-submit test="${command != 'create'}" code="manager.workplan.form.button.delete" action="/manager/workplan/delete"/>
	<acme:form-submit test="${command == 'create'}" code="manager.workplan.form.button.create" action="/manager/workplan/create"/>
	<acme:form-return code="manager.workplan.form.button.return"/>
</acme:form>

	
	
	

