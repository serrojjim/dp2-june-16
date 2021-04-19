<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:form>
	<acme:form-textbox code="authenticated.task.form.label.title" path="title"/>
	<acme:form-textarea code="authenticated.task.form.label.description" path="description"/>
	<acme:form-textbox code="authenticated.task.form.label.url" path="url"/>
	<acme:form-checkbox code="authenticated.task.form.label.isFinished" path="isFinished"/>
	<acme:form-double code="authenticated.task.form.label.workload" path="workload"/>
	<acme:form-textbox code="authenticated.task.form.label.execution_period_initial_date" path="executionPeriod.initialDate"/>
	<acme:form-textbox code="authenticated.task.form.label.execution_period_final_date" path="executionPeriod.finalDate"/>
	
	
	
	<acme:form-submit test="${command == 'create'}" code="authenticated.task.form.button.create" action="/authenticated/task/create"/>
	<acme:form-submit test="${command == 'update'}" code="authenticated.task.form.button.update" action="/authenticated/task/update"/>
	
	<acme:form-return code="authenticated.task.form.button.return"/>
</acme:form>