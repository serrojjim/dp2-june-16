<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.text" path="text" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.link" path="link" width="20%"/>

	<acme:list-column code="anonymous.shout.form.label.sergiolo2" path="sergiolo1.sergiolo2" />
	<acme:list-column code="anonymous.shout.form.label.sergiolo3" path="sergiolo1.sergiolo3" />
	<acme:list-column code="anonymous.shout.form.label.sergiolo4" path="sergiolo1.sergiolo4" />
	<acme:list-column code="anonymous.shout.form.label.sergiolo5" path="sergiolo1.sergiolo5" />
	
</acme:list>