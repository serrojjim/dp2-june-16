<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.text" path="text" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.link" path="link" width="20%"/>
	
	<acme:list-column code="anonymous.shout.list.label.sergiolo3" path="sergiolo1.sergiolo3" width="15%"/>
	<acme:list-column code="anonymous.shout.list.label.sergiolo5" path="sergiolo1.sergiolo5" width="5%"/>
	<acme:list-column code="anonymous.shout.list.label.sergiolo2" path="sergiolo1.sergiolo2" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.sergiolo4" path="sergiolo1.sergiolo4" />

</acme:list>