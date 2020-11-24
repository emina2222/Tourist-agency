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
	<br>
	<section id="sekcija">
	<div class="row">
		<div class="col-md-12 anton-font">
		<h1 id="nazivAranzmana">${aranzman.naziv}</h1>
		<input type="hidden" value="${aranzman.idAranzmana}" id="aranzman"/>
		</div>
	</div>
	
	<br>
	
	<div class="row">
		<div class="col-md-6">
			<p>${programPutovanja}</p>
		</div>
		<div class="col-md-6">
			<img src="images/${nazivSlike}" alt="${nazivSlike}" class="img-fluid"/>
		</div>
	</div>
	
	<br>

			<c:choose>
				<c:when
					test="${kat==2}">
					<c:forEach var="temp" items="${full}">
						<br>
						<div class="row" id="${temp.getSmestaj().getIdSmestaja()}">
							<div class="col-md-3 smestaj"
								id="${temp.getSmestaj().getIdSmestaja()}">
								<p id="${temp.getSmestaj().getIdSmestaja()}">${temp.getSmestaj().getNaziv()}</p>
							</div>
							<div class="col-md-9">
								<div class="row">
									<div class="col-md-3 kreveti">
										<p>Tip smeštaja</p>
									</div>
									<c:forEach var="tem" items="${temp.getPonude()}">
										<div class="col-md-3 bela-pozadina" id="${tem.getId()}">
											<p>
												<fmt:formatDate pattern="dd/MM"
													value="${tem.getDatumPocetka()}" />
												-
												<fmt:formatDate pattern="dd/MM"
													value="${tem.getDatumZavrsetka()}" />
											</p>
										</div>
									</c:forEach>
								</div>
								<c:forEach var="var" items="${temp.getTp()}">
									<div class="row">
										<div class="col-md-3 kreveti" id="${var.getBrojKreveta()}">
											<p>1/${var.getBrojKreveta()}</p>
										</div>
										<c:forEach var="cc" items="${var.getCene()}">
											<div class="col-md-3" onclick="nadji(this)">
												<c:choose>
													<c:when test="${cc > 0}">
														<p id="rezDugme" class="dugmence">
															<span id="a">${cc}</span><span id="b">Rezerviši</span>
														</p>
													</c:when>
													<c:when test="${cc==0}">
														<p id="popunjeno">X</p>
													</c:when>
												</c:choose>
											</div>
										</c:forEach>
									</div>
								</c:forEach>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:when test="${kat==0}">
					<div class="row">
						<div class="col-md-12">
							<form:form
								action="${pageContext.request.contextPath}/rezervacija-izleta-forma"
								method="GET">
								<input type="hidden" name="idAranzmana"
									value="${aranzman.idAranzmana}" />
								<input type="hidden" name="cenaIzleta" value="${cenaIzleta}" />
								<input type="hidden" name="datumIzleta" value="${datumIzleta}" />
								<h4>Cena: ${cenaIzleta} RSD</h4>
								<h4>Datum polaska: ${datumIzleta}</h4>
								<br>
								<div class="row">
									<div class="col-md-12 centar">
									<c:choose>
										<c:when test="${aranzman.kapacitet>0}">
											<input type="submit" class="dugme" value="Rezerviši" />
										</c:when>
										<c:when test="${aranzman.kapacitet==0}">
											<input type="submit" class="popunjeno-btn" value="Popunjeno" disabled/>
										</c:when>
									</c:choose>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="row">
						<div class="col-md-12">
							<form:form
								action="${pageContext.request.contextPath}/rezervacija-ture-forma"
								method="GET">
								<input type="hidden" name="idAranzmana"
									value="${aranzman.idAranzmana}" />
								<input type="hidden" name="cenaTure" value="${cenaTure}" />
								<input type="hidden" name="datumiTure" value="${datumiTure}" />
								<h4>Cena: ${cenaTure} RSD</h4>
								<h4>Period: ${datumiTure}</h4>
								<br>
								<div class="row">
									<div class="col-md-12 centar">
										<c:choose>
										<c:when test="${aranzman.kapacitet>0}">
											<input type="submit" class="dugme" value="Rezerviši" />
										</c:when>
										<c:when test="${aranzman.kapacitet==0}">
											<input type="submit" class="popunjeno-btn" value="Popunjeno" disabled/>
										</c:when>
									</c:choose>
									</div>
								</div>
							</form:form>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
			<br>		
		<div class="row">
			<div class="col-md-6">
			<h1>Znamenitosti i atrakcije</h1>
					<c:forEach var="temp" items="${atrakcije}">
						<button class="btn btn-block atrakcija-btn" type="button"
							data-toggle="collapse" data-target="#collapseExample-${temp.getTipAtrakcije().idTipaAtrakcije}"
							aria-expanded="false" aria-controls="collapseExample" style="display:block;">
							${temp.getTipAtrakcije().naziv}</button>

						<div class="collapse" id="collapseExample-${temp.getTipAtrakcije().idTipaAtrakcije}">
							<c:forEach var="atr" items="${temp.getAtrakcije()}">
								<div class="card card-body">${atr.naziv}</div>
							</c:forEach>
						</div>
					</c:forEach>
			</div>
			<div class="col-md-6">
				<h1>Fakultativni izleti</h1>
				<p>${poruka}</p>
				<c:forEach var="temp" items="${fakIzleti}">
					<p><i class="fas fa-angle-double-right"></i> ${temp.naziv} (${temp.cena} RSD)</p>
				</c:forEach>
			</div>
		</div>

	</section>


