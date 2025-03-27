<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@ include file="navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employees</title>
</head>
<body>

    <!-- Filter -->
    <s:form modelAttribute="scelta" action="filterEmployee" style="display: flex; justify-content: center; gap: 15px; align-items: flex-end; margin: 20px 0;">
        <div class="mb-3">
            <label for="input" class="form-label">Filtra per</label>
            <s:select path="input" class="form-select" id="input">
                <s:option value="">Tutti</s:option>
                <s:option value="status">Status</s:option>
                <s:option value="ruolo">Ruolo</s:option>
                <s:option value="tipologia">Categoria</s:option>
                <s:option value="country">Country</s:option>
            </s:select>
        </div>

        <div class="mb-3">
            <label for="valore" class="form-label">Valore</label>
            <s:input path="value" class="form-control" id="valore" placeholder="Inserisci valore..." />
        </div>

        <div class="mb-3">
            <button type="submit" class="btn btn-primary">Filtra</button>
        </div>
    </s:form>

    <!-- Errors -->
    <c:if test="${empty listEmployees}">
        <p style="text-align: center; color: gray;">Nessun dipendente presente.</p>
    </c:if>

    <c:if test="${not empty error}">
        <p style="text-align: center; color: red;">Errore: ${error}</p>
    </c:if>

    <div align="center" style="margin-top: 10px;">
        <table border="1" style="border-collapse: collapse; text-align: center; font-size: 14px; min-width: 1000px;">
            <tr>
                <td colspan="10"></td>
                <td>
                    <button onclick="window.location.href='${pageContext.request.contextPath}/employee/showFormAddEmployee'"
                            style="width: 100%; height: 100%;">Add Employee</button>
                </td>
            </tr>

            <tr>
                <th>Full Name</th>
                <th>Email</th>
                <th>Gender</th>
                <th>Hobbies</th>
                <th>Country</th>
                <th>Address</th>
                <th>Ruolo</th>
    			<th>Tipologia</th>
    			<th>Contratto</th>
                <th>Status</th>
                <th>Actions</th>
            </tr>

            <c:forEach items="${listEmployees}" var="e">
                <tr>
                    <td>${e.fullname}</td>
                    <td>${e.email}</td>
                    <td>${e.gender}</td>
                    <td>${e.hobbies}</td>
                    <td>${e.country}</td>
                    <td>${e.address}</td>

                    
                  	<!-- Ruolo -->
				 	<td>
					    <c:choose>
					        <c:when test="${not empty e.contratto and not empty e.contratto.role}">
					            ${e.contratto.role.descrizione}
					        </c:when>
					        <c:otherwise>-</c:otherwise>
					    </c:choose>
					</td>
					
					<!-- Tipologia -->
					<td>
					    <c:choose>
					        <c:when test="${not empty e.contratto and not empty e.contratto.tipologia}">
					            ${e.contratto.tipologia.tipo}
					        </c:when>
					        <c:otherwise>-</c:otherwise>
					    </c:choose>
					</td>

                    <!-- Contratto -->
                    <td style="padding: 0;">
                        <c:choose>
                            <c:when test="${not empty e.contratto}">
                                <button onclick="window.location.href='${pageContext.request.contextPath}/showContratto?id=${e.contratto.id}'"
                                        style="width: 100%; height: 100%; font-size: 18px;">&#128196;</button>
                            </c:when>
                            <c:otherwise>
                                <button onclick="window.location.href='${pageContext.request.contextPath}/contratto/showFormAddContratto?idEmployee=${e.id}'"
                                        style="width: 100%; height: 100%; font-size: 18px;">&#10133;</button>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <!-- Status -->
                    <td>
                        <c:choose>
                            <c:when test="${not empty e.contratto}">
                                <c:choose>
                                    <c:when test="${e.contratto.status == true}">
                                        <span style="color: green;">&#128994;</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color: red;">&#128308;</span>
                                    </c:otherwise>
                                </c:choose>
                            </c:when>
                        </c:choose>
                    </td>

                    <!-- Actions -->
                    <td style="padding: 0;">
                        <div style="display: flex; width: 100%;">
                            <button onclick="window.location.href='${pageContext.request.contextPath}/employee/showFormUpdateEmployee?id=${e.id}'"
                                    style="flex: 1; padding: 5px;">Update</button>
                            <button onclick="if(confirm('Sei sicuro di voler ELIMINARE?')) {
                                            window.location.href='${pageContext.request.contextPath}/employee/deleteProcess?id=${e.id}';
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
