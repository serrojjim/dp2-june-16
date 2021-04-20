<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:form>




	
	
	
	
	
	
	<jstl:if test="${canUpdate}">
		
	<acme:form-textbox code="manager.task.form.label.title" path="title" readonly="false"/>
	<acme:form-textarea code="manager.task.form.label.description" path="description" readonly="false"/>
	<acme:form-textbox code="manager.task.form.label.url" path="url" readonly="false"/>
	<acme:form-checkbox code="manager.task.form.label.isFinished" path="isFinished" readonly="false"/>
	<acme:form-double code="manager.task.form.label.workload" path="workload" readonly="false"/>
	<acme:form-textbox code="manager.task.form.label.execution_period_initial_date" path="executionPeriod.initialDate" readonly="false"/>
	<acme:form-textbox code="manager.task.form.label.execution_period_final_date" path="executionPeriod.finalDate" readonly="false"/>
	<acme:form-submit code="manager.task.form.label.update" action="/manager/task/update"/>
	<acme:form-submit code="manager.task.form.label.delete" action="/manager/task/delete"/>
	
	</jstl:if>
	
	<jstl:if test="${!canUpdate}">
		<jstl:if test="${command =='create'}">
	
		<acme:form-textbox code="manager.task.form.label.title" path="title" readonly="false"/>
	<acme:form-textarea code="manager.task.form.label.description" path="description" readonly="false"/>
	<acme:form-textbox code="manager.task.form.label.url" path="url" readonly="false"/>
	<acme:form-checkbox code="manager.task.form.label.isFinished" path="isFinished" readonly="false"/>
	<acme:form-double code="manager.task.form.label.workload" path="workload" readonly="false"/>
	<acme:form-textbox code="manager.task.form.label.execution_period_initial_date" path="executionPeriod.initialDate" readonly="false"/>
	<acme:form-textbox code="manager.task.form.label.execution_period_final_date" path="executionPeriod.finalDate" readonly="false"/>
	<acme:form-submit code="manager.task.form.label.create" action="/manager/task/create"/>
		
		</jstl:if>
		
		<jstl:if test="${command ==null}">
	
		<acme:form-textbox code="manager.task.form.label.title" path="title"/>
	<acme:form-textarea code="manager.task.form.label.description" path="description"/>
	<acme:form-textbox code="manager.task.form.label.url" path="url"/>
	<acme:form-checkbox code="manager.task.form.label.isFinished" path="isFinished"/>
	<acme:form-double code="manager.task.form.label.workload" path="workload"/>
	<acme:form-textbox code="manager.task.form.label.execution_period_initial_date" path="executionPeriod.initialDate"/>
	<acme:form-textbox code="manager.task.form.label.execution_period_final_date" path="executionPeriod.finalDate"/>
		</jstl:if>
	</jstl:if>
	
	
	
	<acme:form-return code="manager.task.form.button.return"/>
</acme:form>