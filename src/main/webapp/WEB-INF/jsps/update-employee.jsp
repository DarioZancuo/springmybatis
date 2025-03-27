<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Employee</title>
</head>
<body>
    <div style="display: flex; justify-content: center; align-items: center; margin-top: 20px;">
        <form action="${pageContext.request.contextPath}/employee/updateProcess" method="post">
            <table border="1" style="width: 600px; text-align: left;">
            
                <tr style="display: none;">
                    <td><b>Employee ID</b></td>
                    <td><input type="hidden" name="id" value="${employee.id}" /></td>
                </tr>
                
                <tr>
                    <td><b>Full Name</b></td>
                    <td><input type="text" name="fullname" value="${employee.fullname}" style="width: 100%;" /></td>
                </tr>
                
                <tr>
                    <td><b>Email</b></td>
                    <td><input type="email" name="email" value="${employee.email}" style="width: 100%;" /></td>
                </tr>
                
                <tr>
                    <td><b>Gender</b></td>
                    <td>
                        <input type="radio" name="gender" value="M" ${employee.gender == 'M' ? 'checked' : ''} /> M
                        <input type="radio" name="gender" value="F" ${employee.gender == 'F' ? 'checked' : ''} /> F
                    </td>
                </tr>
                
                <tr>
                    <td><b>Hobbies</b></td>
                    <td>
                        <select name="hobbies" style="width: 100%;">
                            <c:forEach items="${hobbies}" var="hobby">
                                <option value="${hobby.descrizione}" ${employee.hobbies == hobby.descrizione ? 'selected' : ''}>${hobby.descrizione}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                
                <tr>
                    <td><b>Country</b></td>
                    <td>
                        <select name="country" style="width: 100%;">
                            <c:forEach items="${countries}" var="country">
                                <option value="${country.descrizione}" ${employee.country == country.descrizione ? 'selected' : ''}>${country.descrizione}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                
                <tr>
                    <td><b>Address</b></td>
                    <td><input type="text" name="address" value="${employee.address}" style="width: 100%;" /></td>
                </tr>
                
                <tr>
                    <td colspan="2">
                        <button type="submit" style="width: 100%; padding: 5px;">Update Employee</button>
                    </td>
                </tr>
                
                <tr>
                    <td colspan="2">
                        <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/showEmployees'" style="width: 100%; display: block; padding: 5px;">Back to List</button>
                    </td>
                </tr>
                
            </table>
        </form>
    </div>

    <c:if test="${not empty error}">
        <div style="color: red; text-align: center; margin-bottom: 10px;">${error}</div>
    </c:if>

</body>
</html>
