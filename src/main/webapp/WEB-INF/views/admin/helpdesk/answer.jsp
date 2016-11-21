<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
                <div class="navbar-form navbar-right">
                    <a href="<c:url value="/logout" />" class="btn btn-warning">Wyloguj się</a>
                </div>
            </div>
        </div>
    </nav>
    <ol class="breadcrumb">
        <li class="active bold">Bankowość internetowa</li>
        <li class="active"><a href="<c:url value='/admin/dashboard/' />">Panel administratora</a></li>
        <li class="active">Odpowiedz na zgłoszenie</li>
    </ol>
</header>
<div class="container-fluid">
    <%@include file="../../authheader.jsp" %>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead"><h4>Opis zgłoszenia</h4></span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Id</th>
                <th>Tytuł</th>
                <th>Pytanie</th>
                <th>Użytkownik</th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${helpdesks.id}</td>
                    <td>${helpdesks.title}</td>
                    <td>${helpdesks.question}</td>
                    <td>${helpdesks.user.ssoId}</td>
                </tr>
            <tr>
                <td colspan="4">
                    <form:form method="POST" class="form-horizontal">

                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="answer">Odpowiedź</label>
                                <div class="col-md-7">
                                    <textarea class="form-control" rows="3" name="answer" id="answer"></textarea>
                                </div>
                            </div>

                            <div class="form-actions pull-right">
                                <input type="submit" value="ODPOWIEDZ" class="btn btn-primary"/> albo <a href="<c:url value='/admin/helpdesk' />">Anuluj</a>
                            </div>

                        </form:form>
                </td>
            </tr>
            </tbody>
        </table>
    </div>

</div>


<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value='/static/js/jquery.min.js' />"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/static/js/skrypty.js' />"></script>
</body>
</html>