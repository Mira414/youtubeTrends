<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a Category</title>
</head>
<body>
	<form action="findCategories" method="post">
		<h1>Search for a Category by title</h1>
		<p>
			<label for="categoryId">Title</label>
			<input id="categoryId" name="categoryId" value="${fn:escapeXml(param.title)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="categoriesCreate"><a href="categoriesCreate">Create Category</a></div>
	<br/>
	<h1>Matching Categories</h1>
        <table border="1">
            <tr>
                <th>Title</th>
                <th>Assignable</th>
                <th>Delete Category</th>
                <th>Update Category</th>
            </tr>
            <c:forEach items="${categories}" var="category" >
                <tr>
                    <td><c:out value="${category.getTitle()}" /></td>
                    <td><c:out value="${category.getAssignable()}" /></td>
                    <td><a href="categoriesDelete?categoryId=<c:out value="${category.getCategoryId()}"/>">Delete</a></td>
                    <td><a href="categoriesUpdate?categoryId=<c:out value="${category.getCategoryId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>
