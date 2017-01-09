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
        <li class="active"><a href="<c:url value='/admin/dashboard/' />">Panel administratora</a></li>
        <li class="active">Status transakcji</li>
    </ol>
</header>

<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading"><h4>Zmień status transakcji</h4></div>

            <form:form method="POST" class="form-horizontal">
            <table class="table">
                <tr><td>
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable">Numer istnieje:</label>
                        <div class="col-md-7">
                            <c:choose>
                                <c:when test="${number_exist == 1}">
                                    TAK
                                </c:when>
                                <c:otherwise>
                                    NIE
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </td></tr>
                <tr><td>
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable">Dane odbiorcy:</label>
                        <div class="col-md-3">
                            ${transaction.name}<br/>
                            ${transaction.address1}<br/>
                            ${transaction.address2}<br/>
                        </div>
                        <div class="col-md-3">
                                ${user.firstName} ${user.lastName}<br/>
                                ${user.city}<br/>
                                ${user.postalcode}<br/>
                        </div>
                    </div>
                </td></tr>
                <tr><td>
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable">Wystarczająca ilość środków:</label>
                        <div class="col-md-7">
                            <c:choose>
                                <c:when test="${value_ok == 1}">
                                    TAK
                                </c:when>
                                <c:otherwise>
                                    NIE
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </td></tr>
                <tr><td>
                    <div class="form-group col-md-12">
                        <label class="col-md-3 control-lable" for="status">Wybierz status:</label>
                        <div class="col-md-7">
                            <select name="status" id="status" class="form-control">
                                <c:forEach items="${statuses}" var="stat">
                                    <option value="${stat.id}" <c:if test="${transaction.status.id==stat.id}">selected</c:if>>${stat.name} (${stat.description})</option>
                                </c:forEach>
                            </select>

                        </div>
                    </div>
                </td></tr>

                <tr><td>
                    <div class="form-actions pull-right">
                        <input type="submit" value="ZMIEŃ STATUS" class="btn btn-primary"/> albo <a href="<c:url value='/admin/transaction/list' />">Anuluj</a>
                    </div>
                </td></tr>

                </form:form>
</table>


                <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
                <script src="<c:url value='/static/js/jquery.min.js' />"></script>
                <!-- Include all compiled plugins (below), or include individual files as needed -->
                <script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
                <script src="<c:url value='/static/js/skrypty.js' />"></script>
</body>
</html>