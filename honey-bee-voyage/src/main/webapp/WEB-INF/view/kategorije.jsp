<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Honey Bee Voyage</title>
<link href='<spring:url value="/resources/css/style.css"/>'
	rel="stylesheet" />
<link rel='stylesheet'
	href='webjars/bootstrap/4.1.3/css/bootstrap.min.css'>
<link rel='stylesheet'
	href='webjars/font-awesome/5.14.0/css/font-awesome.css'>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.1/css/all.css"
	integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP"
	crossorigin="anonymous">
<style>
@import url('https://fonts.googleapis.com/css?family=Anton');

@import url('https://fonts.googleapis.com/css?family=Lobster');

@import
	url('https://fonts.googleapis.com/css?family=Saira+Extra+Condensed');

@import url('https://fonts.googleapis.com/css?family=Roboto');
@import url('https://fonts.googleapis.com/css2?family=Rajdhani:wght@300&display=swap');
@import
	url('https://fonts.googleapis.com/css2?family=Sacramento&display=swap');
</style>

</head>
<body class="pozadina">

<div class="container-fluid">

	<div class="row">
		<div class="col-md-4">
			<a href="${pageContext.request.contextPath }/"><img src="images/logo1.png" alt="Logo" class="img-fluid" /></a>
		</div>
		<div class="col-md-3 offset-md-5">
			<security:authorize access="isAuthenticated()">
				<br>
				<a href="${pageContext.request.contextPath }/profil" class="inline-elements"><security:authentication property="principal.username"/></a>
				|
				<form:form action="${pageContext.request.contextPath}/logout"
					method="POST" class="inline-elements">
					<input type="submit" class="logout-submit-btn" value="Logout" />
				</form:form>
				
			</security:authorize>
			<security:authorize access="isAnonymous()">
				<br>
				<a href="${pageContext.request.contextPath }/showLoginPage">Login</a>
				|
				<a href="${pageContext.request.contextPath }/registracija">Register</a>
			</security:authorize>
		</div>
	</div>

	<nav class="navbar navbar-expand-lg navbar-light text-center">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav ml-auto mx-auto">
				<c:forEach var="temp" items="${kategorije}">
					<a class="nav-item nav-link navigacijaPozadina"
						href="${pageContext.request.contextPath }/kategorije-${temp.id_kategorije}">${temp.naziv}</a>
				</c:forEach>
			</div>
		</div>
	</nav>

	<div class="row">
		<div class="col-md-12 centar">
			<p>${poruka}</p>
		</div>
	</div>
	<br>
		<c:forEach var="temp" items="${aranzmani}">
			<div class="row">
				<div class="col-md-12 form-background">
				<br>
					<c:forEach var="lok" items="${temp.getDestinacija()}">
						<h5 class="anton-font">
							<i class="fas fa-map-marker-alt ar-icons"></i> ${lok.lokacija}
						</h5>
					</c:forEach>
					<h1 class="anton-font">${temp.getAranzman().naziv}</h1>
					<c:choose>
						<c:when test="${temp.tipPrevoza.equals('Autobus')}">
							<p>${temp.tipPrevoza }
								<i class="fas fa-bus ar-icons"></i>
							</p>
						</c:when>
						<c:when test="${temp.tipPrevoza.equals('Avion')}">
							<p>${temp.tipPrevoza }
								<i class="fas fa-plane ar-icons"></i>
							</p>
						</c:when>
					</c:choose>
					<p>${temp.cena} RSD</p>
					<div class="centar">
						<a
							href="${pageContext.request.contextPath }/aranzmani-${temp.getAranzman().idAranzmana}"><input
							type="button" class="dugme" value="Saznaj više" /></a>
					</div>
					<br>
				</div>
			</div>
			<br>
		</c:forEach>
		<br>
		<br>
		<br>
		<br>

		<%-- <security:authorize access="isAuthenticated()">
			<p>
				User:
				<security:authentication property="principal.username" />
				<br> <br> Roles:
				<security:authentication property="principal.authorities" />
				<!-- Ispisuje niz uloga -->
			</p>
			<hr>
			<!-- Link za admine -->

			<security:authorize access="hasRole('ADMIN')">
				<p>
					<a href="${pageContext.request.contextPath }/admin">Admin Link</a>
				</p>
			</security:authorize>
		</security:authorize> --%>

		<div class="row footerP">
			<div class="col-md-6 centar">
				<p class="kopirajt footFont">Honey Bee &#x24B8; Since 2020.</p>
			</div>
			<div class="col-md-6 centar">
				<a href="https://www.instagram.com/autostoper_sa_sekirom/"
					target="_blank"><i class="fab fa-instagram footIkone"></i></a> <a
					href="mailto:emarmarac22@gmail.com" role="button"><i
					class="fas fa-envelope footIkone"></i></a> <a
					href="https://twitter.com/EMarmarac" target="_blank"><i
					class="fab fa-twitter footIkone"></i></a> <a
					href="https://linkedin.com/in/emina-marmarac" target="_blank"><i
					class="fab fa-linkedin footIkone"></i></a>
			</div>
		</div>


	</div>


<script type="text/javascript" src="webjars/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
