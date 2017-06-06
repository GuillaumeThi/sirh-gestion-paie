<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>

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
	
	<h1 class="col-md-12" style="text-align: center">Liste des employés</h1>


	<div class="container" style="width: 70%; margin: auto">
		
		<table class="table table-striped">
			<thead>
	            <tr>
	            	<th>Date de création</th>
	                <th>Matricule</th>
	                <th>Entreprise</th>
	                <th>Grade</th>
	                <th>Profil</th>
	            </tr>
	        </thead>
	        
	        <tbody>
	        	<c:forEach var="employe" items="${employes}">
	        	<tr>
	        		<td>${employe.dateCreation}</td>
	        		<td>${employe.matricule}</td>
	        		<td>${employe.entreprise.denomination}</td>
	        		<td>${employe.grade.code}</td>
	        		<td>${employe.profilRemuneration.code}</td>
	        	</tr>
	        	</c:forEach>
	        </tbody>
		</table>
		
	</div>

</body>
</html>