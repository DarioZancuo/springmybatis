<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Roles</title>
</head>
<body>   
    <div style="display: flex; justify-content: center; margin-top: 20px;">
        <table border="1" style="width: 800px; text-align: center;">
        
			 <tr>
			    <td></td>
			    <td></td>
			    <td>
			        <button onclick="window.location.href='${pageContext.request.contextPath}/role/showFormAddRole'"
			                style="width: 100%; padding: 5px;">
			            Add Role
			        </button>
			    </td>
			</tr>
            
            <tr>
                <th>Descrizione</th>
                <th>Stipendio Minimo</th>
                <th>Actions</th>
            </tr>
            
            <c:forEach items="${roles}" var="role">
                <tr>
                    <td>${role.descrizione}</td>
                    <td>
                        <fmt:formatNumber value="${role.stipendioMin}" type="currency" currencySymbol="€" />
                    </td>
					<td style="padding: 0;">
					    <div style="display: flex; width: 100%;">
					        <button
					            onclick="window.location.href='${pageContext.request.contextPath}/role/showFormUpdateRole?id=${role.id}'"
					            style="flex: 1; padding: 5px;">Update</button>
					
					        <button
					            onclick="if(confirm('Sei sicuro di voler ELIMINARE?')) {
					                window.location.href='${pageContext.request.contextPath}/role/deleteProcess?id=${role.id}';
					            }"
					            style="flex: 1; padding: 5px;">Delete</button>
					    </div>
					</td>
                </tr>
            </c:forEach>
            
        </table>
    </div>
</body>
</html>
