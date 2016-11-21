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
				<div class="navbar-form navbar-right">
					<a href="<c:url value="/logout" />" class="btn btn-warning">Wyloguj się</a>
				</div>
			</div>
		</div>
	</nav>
	<ol class="breadcrumb">
		<li class="active bold">Bankowość internetowa</li>
		<li class="active"><a href="<c:url value='/admin/dashboard/' />">Panel administratora</a></li>
		<li class="active"><c:choose>
			<c:when test="${edit}">
				Edycja użytkownika
			</c:when>
			<c:otherwise>
				Dodawanie użytkownika
			</c:otherwise>
		</c:choose></li>
	</ol>
</header>
<div class="container-fluid">
		<%@include file="../../authheader.jsp" %>
	<div class="panel panel-default">
		<div class="panel-heading"><h4>Dodaj użytkownika</h4></div>
		<tr class="panel-body">
	 	<form:form method="POST" modelAttribute="user" class="form-horizontal">
			<form:input type="hidden" path="id" id="id"/>
			<table class="table">
			<tr><td>
				<div class="form-group">
					<label class="col-md-3 control-label" for="firstName">Imię</label>
					<div class="col-md-7">
						<form:input type="text" path="firstName" id="firstName" class="form-control"/>
						<div class="text-danger">
							<form:errors path="firstName" class="help-inline"/>
						</div>
					</div>
				</div>
			</td></tr>

			<tr><td>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="lastName">Nazwisko</label>
					<div class="col-md-7">
						<form:input type="text" path="lastName" id="lastName" class="form-control" />
						<div class="text-danger">
							<form:errors path="lastName" class="help-inline"/>
						</div>
					</div>
				</div>
			</td></tr>

			<tr><td>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="ssoId">Identyfikator</label>
					<div class="col-md-7">
						<c:choose>
							<c:when test="${edit}">
								<form:input type="text" path="ssoId" id="ssoId" class="form-control" disabled="true"/>
							</c:when>
							<c:otherwise>
								<form:input type="text" path="ssoId" id="ssoId" class="form-control" />
								<div class="text-danger">
									<form:errors path="ssoId" class="help-inline"/>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</td></tr>

			<tr><td>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="password">Hasło</label>
					<div class="col-md-7">
						<form:input type="password" path="password" id="password" class="form-control" />
						<div class="text-danger">
							<form:errors path="password" class="help-inline"/>
						</div>
					</div>
				</div>
			</td></tr>

			<tr><td>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="email">Email</label>
					<div class="col-md-7">
						<form:input type="text" path="email" id="email" class="form-control" />
						<div class="text-danger">
							<form:errors path="email" class="help-inline"/>
						</div>
					</div>
				</div>
			</td></tr>


				<tr><td>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="city">Adres</label>
					<div class="col-md-7">
						<form:input type="text" path="city" id="city" class="form-control" />
						<div class="text-danger">
							<form:errors path="city" class="help-inline"/>
						</div>
					</div>
				</div>
			</td></tr>

				<tr><td>
					<div class="form-group col-md-12">
						<label class="col-md-3 control-label" for="postalcode">Kod pocztowy</label>
						<div class="col-md-7">
							<form:input type="text" path="postalcode" id="postalcode" class="form-control" />
							<div class="text-danger">
								<form:errors path="postalcode" class="help-inline"/>
							</div>
						</div>
					</div>
				</td></tr>

				<tr><td>
					<div class="form-group col-md-12">
						<label class="col-md-3 control-label" for="phone">Telefon</label>
						<div class="col-md-7">
							<form:input type="text" path="phone" id="phone" class="form-control" />
							<div class="text-danger">
								<form:errors path="phone" class="help-inline"/>
							</div>
						</div>
					</div>
				</td></tr>

				<tr><td>
					<div class="form-group col-md-12">
						<label class="col-md-3 control-label" for="image_token">Token obrazkowy</label>
						<div class="col-md-7">
								<form:select  path="image_token" class="form-control">
									<c:forEach items="${imagetokens}" var="img">
										<c:choose>
											<c:when test="${user.image_token.id ==img.id}">
												<option value="${img.id}" selected="selected">${img.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${img.id}">${img.name}</option>
											</c:otherwise>
										</c:choose>

									</c:forEach>
								</form:select>

							<div class="text-danger">
								<form:errors path="image_token" class="help-inline"/>
							</div>
						</div>
					</div>
				</td></tr>

			<tr><td>
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="userProfiles">Typ konta</label>
					<div class="col-md-7">
						<form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control" />
						<div class="text-danger">
							<form:errors path="userProfiles" class="help-inline"/>
						</div>
					</div>
				</div>
			</td></tr>
	
			<tr><td>
				<div class="form-actions pull-right">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Aktualizuj" class="btn btn-primary"/> albo <a href="<c:url value='/admin/users/list' />">Anuluj</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Utwórz nowe konto" class="btn btn-primary"/> albo <a href="<c:url value='/admin/users/list' />">Anuluj</a>
						</c:otherwise>
					</c:choose>
				</div>
			</td></tr>
			</table>
			</div>
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