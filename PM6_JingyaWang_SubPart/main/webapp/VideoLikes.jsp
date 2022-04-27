<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<title>Find a Video</title>
</head>
<body>
 <div class="container theme-showcase" role="main">
	<!-- <form action="findvideolikes" method="post">
		<p>
			<label for="title">Video Likes</label>
		</p>
	</form> -->
	<br/>
		
	<h1>Video Likes</h1>
      <table class="table table-striped">
            <thead><tr>
                <th>Video Title</th>
                <th>User</th>
                <th>Time</th>

            </tr></thead>
            <c:forEach items="${likeDetails}" var="likeDetail" >
                 <tbody><tr>
                    <td><c:out value="${likeDetail.getVideoTitle()}" /></td>
                    <td><c:out value="${likeDetail.getUserName()}" /></td>
                    <td><c:out value="${likeDetail.getCreated()}" /></td>
      <%--              <td><c:out value="${likes}" /></td>
                     <td><c:out value="${videos.getTags()}" /></td>
                       <td><c:out value="${videos.getViews()}" /></td>
                    <td><c:out value="${videos.getDislikes()}" /></td>
                    <td><c:out value="${videos.getThumbnailLink()}" /></td>
                    <td><a href="videodelete?title=<c:out value="${videos.getTitle()}"/>">Delete</a></td>
                    <td><a href="videoupdate?title=<c:out value="${videos.getTitle()}"/>">Update</a></td> --%>
                </tr></tbody>
            </c:forEach>
       </table>
     </div>
    <!-- Bootstrap -->
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
