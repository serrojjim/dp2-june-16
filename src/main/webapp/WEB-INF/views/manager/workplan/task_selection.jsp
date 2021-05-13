<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>
<acme:form>

	<acme:message code="manager.workplan.form.label.title"/>
	<jstl:out value="${title}"/> 
	
	<jstl:choose>
		<jstl:when test="${command != 'create'}">
		
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
	<acme:form-return code="manager.workplan.form.button.return"/>
</acme:form>

	
	
	

