<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<li class="active">Panel logowania</li>
	</ol>
</header>

<c:url var="loginUrl" value="/login" />
<form action="${loginUrl}" method="post" class="form-horizontal" id="login-form">
	<div class="col-md-6 col-center-block">

		<c:if test="${param.error != null}">
		<div class="alert alert-danger">
			<p>Nieprawidłowe dane logowania. Spróbuj ponownie.</p>
		</div>
		</c:if>
		<c:if test="${param.logout != null}">
		<div class="alert alert-success">
			<p>Zostałeś wylogowany.</p>
		</div>
		</c:if>

		<div class="panel panel-default" id="log1">
			<div class="panel-heading"><h4>Podaj swój identyfikator</h4></div>
			<div class="panel-body">
				<div class="form-group">
					<div class="col-lg-12">
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-envelope"></span></span>
							<input type="text" class="form-control input-lg" name="ssoId" id="inputEmail" placeholder="Identyfikator">

						</div>
						<div class="container-fluid"><small class="text-danger" id="loginError">Podaj poprawny adres email.</small></div>

					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-10 col-lg-offset-2">
						<button type="submit" class="btn btn-success pull-right" data-loading-text="Przetważanie...">Przejdź dalej</button>
					</div>
				</div>
</div>
</div>
<div class="panel panel-default" id="log2">
	<div class="panel-heading"><h4>Podaj swoje hasło</h4></div>
	<div class="panel-body">
		<div class="form-group">
			<div class="col-lg-12">
				<div class="media">
					<div class="media-left">
						<img class="media-object thumbnail img-responsive" id="image-token" src="" />
						<!--<img class="media-object thumbnail" src="<c:url value='/static/images/cat.jpg' />" alt="...">-->
					</div>
				</div>
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-12">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1"><div class="glyphicon glyphicon-lock"></div></span>
					<input type="password" class="form-control input-lg" name="password" id="inputPassword" placeholder="Hasło">
				</div>
				<div class="container-fluid"><small class="text-danger" id="passwordError">Podaj swoje tajne hasło.</small></div>
			</div>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
		<div class="form-group">
			<div class="col-lg-10 col-lg-offset-2">
				<div class="btn-toolbar">
					<button type="submit" class="btn btn-success pull-right">Przejdź dalej</button>
					<button type="reset" id="resetLogin" class="btn btn-default pull-right">Popraw</button>
				</div>
			</div>
		</div>
		</form>
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