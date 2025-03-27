<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Category</title>
</head>
<body>
    <div style="display: flex; justify-content: center; margin-top: 20px;">
        <form action="${pageContext.request.contextPath}/category/createProcess" method="post">
            <table border="1" style="width: 600px;">
            
                <tr>
                    <td><b>Code</b></td>
                    <td>
                        <input type="text" name="code" value="${category.code}" readonly style="width: 100%;"/>
                    </td>
                </tr>
                
                <tr>
                    <td><b>Descrizione</b></td>
                    <td>
                        <input type="text" name="descrizione" value="${category.descrizione}" style="width: 100%;"/>
                    </td>
                </tr>
                
                <tr>
                    <td colspan="2">
                        <button type="submit" style="width: 100%; padding: 5px;">
                            <c:choose>
                                <c:when test="${category.code == 'hobbies'}">Save Hobby</c:when>
                                <c:when test="${category.code == 'country'}">Save Country</c:when>
                                <c:otherwise>Save Category</c:otherwise>
                            </c:choose>
                        </button>
                    </td>
                </tr>
                
                <tr>
                    <td colspan="2">
                        <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/showCategories?code=${category.code}'" style="width: 100%; padding: 5px;">
                            Back to List
                        </button>
                    </td>
                </tr>
                
            </table>
        </form>
    </div>
    
    <c:if test="${not empty error}">
        <div class="alert alert-danger" style="text-align: center; color: red; margin-top: 20px;">
            ${error}
        </div>
    </c:if>
    
</body>
</html>
