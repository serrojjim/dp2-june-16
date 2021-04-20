<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:form>


	
	
	
	
	<jstl:if test="${canUpdate}">
		<acme:form-textbox code="authenticated.task.form.label.title" path="title" readonly="false"/>
	<acme:form-textarea code="authenticated.task.form.label.description" path="description" readonly="false"/>
	<acme:form-textbox code="authenticated.task.form.label.url" path="url" readonly="false"/>
	<acme:form-checkbox code="authenticated.task.form.label.isFinished" path="isFinished" readonly="false"/>
	<acme:form-double code="authenticated.task.form.label.workload" path="workload" readonly="false"/>
	<acme:form-textbox code="authenticated.task.form.label.execution_period_initial_date" path="executionPeriod.initialDate" readonly="false"/>
	<acme:form-textbox code="authenticated.task.form.label.execution_period_final_date" path="executionPeriod.finalDate" readonly="false"/>
				<acme:form-submit code="authenticated.task.form.label.execution_period_final_date" action="/manager/task/update"/>
	
	</jstl:if>
	
	<jstl:if test="${!canUpdate}">
		<acme:form-textbox code="authenticated.task.form.label.title" path="title"/>
	<acme:form-textarea code="authenticated.task.form.label.description" path="description"/>
	<acme:form-textbox code="authenticated.task.form.label.url" path="url"/>
	<acme:form-checkbox code="authenticated.task.form.label.isFinished" path="isFinished"/>
	<acme:form-double code="authenticated.task.form.label.workload" path="workload"/>
	<acme:form-textbox code="authenticated.task.form.label.execution_period_initial_date" path="executionPeriod.initialDate"/>
	<acme:form-textbox code="authenticated.task.form.label.execution_period_final_date" path="executionPeriod.finalDate"/>
	</jstl:if>
	
	
	
	
	
	<acme:form-return code="authenticated.task.form.button.return"/>
</acme:form>