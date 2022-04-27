<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a Category</title>
</head>
<body>
	<h1>Update Category</h1>
	<form action="categoriesUpdate" method="post">
		<p>
			<label for="title">Title</label>
			<input id="title" name="title" value="${fn:escapeXml(param.title)}">
		</p>
		<p>
			<label for="assignable">Assignable</label>
			<input id="assignable" name="assignable" value="${fn:escapeXml(param.assignable)}">
		</p>
		<p>
			<label for="newTitle">New Title</label>
			<input id="newTitle" name="newTitle" value="">
		</p>
		<p>
			<label for="newAssignable">New Assignable</label>
			<input id="newAssignable" name="newAssignable" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>