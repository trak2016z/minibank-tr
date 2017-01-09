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
        <li class="active">Zlecenie przelewu</li>
    </ol>
</header>

<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel-heading"><h4>Zlecenie przelewu</h4></div>
            <div style="margin: auto; width: 300px;">
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Przelew zwykły</a></li>
                    <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Przelew z szablonu</a></li>
                </ul>
            </div>
            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <form:form method="POST" modelAttribute="transaction" mehod="post" class="form-horizontal">
                        <table class="table table-striped">
                            <tr><td>
                                <div class="form-group col-md-12">
                                    <label class="col-md-3 control-lable" for="source_wallet">Rachunek źródłowy:</label>
                                    <div class="col-md-7">
                                        <form:select path="source_wallet" id="source_wallet" class="form-control">
                                            <c:forEach items="${source_wallets}" var="source">
                                                <option value="${source.id}" <c:if test="${accountid==source.id}">selected</c:if>>${source.name}</option>
                                            </c:forEach>
                                        </form:select>
                                        <div class="text-danger">
                                            <form:errors path="source_wallet" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                            </td></tr>
                            <tr><td>
                                <div class="form-group col-md-12">
                                    <label class="col-md-3 control-lable" for="wallet_number">Numer rachunku odbiorcy:</label>
                                    <div class="col-md-7">
                                        <form:input type="text" path="wallet_number" id="wallet_number" cssClass="form-control" required="required" />
                                        <div class="text-danger">
                                            <form:errors path="wallet_number" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                            </td></tr>
                            <tr><td>
                                <div class="form-group col-md-12">
                                    <label class="col-md-3 control-lable" for="name">Odbiorca:</label>
                                    <div class="col-md-7">
                                        <form:input type="text" path="name" id="name" cssClass="form-control" required="required" />
                                        <div class="text-danger">
                                            <form:errors path="name" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                            </td></tr>
                            <tr><td>
                                <div class="form-group col-md-12">
                                    <label class="col-md-3 control-lable" for="address1">Adres:</label>
                                    <div class="col-md-7">
                                        <form:input type="text" path="address1" id="address1" cssClass="form-control" />
                                        <div class="text-danger">
                                            <form:errors path="address1" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                            </td></tr>
                            <tr><td>
                                <div class="form-group col-md-12">
                                    <label class="col-md-3 control-lable" for="address2">Dalszy adres:</label>
                                    <div class="col-md-7">
                                        <form:input type="text" path="address2" id="address2" cssClass="form-control" />
                                        <div class="text-danger">
                                            <form:errors path="address2" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                            </td></tr>
                            <tr><td>
                                <div class="form-group col-md-12">
                                    <label class="col-md-3 control-lable" for="title">Tytuł przelewu:</label>
                                    <div class="col-md-7">
                                        <form:input type="text" path="title" id="title" cssClass="form-control" required="required" />
                                        <div class="text-danger">
                                            <form:errors path="title" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                            </td></tr>
                            <tr><td>
                                <div class="form-group col-md-12">
                                    <label class="col-md-3 control-lable" for="value">Kwota przelewu:</label>
                                    <div class="col-md-7">
                                        <form:input type="text" path="value" id="value" cssClass="form-control" required="required" />
                                        <div class="text-danger">
                                            <form:errors path="value" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                            </td></tr>
                            <tr><td>
                                <div class="form-actions pull-right">
                                    <input type="submit" value="Wykonaj przelew" class="btn btn-primary"/> albo <a href="<c:url value='/dashboard/bankaccount/list' />">Anuluj</a>
                                </div>
                            </td></tr>
                        </table>
                    </form:form>
                </div>
                <div role="tabpanel" class="tab-pane" id="profile">
                    <form:form method="POST" modelAttribute="transaction" mehod="post" class="form-horizontal">
                        <table class="table table-striped">
                            <tr><td>
                                <div class="form-group col-md-12">
                                    <label class="col-md-3 control-lable" for="source_wallet">Nazwa szablonu:</label>
                                    <div class="col-md-7">
                                        <select id="source_wallet" class="form-control">
                                            <option value="BRAK" selected disabled>Wybierz szablon</option>
                                            <c:forEach items="${savetransactions}" var="save">
                                                <option class="savecheck" value="${save.id}">${save.transactionname}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </td></tr>
                            </table>

                    <table class="table table-striped" id="formtrans">

                            <tr><td>
                                <div class="form-group col-md-12">
                                    <label class="col-md-3 control-lable" for="source_wallet2">Rachunek źródłowy:</label>
                                    <div class="col-md-7">
                                        <form:select path="source_wallet" id="source_wallet2" class="form-control">
                                            <c:forEach items="${source_wallets}" var="source">
                                                <option value="${source.id}" <c:if test="${accountid==source.id}">selected</c:if>>${source.name}</option>
                                            </c:forEach>
                                        </form:select>
                                        <div class="text-danger">
                                            <form:errors path="source_wallet" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                            </td></tr>
                            <tr><td>
                                <div class="form-group col-md-12">
                                    <label class="col-md-3 control-lable" for="wallet_number2">Numer rachunku odbiorcy:</label>
                                    <div class="col-md-7">
                                        <form:input type="text" path="wallet_number" id="wallet_number2" cssClass="form-control" required="required" readonly="readonly" />
                                        <div class="text-danger">
                                            <form:errors path="wallet_number" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                            </td></tr>
                            <tr><td>
                                <div class="form-group col-md-12">
                                    <label class="col-md-3 control-lable" for="name2">Odbiorca:</label>
                                    <div class="col-md-7">
                                        <form:input type="text" path="name" id="name2" cssClass="form-control" required="required" readonly="readonly" />
                                        <div class="text-danger">
                                            <form:errors path="name" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                            </td></tr>
                            <tr><td>
                                <div class="form-group col-md-12">
                                    <label class="col-md-3 control-lable" for="address12">Adres:</label>
                                    <div class="col-md-7">
                                        <form:input type="text" path="address1" id="address12" cssClass="form-control" readonly="readonly" />
                                        <div class="text-danger">
                                            <form:errors path="address1" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                            </td></tr>
                            <tr><td>
                                <div class="form-group col-md-12">
                                    <label class="col-md-3 control-lable" for="address22">Dalszy adres:</label>
                                    <div class="col-md-7">
                                        <form:input type="text" path="address2" id="address22" cssClass="form-control" readonly="readonly" />
                                        <div class="text-danger">
                                            <form:errors path="address2" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                            </td></tr>
                            <tr><td>
                                <div class="form-group col-md-12">
                                    <label class="col-md-3 control-lable" for="title2">Tytuł przelewu:</label>
                                    <div class="col-md-7">
                                        <form:input type="text" path="title" id="title2" cssClass="form-control" required="required" />
                                        <div class="text-danger">
                                            <form:errors path="title" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                            </td></tr>
                            <tr><td>
                                <div class="form-group col-md-12">
                                    <label class="col-md-3 control-lable" for="value2">Kwota przelewu:</label>
                                    <div class="col-md-7">
                                        <form:input type="text" path="value" id="value2" cssClass="form-control" required="required" />
                                        <div class="text-danger">
                                            <form:errors path="value" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                            </td></tr>
                            <tr><td>
                                <div class="form-actions pull-right">
                                    <input type="submit" value="Wykonaj przelew" class="btn btn-primary"/> albo <a href="<c:url value='/dashboard/bankaccount/list' />">Anuluj</a>
                                </div>
                            </td></tr>
                        </table>
                    </form:form>
                </div>
            </div>



            <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
            <script src="<c:url value='/static/js/jquery.min.js' />"></script>
            <!-- Include all compiled plugins (below), or include individual files as needed -->
            <script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
            <script src="<c:url value='/static/js/skrypty.js' />"></script>
</body>
</html>