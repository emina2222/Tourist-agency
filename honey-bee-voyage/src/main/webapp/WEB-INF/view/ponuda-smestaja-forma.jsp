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
							</div></li>
						<li class="nav-item dropdown"><a
							class="nav-item navigacijaPozadina nav-link dropdown-toggle" href="#" id="5" role="button"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								Prevoz </a>
							<div class="dropdown-menu" aria-labelledby="5">
								<a class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/prevoznici">Pregled prevoza</a>
								<a class="dropdown-item nav-link navigacijaPozadina" href="${pageContext.request.contextPath }/ponudePrevoznika">Ponude</a>
							</div></li>
					</ul>
				</div>
			</nav>
		</security:authorize>
		<br>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<form:form class="well form-horizontal"
					action="${pageContext.request.contextPath }/sacuvajFormuPonudeSmestaja" method="POST">
					<input type="hidden" name="idAranzmana" value="${idAranzmana}"/>
					
					<p>
						Naziv *: <input name="naziv" class="form-control" type="text" required/>
					</p>

					<p>
						Datum od (dd/MM/yyyy) *: <input name="datumOd" class="form-control" type="text" required/>
					</p>
					
					<p>
						Datum do (dd/MM/yyyy) *: <input name="datumDo" class="form-control" type="text" required/>
					</p>

					<div class="centar">
						<input type="submit" class="dugme" value="Potvrdi" />
					</div>
					
				</form:form>

			</div>
			<div class="col-md-3"></div>
		</div>

	</div>
	<script>
	
	document.getElementById("prevoznik").addEventListener("change", nadji);
	
	function nadji(){
		var el=document.getElementById("prevoznik").value;
		$.ajax({				
			method : "GET",
			url : "${pageContext.request.contextPath}/nadji-vozila-prevoznika",
			data : {
				"prevoznik":el //prosledjuje se idPrevoznika iz prvog selecta
			},
	    	success : function(data) {
	    		let odgovor=JSON.parse(data);
	    		prikaziSelect(odgovor); //poziva se funkcija koja ce izlistati sva vozila za tog prevoznika
	    		console.log(odgovor);
			}
		});
	}
	
		
		function prikaziSelect(lista) {

			document.getElementById('vozilo').innerHTML = ''; //brisanje sadrzaja drugog selecta za vozila

			for (var index = 0, len = lista.length; index < len; index++) { //iteracija kroz listu
				var newoption = document.createElement('option'); //kreira se option element 
				newoption.value = lista[index].idVozila;//ovo je nevidljivi element, value za option
				newoption.innerHTML = lista[index].naziv; //naziv vozila ce biti vidljiv u optionu
				document.getElementById('vozilo').appendChild(newoption); //option element se postavlja kao child element u drugom selectu
			}
		}
	</script>
	
<script type="text/javascript" src="webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
	
</body>
</html>