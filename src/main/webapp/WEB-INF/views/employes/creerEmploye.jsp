<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/css/bootstrap.css">
<title>SIRH</title>
</head>

<body>

	<nav>
		<a class="col-md-1" href="<%=request.getContextPath()%>/mvc/employes/lister">Employés</a>
		<a class="col-md-1" href="<%=request.getContextPath()%>/mvc/bulletins/lister">Bulletins</a>
	</nav>
	
	<h1 class="col-md-12" style="text-align: center">Ajouter un employé</h1>

	<div style="width: 70%; margin: auto; margin-top: 100px">
	<form class="form-horizontal" method="post">
	
		<!-- Text input-->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="matricule">Matricule</label>  
		  <div class="col-md-4">
		  <input id="matricule" name="matricule" type="text" placeholder="matricule..." class="form-control input-md">
		    
		  </div>
		</div>
		
		<!-- Select Basic -->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="entreprise">Entreprise</label>
		  <div class="col-md-4">
		    <select id="entreprise" name="entreprise" class="form-control">
		    	<c:forEach var="entreprise" items="${entreprises}">
		      		<option value="${entreprise.id}">${entreprise.denomination}</option>
		      	</c:forEach>
		    </select>
		  </div>
		</div>
		
		<!-- Select Basic -->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="profil">Profil</label>
		  <div class="col-md-4">
		    <select id="profil" name="profil" class="form-control">
		      <c:forEach var="profil" items="${profils}">
		      		<option value="${profil.id}">${profil.code}</option>
		      	</c:forEach>
		    </select>
		  </div>
		</div>
		
		<!-- Select Basic -->
		<div class="form-group">
		  <label class="col-md-4 control-label" for="grade">Grade</label>
		  <div class="col-md-4">
		    <select id="grade" name="grade" class="form-control">
		      	<c:forEach var="grade" items="${grades}">
		      		<option value="${grade.id}">${grade.code}</option>
		      	</c:forEach>
		    </select>
		  </div>
		</div>
		
		<!-- Button -->
		<div class="form-group">
		  <div class="col-md-4" style="float: right">
		    <button id="add" name="addButton" class="btn btn-primary">Ajouter</button>
		  </div>
		</div>
	
		<sec:csrfInput/>
		
	</form>
	</div>
	
</body>
</html>