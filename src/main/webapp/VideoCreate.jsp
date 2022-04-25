<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>Create a Video</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <h1>Create Video</h1>
    </div>
    <form action="videocreate" method="post">
        <p>
        <h2><label for="TrendingDate">TrendingDate (yyyy-mm-dd)</label></h2> <input
            id="TrendingDate" name="TrendingDate" value="">
        </p>
        <p>
        <h2><label for="Title">Title</label></h2> <input id="Title" name="Title"
                                                         value="">
        </p>
        <p>
        <h2><label for="PublishTime">PublishTime (yyyy-mm-dd)</label></h2> <input
            id="PublishTime" name="PublishTime" value="">
        </p>
        <p>
        <h2><label for="Tags">Tags</label></h2> <input id="Tags" name="Tags" value="">
        </p>
        <p>
        <h2><label for="Views">Views</label></h2> <input id="Views" name="Views"
                                                         value="">
        </p>
        <p>
        <h2><label for="CommentCount">CommentCount</label></h2> <input
            id="CommentCount" name="CommentCount" value="">
        </p>
        <p>
        <h2><label for="ThumbnailLink">ThumbnailLink</label></h2> <input
            id="ThumbnailLink" name="ThumbnailLink" value="">
        </p>
        <p>
        <h2><label for="Dislikes">Dislikes</label></h2> <input id="Dislikes"
                                                               name="Dislikes" value="">
        </p>
        <p>
        <h2><label for="CommentsDisabled">CommentsDisabled</label></h2> <input
            id="CommentsDisabled" name="CommentsDisabled" value="">
        </p>
        <p>
        <h2><label for="RatingsDisabled">RatingsDisabled</label></h2> <input
            id="RatingsDisabled" name="RatingsDisabled" value="">
        </p>
        <p>
        <h2><label for="VideoErrorOrRemoved">VideoErrorOrRemoved</label></h2> <input
            id="VideoErrorOrRemoved" name="VideoErrorOrRemoved" value="">
        </p>
        <p>
        <h2><label for="Description">Description</label></h2>  <input id="Description"
                                                                      name="Description" value="">
        </p>
        <p>
        <h2><label for="CategoryId">CategoryId</label></h2>  <input id="CategoryId"
                                                                    name="CategoryId" value="">
        </p>
        <p>
        <h2><label for="CountryId">CountryId</label></h2>  <input id="CountryId"
                                                                  name="CountryId" value="">
        </p>
        <p>
        <h2><label for="UserId">UserId</label></h2>  <input id="UserId" name="UserId"
                                                            value="">
        </p>
        <p>
            <input type="submit" class="btn btn-lg btn-primary">
        </p>
    </form>
    <br/>
    <br/>
    <p>
    <div class="alert alert-success" role="alert">
        <span id="successMessage"><b>${messages.success}</b></span>
    </div>
    </p>
</div>

<!-- Bootstrap -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>

</body>
</html>