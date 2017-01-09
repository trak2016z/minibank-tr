<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <li class="active"><a href="<c:url value='/dashboard/' />">Panel użytkownika</a></li>
        <li class="active">Lista rachunków bankowych</li>
    </ol>
</header>
<div class="container-fluid">
    <%@include file="../../authheader.jsp" %>
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead"><h4>Lista rachunków bankowych</h4></span></div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Id</th>
                <th>Nazwa</th>
                <th>Numer</th>
                <th>Zawartość</th>
                <th>Akcje</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${wallets}" var="acc">
                <c:choose>
                    <c:when test="${acc.active==true}">
                        <tr>
                            <td>${acc.id}</td>
                            <td>${acc.name}</td>
                            <td>${acc.number}</td>
                            <td><fmt:formatNumber type="number"
                                                  minFractionDigits="2" value="${acc.content}" /></td>
                            <td><a href="<c:url value='/dashboard/transaction/new/${acc.id}' />" class="btn btn-success">PRZELEW</a>
                                <a href="#" data-href="<c:url value='/dashboard/bankaccount/delete-${acc.id}' />" data-toggle="modal" data-target="#deleteModal" class="btn btn-danger custom-width">ZAWIEŚ</a></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <tr class="warning">
                            <td>${acc.id}</td>
                            <td>${acc.name}</td>
                            <td>${acc.number}</td>
                            <td><fmt:formatNumber type="number"
                                                  minFractionDigits="2" value="${acc.content}" /></td>
                            <td>ZAWIESZONE</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            </tbody>
        </table>
    </div>
        <div class="well">
            <a href="<c:url value='/dashboard/bankaccount/add' />">Dodaj nowy rachunek bankowy</a>
        </div>
</div>

<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">UWAGA! Wymagane potwierdzenie</h4>
            </div>

            <div class="modal-body">
                <p>Czy na pewno chcesz dokonać zawieszenia tego konta?</p>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Anuluj</button>
                <a class="btn btn-danger btn-ok">ZAWIEŚ</a>
            </div>
        </div>
    </div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value='/static/js/jquery.min.js' />"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
<script src="<c:url value='/static/js/skrypty.js' />"></script>

</body>
</html>