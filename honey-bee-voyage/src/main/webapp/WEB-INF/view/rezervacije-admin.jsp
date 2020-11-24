<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
@import url('https://fonts.googleapis.com/css2?family=Rajdhani:wght@300&display=swap');

@import url('https://fonts.googleapis.com/css?family=Lobster');

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
			<div class="col-md-3 offset-md-5">
				<security:authorize access="isAuthenticated()">
					<br>
					<a href="${pageContext.request.contextPath }/profil"
						class="inline-elements"><security:authentication
							property="principal.username" /></a>
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
	
	<security:authorize access="hasRole('ADMIN')">
			<nav class="navbar navbar-expand-lg navbar-light text-center">
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav ml-auto mx-auto">
						<li class="nav-item dropdown"><a
							class="nav-item navigacijaPozadina nav-link dropdown-toggle" href="#" id="1" role="button"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Klijenti </a>
							<div class="dropdown-menu" aria-labelledby="1">
								<a class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/klijenti">Pregled klijenata</a> <a
									class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/pregledRezervacija">Pregled rezervacija</a>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-item navigacijaPozadina nav-link dropdown-toggle" href="#" id="2" role="button"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Zaposleni </a>
							<div class="dropdown-menu" aria-labelledby="2">
								<a class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/zaposleni">Pregled zaposlenih</a> 
								<a class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/turisticki-vodici">Turistički vodiči</a>
								<a class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/angazovanje-vodica">Angažovanje vodiča</a>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-item navigacijaPozadina nav-link dropdown-toggle" href="#" id="3" role="button"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Aranžmani </a>
							<div class="dropdown-menu" aria-labelledby="3">
								<a class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/kategorije">Kategorije</a>
								<a class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/aranzmani">Pregled aranžmana</a>
								<a class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/destinacije">Destinacije</a>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-item navigacijaPozadina nav-link dropdown-toggle" href="#" id="4" role="button"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Smeštaj </a>
							<div class="dropdown-menu" aria-labelledby="4">
								<a class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/smestaj">Pregled smeštaja</a>
								<a class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/smestajne-jedinice">Smeštajne jedinice</a>
								<a class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/ponudeSmestaja">Ponude</a>
								<a class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/stavke-ponude">Stavke ponude</a>
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-item navigacijaPozadina nav-link dropdown-toggle" href="#" id="5" role="button"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Prevoz </a>
							<div class="dropdown-menu" aria-labelledby="5">
								<a class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/prevoznici">Pregled prevoza</a>
								<a class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/ponudePrevoznika">Ponude</a>
								<a class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/vozila">Prevozna sredstva</a>
							</div></li>
					</ul>
				</div>
			</nav>
		</security:authorize>
		
		<br>
		
		<div class="col-md-12">
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">Id</th>
						<th scope="col">Ime</th>
						<th scope="col">Prezime</th>
						<th scope="col">Aranzman</th>
						<th scope="col">Datum od</th>
						<th scope="col">Datum do</th>
						<th scope="col">Cena</th>
						<th scope="col">Smestaj</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="temp" items="${rez}">
					<tr>
						<td>${temp.getId_rezervacije()}</td>
						<td>${temp.getIme()}</td>
						<td>${temp.getPrezime()}</td>
						<td>${temp.getNaziv_aranzmana()}</td>
							<c:choose>
								<c:when
									test="${temp.getGrupa()==2}">
									<td>${temp.getDatum_polaska()}</td>
									<td>${temp.getDatum_dolaska()}</td>
								</c:when>
								<c:when
									test="${temp.getGrupa()==0 || temp.getGrupa()==1}">
									<td>${temp.getDatumPocetkaAr()}</td>
									<td>${temp.getDatumKrajaAr()}</td>
								</c:when>
							</c:choose>
							<td>${temp.getCena()}</td>
							<c:choose>
								<c:when test="${not empty temp.getNaziv_smestaja()}">
									<td>${temp.getNaziv_smestaja()}</td>
								</c:when>
								<c:otherwise>
									<td>-</td>
								</c:otherwise>
							</c:choose>
						</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>

	</div>
	

<script type="text/javascript" src="webjars/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
