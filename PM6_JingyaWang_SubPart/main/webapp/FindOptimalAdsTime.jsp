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
<title>Find Optimal Ads Time</title>
</head>
<body>
 <div class="container theme-showcase" role="main">
	<form action="findoptimaladstime" method="post">
	<div class="jumbotron">
		<h1>List the Top N Optimal Ads Time</h1>
		<h4>(Based on the Views of the Video Table)</h4>
		<h3>If you want to cooperate with the Video Creator, you can find the contact information below .</h3>
	</div>
	<p>
			<label for="topN">You want which rank videos info? </label>
			<input id="topN" name="topN" value="${fn:escapeXml(param.time)}">
		</p>	
		<p>
			<input type="submit" class="btn btn-lg btn-primary">
			<br/><br/>
		</p>
	</form>
	<br/>
	<br/>
		<div class="alert alert-info" role="alert">
	<h2><span id="successMessage"><b>${messages.success}</b></span></h2>
	</div>
	<br/>
	<h1>Top N Ads Time(Including Video Info)</h1>
      <table class="table table-striped">
            <thead><tr>
            	<th>Views</th>
                <th>PublishTime</th>
                <th>Title</th>
                <th>Tags</th>
                <th>thumbnailLink</th>
            </tr></thead>
            <c:forEach items="${videos}" var="videos" >
                 <tbody><tr>
                 	<td><c:out value="${videos.getViews()}" /></td>
                 	<td><c:out value="${videos.getPublishTime()}" /></td>
                    <td><c:out value="${videos.getTitle()}" /></td>
                    <td><c:out value="${videos.getTags()}" /></td>
                    <td><c:out value="${videos.getThumbnailLink()}" /></td>
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
