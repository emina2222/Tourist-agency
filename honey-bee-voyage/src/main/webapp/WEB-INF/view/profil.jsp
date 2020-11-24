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
<link rel='stylesheet' href='webjars/font-awesome/5.14.0/css/font-awesome.css'>
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
			<a href="${pageContext.request.contextPath }/"><img src="images/logo1.png" alt="Logo" class="img-fluid" /></a>
		</div>
		<div class="col-md-3 offset-md-5">
			<br> 
			<security:authorize access="isAuthenticated()">
			<a href="${pageContext.request.contextPath }/profil"><security:authentication
					property="principal.username" /></a> |
			<form:form action="${pageContext.request.contextPath}/logout"
				method="POST" class="inline-elements">
				<input type="submit" class="logout-submit-btn"
					value="Logout" />
			</form:form>
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
					<a class="nav-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/kategorije-${temp.id_kategorije}">${temp.naziv}</a>
				</c:forEach>
			</div>
		</div>
	</nav>

<br>
<h2>Profil</h2>

		<div class="row">
			<div class="col-md-6">
				<p>Ime</p>
				<p class="stavke2">${putnik.ime}</p>

				<p>Prezime</p>
				<p class="stavke2">${putnik.prezime}</p>

				<p>Email<p>
				<p class="stavke2">${putnik.email}</p>

				<p>Broj telefona</p>
				<p class="stavke2">${putnik.brojTelefona}</p>
				
			</div>
			<div class="col-md-6">
			<h1>Moje rezervacije</h1>
				<p>${poruka}</p>

				<c:forEach var="temp" items="${rez}">
					<form:form
						action="${pageContext.request.contextPath }/otkaziRezervaciju"
						method="POST">
						<input type="hidden" name="idAranzmana" value="${temp.getId_aranzmana()}"/>
						<input type="hidden" name="idPutnika" value="${temp.getId_putnika()}"/>
						<input type="hidden" name="idSmestaja" value="${temp.getId_smestaja()}"/>
						<input type="hidden" name="idPonude" value="${temp.getId_ponude()}"/>
						<input type="hidden" name="idRezervacije" value="${temp.getId_rezervacije()}"/>
						<input type="hidden" name="grupa" value="${temp.getGrupa()}"/>
						
						<button class="btn btn-block atrakcija-btn" type="button"
							data-toggle="collapse"
							data-target="#collapseExample-${temp.getId_aranzmana()}"
							aria-expanded="false" aria-controls="collapseExample"
							style="display: block;">${temp.getNaziv_aranzmana()}</button>

						<div class="collapse" id="collapseExample-${temp.getId_aranzmana()}">
							<c:if test="${not empty temp.getNaziv_smestaja()}">
								<div class="card card-body">Smestaj: ${temp.getNaziv_smestaja()}</div>
							</c:if>
							<div class="card card-body">Odrasli:
								${temp.getBroj_putnika()}</div>
							<div class="card card-body">Deca:
								${temp.getBroj_dece()}</div>
							<div class="card card-body">Cena: ${temp.getCena()}</div>
							<c:if
								test="${temp.getGrupa()==0 or temp.getGrupa()==1}">
								<div class="card card-body">Period:
									${temp.getDatumPocetkaAr()} - ${temp.getDatumKrajaAr()}</div>
							</c:if>
							<c:if
								test="${temp.getGrupa()==2}">
								<div class="card card-body">Period:
									${temp.getDatum_polaska()} - ${temp.getDatum_dolaska()}</div>
							</c:if>
							<div class="card card-body">
								<input type="submit" class="dugme-cancel"
									value="Otkaži rezervaciju" />
							</div>
						</div>
					</form:form>

				</c:forEach>
			</div>
		</div>


	
	<div class="row footerP">
        <div class="col-md-6 centar">
            <p class="kopirajt footFont">Honey Bee &#x24B8; Since 2020.</p>
        </div>
        <div class="col-md-6 centar">
            <a href="https://www.instagram.com/autostoper_sa_sekirom/" target="_blank"><i class="fab fa-instagram footIkone"></i></a>
            <a href="mailto:emarmarac22@gmail.com" role="button"><i class="fas fa-envelope footIkone"></i></a>
            <a href="https://twitter.com/EMarmarac" target="_blank"><i class="fab fa-twitter footIkone"></i></a>
            <a href="https://linkedin.com/in/emina-marmarac" target="_blank"><i class="fab fa-linkedin footIkone"></i></a>
        </div>
    </div>
</div>

<script type="text/javascript" src="webjars/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
