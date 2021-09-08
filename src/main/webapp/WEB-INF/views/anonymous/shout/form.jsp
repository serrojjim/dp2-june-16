<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="anonymous.shout.form.label.author" path="author" placeholder="John Davies"/>
	<acme:form-textarea code="anonymous.shout.form.label.text" path="text" placeholder="This is an example text 123"/>
	<acme:form-textbox code="anonymous.shout.form.label.link" path="link" placeholder="http://wwww.ebiblioteca.org"/>
	
	<acme:form-textbox code="anonymous.shout.form.label.sergiolo3" path="sergiolo1.sergiolo3" placeholder="anonymous.shout.form.placeholder.sergiolo3"/>
	<acme:form-checkbox code="anonymous.shout.form.label.sergiolo5" path="sergiolo1.sergiolo5"/>
	<acme:form-textbox code="anonymous.shout.form.label.sergiolo2" path="sergiolo1.sergiolo2" placeholder="anonymous.shout.form.placeholder.sergiolo2"/>
	<acme:form-money code="anonymous.shout.form.label.sergiolo4" path="sergiolo1.sergiolo4" placeholder="anonymous.shout.form.placeholder.sergiolo4"/>
	
	<acme:form-submit code="anonymous.shout.form.button.create" action="/anonymous/shout/create"/>
	<acme:form-return code="anonymous.shout.form.button.return"/>
</acme:form>