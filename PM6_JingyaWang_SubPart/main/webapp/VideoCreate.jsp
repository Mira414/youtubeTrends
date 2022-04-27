<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Video</title>
</head>
<body>
	<h1>Create Video</h1>
	<form action="videocreate" method="post">
		<p>
			<label for="TrendingDate">TrendingDate (yyyy-mm-dd)</label> <input
				id="TrendingDate" name="TrendingDate" value="">
		</p>
		<p>
			<label for="Title">Title</label> <input id="Title" name="Title"
				value="">
		</p>
		<p>
			<label for="PublishTime">PublishTime (yyyy-mm-dd)</label> <input
				id="PublishTime" name="PublishTime" value="">
		</p>
		<p>
			<label for="Tags">Tags</label> <input id="Tags" name="Tags" value="">
		</p>
		<p>
			<label for="Views">Views</label> <input id="Views" name="Views"
				value="">
		</p>
		<p>
			<label for="CommentCount">CommentCount</label> <input
				id="CommentCount" name="CommentCount" value="">
		</p>
		<p>
			<label for="ThumbnailLink">ThumbnailLink</label> <input
				id="ThumbnailLink" name="ThumbnailLink" value="">
		</p>
		<p>
			<label for="Dislikes">Dislikes</label> <input id="Dislikes"
				name="Dislikes" value="">
		</p>
		<p>
			<label for="CommentsDisabled">CommentsDisabled</label> <input
				id="CommentsDisabled" name="CommentsDisabled" value="">
		</p>
		<p>
			<label for="RatingsDisabled">RatingsDisabled</label> <input
				id="RatingsDisabled" name="RatingsDisabled" value="">
		</p>
		<p>
			<label for="VideoErrorOrRemoved">VideoErrorOrRemoved</label> <input
				id="VideoErrorOrRemoved" name="VideoErrorOrRemoved" value="">
		</p>
		<p>
			<label for="Description">Description</label> <input id="Description"
				name="Description" value="">
		</p>
		<p>
			<label for="CategoryId">CategoryId</label> <input id="CategoryId"
				name="CategoryId" value="">
		</p>
		<p>
			<label for="CountryId">CountryId</label> <input id="CountryId"
				name="CountryId" value="">
		</p>
		<p>
			<label for="UserId">UserId</label> <input id="UserId" name="UserId"
				value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br />
	<br />
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>