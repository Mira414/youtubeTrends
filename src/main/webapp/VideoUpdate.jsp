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
    <title>Update a Video</title>
</head>
<body>
<div class="container theme-showcase" role="main">
    <div class="jumbotron">
        <h1>Update Video</h1>
    </div>
    <form action="videoupdate" method="post">
        <p>
        <h2><label for="title">Title</label></h2>
        <input id="title" name="title" value="${fn:escapeXml(param.title)}">
        </p>
        <p>
        <h2><label for="newTitle">New Title</label></h2>
        <input id="newTitle" name="newTitle" value="">
        </p>
        <p>
            <input type="submit" class="btn btn-lg btn-primary">
        </p>
    </form>
    <br/><br/>
    <p>
    <div class="alert alert-success" role="alert">
        <h2><span id="successMessage"><b>${messages.success}</b></span></h2>
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