<br>
<br>
<br>
<br>
<br>
<br>

<%-- 	<security:authorize access="isAuthenticated()">
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
            <a href="https://www.instagram.com/autostoper_sa_sekirom/" target="_blank"><i class="fab fa-instagram footIkone"></i></a>
            <a href="mailto:emarmarac22@gmail.com" role="button"><i class="fas fa-envelope footIkone"></i></a>
            <a href="https://twitter.com/EMarmarac" target="_blank"><i class="fab fa-twitter footIkone"></i></a>
            <a href="https://linkedin.com/in/emina-marmarac" target="_blank"><i class="fab fa-linkedin footIkone"></i></a>
        </div>
    </div>
</div>	
	
	
	<script>
	
   	 
   	 function nadji(el){
   		 el.style.color='red';
   		 
   		 const ar=document.getElementById('aranzman').value; //dohvatanje id aranzmana iz hidden input polja
   		 
   		 var cena=el.textContent; //uzimanje cene kao string
   		 
   		 var siblings=el.parentNode.children; //trazi ugnjezdene divove u parent divu
   		 
   		 var krevet=siblings[0]; //uzima prvi sibling (div u kome je upisan broj kreveta)
   		 
   	  	 var index = [].indexOf.call (el.parentNode.children, el); //trazi index kliknutog elementa, da bi ga poklopio sa indeksom ponude 
   	  	 //zato sto su u istoj koloni
   	  	 var dads=el.parentNode.parentNode.children //dohvatanje diva koji je parent divu koji je parent ponudi divu
   	  	 var sons=dads[0] //dohvatanje parenta diva za ponudu
   	  	 var daughters=sons.children //dohvatanje diva za ponudu i njegovog siblinga (tip smestaja div)
   	  	 
   	  	 var ponuda=daughters[index] //dohvatanje indeksa ponude koji odgovara indeksu elementa 
		
   		 var granddads=el.parentNode.parentNode.parentNode //dohvatanje vrhovnog diva sa id-em smestaja
   		 
   		 var smestaj=granddads.id; //stavljanje id smestaja u varijablu
         
         //alert(ar+" Broj kreveta: "+krevet.id+" Index: "+index+" Smestaj: "+smestaj+" Ponuda: "+ponuda.id); //ispis
         //ajax poziv sa prosledjivanjem cene, kreveta, ponude, smestaja i aranzmana
         
   	  
		$.ajax({
				method : "GET",
				url : "${pageContext.request.contextPath}/rezervacija",
				data : {
					   "ar":ar,
					   "smestaj":smestaj,
					   "ponuda":ponuda.id,
					   "krevet":krevet.id
					   },
				success : function(data) {
					console.log(data);
					var response=JSON.parse(data);
					
					window.location.href="${pageContext.request.contextPath}/rezervacija-forma?nazivAranzmana="+response[0].nazivAranzmana
							+"&idAranzmana="+response[0].idAranzmana+"&rezultat="+response[0].rezultat+"&nazivSmestaja="+response[0].nazivSmestaja
							+"&idSmestaja="+response[0].idSmestaja+"&idPonude="+response[0].idPonude+"&datumi="+response[0].datumi
							+"&brojKreveta="+response[0].brojKreveta+"&cena="+response[0].cena;
				}
			});
   		}
   		
   	
	</script>
	
	

<script type="text/javascript" src="webjars/jquery/3.5.1/jquery.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<script type="text/javascript" src="webjars/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>
