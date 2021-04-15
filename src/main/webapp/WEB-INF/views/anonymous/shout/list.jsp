<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="anonymous.shout.list.label.moment" path="moment" widht=20%"/>
	<acme:list-column code="anonymous.shout.list.label.author" path="author" widht=20%"/>
	<acme:list-column code="anonymous.shout.list.label.text" path="text" widht=20%"/>


</acme:list>