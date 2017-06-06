<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		<a href="#">Employés</a>
		<a href="#">Bulletins</a>
	</nav>

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

</body>
</html>