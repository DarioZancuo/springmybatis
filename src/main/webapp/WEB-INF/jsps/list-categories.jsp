<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Categories</title>
</head>
<body>   
    <div style="display: flex; justify-content: center; margin-top: 20px;">
        <table border="1" style="width: 600px; text-align: center;">
        
            <tr>
                <td></td>
                <td>
                    <button onclick="window.location.href='category/showFormAddCategory?code=${code}'"
                        style="width: 100%; padding: 5px;">
                        <c:choose>
                            <c:when test="${code == 'hobbies'}">Add Hobby</c:when>
                            <c:when test="${code == 'country'}">Add Country</c:when>
                            <c:otherwise>Add Category</c:otherwise>
                        </c:choose>
                    </button>
                </td>
            </tr>
            
            <tr>
                <th>
                    <c:choose>
                        <c:when test="${code == 'hobbies'}">Hobbies</c:when>
                        <c:when test="${code == 'country'}">Countries</c:when>
                    </c:choose>
                </th>
                <th>Actions</th>
            </tr>
            
            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.descrizione}</td>
                    <td style="padding: 0;">
                        <div style="display: flex; width: 100%;">
                            <button onclick="window.location.href='category/showFormUpdateCategory?id=${category.id}&code=${code}'"
                                style="flex: 1; padding: 5px;">Update</button>
                            <button onclick="if(confirm('Sei sicuro di voler ELIMINARE?')) { window.location.href='category/deleteProcess?id=${category.id}&code=${code}'; }"
                                style="flex: 1; padding: 5px;">Delete</button>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            
        </table>
    </div>
</body>
</html>
