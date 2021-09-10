<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.author" path="author" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.text" path="text" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.link" path="link" width="20%"/>
	
	<acme:list-column code="anonymous.shout.list.label.deadline" path="mocke.deadline" width="15%"/>
	<acme:list-column code="anonymous.shout.list.label.important" path="mocke.important" width="5%"/>
	<acme:list-column code="anonymous.shout.list.label.identification" path="mocke.identification" width="20%"/>
	<acme:list-column code="anonymous.shout.list.label.budget" path="mocke.budget" />

</acme:list>