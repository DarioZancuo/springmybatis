<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Role</title>
</head>
<body>
    <div style="display: flex; justify-content: center; margin-top: 20px;">
        <form action="${pageContext.request.contextPath}/role/updateProcess" method="post">
            <input type="hidden" name="id" value="${role.id}" />
            <table border="1" style="width: 600px;">
                <tr>
                    <td><b>Descrizione</b></td>
                    <td>
                        <input type="text" name="descrizione" value="${role.descrizione}" style="width: 100%;" />
                    </td>
                </tr>

                <tr>
                    <td><b>Stipendio Minimo</b></td>
                    <td>
                        <input type="number" step="0.01" name="stipendioMin" value="${role.stipendioMin}" style="width: 100%;" />
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <button type="submit" style="width: 100%; padding: 5px;">Update Role</button>
                    </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/showRoles'" style="width: 100%; padding: 5px;">
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
