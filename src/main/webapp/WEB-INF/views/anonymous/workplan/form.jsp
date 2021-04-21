<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:form readonly="true">


	<acme:form-textbox code="anonymous.workplan.form.label.title" path="title"/>
	<acme:form-double code="anonymous.workplan.form.label.workload" path="workload"/>
	<acme:form-moment code="anonymous.workplan.form.label.execution_period_initial_date" path="executionPeriod.initialDate"/>
	<acme:form-moment code="anonymous.workplan.form.label.execution_period_final_date" path="executionPeriod.finalDate"/>
	
	<acme:form-return code="anonymous.workplan.form.button.return"/>
</acme:form>