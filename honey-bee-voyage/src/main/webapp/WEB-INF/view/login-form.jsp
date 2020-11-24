<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Honey Bee Voyage</title>
<link href='<spring:url value="/resources/css/style.css"/>' rel="stylesheet" />
<link rel='stylesheet' href='webjars/bootstrap/4.1.3/css/bootstrap.min.css'>
<link rel='stylesheet' href='webjars/font-awesome/5.14.0/css/font-awesome.css'>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
	integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP"
	crossorigin="anonymous">
<style>
@import url('https://fonts.googleapis.com/css?family=Anton');

@import url('https://fonts.googleapis.com/css?family=Lobster');
@import url('https://fonts.googleapis.com/css2?family=Rajdhani:wght@300&display=swap');
@import
	url('https://fonts.googleapis.com/css?family=Saira+Extra+Condensed');

@import url('https://fonts.googleapis.com/css?family=Roboto');

@import
	url('https://fonts.googleapis.com/css2?family=Sacramento&display=swap');
</style>
</head>
<body class="pozadina">

<div class="container-fluid">

	<div class="row">
		<div class="col-md-4">
			<a href="${pageContext.request.contextPath }/"><img
				src="images/logo1.png" alt="Logo" class="img-fluid" /></a>
		</div>
		<div class="col-md-8"></div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<form:form
				action="${pageContext.request.contextPath}/autentifikacija"
				method="POST">

				<!-- Provera login error -->

				<c:if test="${param.error != null}">

					<i class="failed">Uneli ste neispravne podatke.</i>

				</c:if>

				<!-- Name moraju biti username i password, inace ne radi -->
				<p>
					Korisničko ime: 
					<br>
					<input type="text" class="form-control" name="korisnicko_ime" />
				</p>

				<p>
					Lozinka:
					<br> 
					<input type="password" class="form-control" name="lozinka" />
				</p>

					<div class="centar">
						<input type="submit" class="dugme" value="Uloguj se" />
					</div>
				</form:form>
		</div>
		<div class="col-md-3"></div>
	</div>

</div>

	<script type="text/javascript" src="webjars/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>