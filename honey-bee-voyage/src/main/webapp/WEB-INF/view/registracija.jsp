<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Honey Bee Voyage</title>
<link href='<spring:url value="/resources/css/style.css"/>' rel="stylesheet" />
<link rel='stylesheet' href='webjars/bootstrap/4.1.3/css/bootstrap.min.css'>
	<style>
		   @import url('https://fonts.googleapis.com/css?family=Anton');
		   @import url('https://fonts.googleapis.com/css?family=Lobster');
		   @import url('https://fonts.googleapis.com/css?family=Saira+Extra+Condensed');
		   @import url('https://fonts.googleapis.com/css?family=Roboto');
		   @import url('https://fonts.googleapis.com/css2?family=Sacramento&display=swap');
		   @import url('https://fonts.googleapis.com/css2?family=Rajdhani:wght@300&display=swap');
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
				<form:form class="well form-horizontal"
					action="${pageContext.request.contextPath }/processRegistration"
					method="POST" modelAttribute="korisnik">
					<p>
						Korisničko ime: <input name="korisnickoIme" class="form-control"
							type="text" path="korisnickoIme">
					</p>

					<p>
						Lozinka: <input name="lozinka" class="form-control"
							type="password" path="lozinka">
					</p>
					<div class="centar">
						<input type="submit" class="dugme" value="Registruj se">
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