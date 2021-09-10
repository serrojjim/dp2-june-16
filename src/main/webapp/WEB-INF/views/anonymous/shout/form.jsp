<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author" placeholder="John Davies"/>
	<acme:form-textarea code="anonymous.shout.form.label.text" path="text" placeholder="This is an example text 123"/>
	<acme:form-textbox code="anonymous.shout.form.label.link" path="link" placeholder="http://wwww.ebiblioteca.org"/>
	
	<acme:form-textbox code="anonymous.shout.form.label.deadline" path="mocke.deadline" placeholder="anonymous.shout.form.placeholder.deadline"/>
	<acme:form-checkbox code="anonymous.shout.form.label.important" path="mocke.important"/>
	<acme:form-textbox code="anonymous.shout.form.label.identification" path="mocke.identification" placeholder="anonymous.shout.form.placeholder.identification"/>
	<acme:form-money code="anonymous.shout.form.label.budget" path="mocke.budget" placeholder="anonymous.shout.form.placeholder.budget"/>
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>