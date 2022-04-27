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
	<form action="findvideos" method="post">
	<div class="jumbotron">
		<h1>Search for a Video by Titles</h1>
	</div>
		<p>
			<label for="title">title</label>
			<input id="title" name="title" value="${fn:escapeXml(param.title)}">
		</p>
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/>
		</p>
	</form>
	<br/>
	<!-- <button type="button" class="btn btn-primary"><a href="videocreate">Create Video</a></button> -->
	<div id="videoCreate">
		<a href="videocreate" class="btn btn-success">Create Video</a>
		<br/>
		<a href="findhottest" class="btn btn-info">Hottest Video </a>
		<br/>
		<a href="findoptimaladstime" class="btn btn-warning">Optimal Ads Time</a>
	</div>
	<br/>
		<div class="alert alert-info" role="alert">
	<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
	</div>
	<br/>
	<h1>Matching Video</h1>
      <table class="table table-striped">
            <thead><tr>
                <th>Title</th>
                <th>PublishTime</th>
                <th>Tags</th>
                <th>Views</th>
                <th>Dislikes</th>
                <th>Likes</th>
                <th>Delete Video</th>
                <th>Update Video</th>
            </tr></thead>
            <c:forEach items="${videos}" var="videos" >
                 <tbody><tr>
                    <td><c:out value="${videos.getTitle()}" /></td>
                    <td><c:out value="${videos.getPublishTime()}" /></td>
                    <td><c:out value="${videos.getTags()}" /></td>
                       <td><c:out value="${videos.getViews()}" /></td>
                    <td><c:out value="${videos.getDislikes()}" /></td>
                    <%-- <td><c:out value="${videos.getThumbnailLink()}" /></td> --%>
                    <td><a href="videolikes?videoId=<c:out value="${videos.getVideoId()}"/>">Likes</a></td>
                    <td><a href="videodelete?title=<c:out value="${videos.getTitle()}"/>">Delete</a></td>
                    <td><a href="videoupdate?title=<c:out value="${videos.getTitle()}"/>">Update</a></td>
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
