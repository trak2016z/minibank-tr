<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>MiniBank</title>

    <!-- Bootstrap -->
    <link href="<c:url value='/static/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/static/css/style.css' />" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<header id="header">
    <nav class="navbar navbar-inverse navbar-static-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-usd" aria-hidden="true"></span>Mini Bank</a>
            </div>
            <div id="navbar" class="navbar-collapse">
                <p class="navbar-text">Witamy w systemie bankowości internetowej!</p>
            </div>
        </div>
    </nav>
    <ol class="breadcrumb">
        <li class="active bold">Bankowość internetowa</li>
        <li class="active"><a href="<c:url value='/dashboard/' />">Panel użytkownika</a></li>
        <li class="active">Dodawanie nowego zgłoszenia</li>
    </ol>
</header>

<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading"><h4>Dodaj nowe zgłoszenie</h4></div>
        <tr class="panel-body">
            <form:form method="POST" modelAttribute="helpdesk" class="form-horizontal">
            <table class="table">
                <tr><td>
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="title">Tytuł:</label>
                        <div class="col-md-7">
                            <form:input path="title" name="title" id="title" class="form-control" />
                            <div class="text-danger">
                                <form:errors path="title" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </td></tr>

                <tr><td>
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="question">Treść:</label>
                        <div class="col-md-7">
                            <form:textarea path="question" class="form-control" rows="3" name="question" id="question"></form:textarea>
                            <div class="text-danger">
                                <form:errors path="question" class="help-inline"/>
                            </div>
                        </div>
                    </div>
                </td></tr>
                    <form:input type="hidden" name="user" id="user" class="form-control" path="user.id"  />
                <tr><td>
                    <div class="form-actions pull-right">
                        <input type="submit" value="Dodaj zgłoszenie" class="btn btn-primary"/> albo <a href="<c:url value='/dashboard/helpdesk/list' />">Anuluj</a>
                    </div>
                </td></tr>

                </form:form>


                <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
                <script src="<c:url value='/static/js/jquery.min.js' />"></script>
                <!-- Include all compiled plugins (below), or include individual files as needed -->
                <script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
                <script src="<c:url value='/static/js/skrypty.js' />"></script>
</body>
</html>