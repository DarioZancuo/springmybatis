<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@ include file="navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Contratto</title>
</head>
<body>

<div style="display: flex; justify-content: center; align-items: center; margin-top: 20px;">
<s:form modelAttribute="contratto" action="${pageContext.request.contextPath}/contratto/updateProcess" method="post">
        <table border="1" style="width: 600px; text-align: left; border-collapse: collapse;">
            
			<tr>
			    <s:hidden path="id" />
			</tr>
			
            <tr>
                <td><b>Data Assunzione</b></td>
                <td><s:input path="dataAssunzione" type="date" style="width: 100%;" /></td>
            </tr>
            
            <tr>
                <td><b>Stipendio</b></td>
                <td><s:input path="stipendio" style="width: 100%;" /></td>
            </tr>
            
            <tr>
                <td><b>Data Dimissione</b></td>
                <td><s:input path="dataDimissione" type="date" style="width: 100%;" /></td>
            </tr>
            
            <tr>
                <td><b>Status</b></td>
                <td>
                    <s:radiobutton path="status" value="true" /> Attivo
                    <s:radiobutton path="status" value="false" /> Terminato
                </td>
            </tr>
            
            <tr>
                <td><b>Ruolo</b></td>
                <td>
                    <s:select path="role.id" style="width: 100%;">
                        <s:options items="${roles}" itemValue="id" itemLabel="descrizione" />
                    </s:select>
                </td>
            </tr>
            
            <tr>
                <td><b>Categoria</b></td>
                <td>
                    <s:select path="tipologia.id" style="width: 100%;">
                        <s:options items="${tipologie}" itemValue="id" itemLabel="tipo" />
                    </s:select>
                </td>
            </tr>

            <tr>
                <td colspan="2" style="text-align: center;">
                    <button type="submit" style="width: 100%; padding: 5px;">Update Contratto</button>
                </td>
            </tr>
            
            <tr>
                <td colspan="2" style="text-align: center;">
                    <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/showEmployees'" style="width: 100%; padding: 5px;">Back to List</button>
                </td>
            </tr>
        </table>
    </s:form>
</div>

<c:if test="${not empty error}">
    <div style="color: red; text-align: center; margin-bottom: 10px;">${error}</div>
</c:if>

</body>
</html>
