<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contratto</title>
</head>
<body>

    <div style="display: flex; justify-content: center; align-items: center; margin-top: 20px;">
        <table border="1" style="width: 600px; text-align: left; border-collapse: collapse;">
            
            <tr>
                <td><b>Status</b></td>
				<td>
				    <c:choose>
				        <c:when test="${contratto.status == true}">
				            <span>&#128994;</span> Attivo
				        </c:when>
				        <c:otherwise>
				            <span>&#128308;</span> Terminato
				        </c:otherwise>
				    </c:choose>
				</td>
            </tr>
            
            <tr>
                <td><b>Categoria</b></td>
                <td>${contratto.tipologia.tipo}</td>
            </tr>
            
            <tr>
                <td><b>Ruolo</b></td>
                <td>${contratto.role.descrizione}</td>
            </tr>
            
            <tr>
                <td><b>Stipendio</b></td>
                <td>${contratto.stipendio}€</td> 
            </tr>
            
            <tr>
                <td><b>Data Assunzione</b></td>
                <td>${contratto.dataAssunzione}</td> 
            </tr>
            
            <tr>
                <td><b>Data Dimissione</b></td>
                <td>${contratto.dataDimissione}</td> 
            </tr>
            
            <tr>
			    <td colspan="2" style="text-align: center;">
			<button type="button"
			        onclick="window.location.href='${pageContext.request.contextPath}/contratto/showFormUpdateContratto?id=${contratto.id}'"
			        style="width: 100%; padding: 5px;">Update</button>
			    </td>
			</tr>

			<tr>
			    <td colspan="2" style="text-align: center;">
			        <button type="button"
			                onclick="if(confirm('Sei sicuro di voler ELIMINARE questo contratto?')) 
			                         window.location.href='${pageContext.request.contextPath}/contratto/deleteLogicalProcess?id=${contratto.id}'"
			                style="width: 100%; padding: 5px; ">Delete</button>
			    </td>
			</tr>

            <tr>
                <td colspan="2" style="text-align: center;">
                    <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/showEmployees'" style="width: 100%; padding: 5px;">Back to List</button>
                </td>             
            </tr>   
                   
        </table>
    </div>
</body>
</html>
