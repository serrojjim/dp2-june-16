<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:form>



	<acme:form-textbox code="manager.task.form.label.title" path="title" readonly="false"/>
	<acme:form-textarea code="manager.task.form.label.description" path="description" readonly="false"/>
	<acme:form-textbox code="manager.task.form.label.url" path="url" readonly="false"/>
	<acme:form-checkbox code="manager.task.form.label.isFinished" path="isFinished" readonly="false"/>
	<acme:form-double code="manager.task.form.label.workload" path="workload" readonly="false"/>
	<acme:form-textbox code="manager.task.form.label.execution_period_initial_date" path="executionPeriod.initialDate" readonly="false"/>
	<acme:form-textbox code="manager.task.form.label.execution_period_final_date" path="executionPeriod.finalDate" readonly="false"/>
	<acme:form-submit test="${command == 'update'}" code="manager.task.form.button.update" action="/manager/task/update"/>
	<acme:form-submit test="${command == 'create'}" code="manager.task.form.button.create" action="/manager/task/create"/>

	
	
	
	
	
	<acme:form-return code="manager.task.form.button.return"/>
</acme:form>