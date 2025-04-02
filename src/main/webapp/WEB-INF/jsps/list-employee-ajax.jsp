<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employees AJAX</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"
            integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg=="
            crossorigin="anonymous"></script>
</head>
<body>

<div style="display: flex; justify-content: center; margin-top: 20px;">
    <table id="employeeTable" border="1">
        <thead>
            <tr>
                <td colspan="10"></td>
                <td>
                    <button onclick="location.href='employee/showFormAddEmployee'" style="width: 100%;">Add Employee</button>
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
                <th>Azioni</th>
            </tr>
        </thead>
        <tbody>
            <!-- AJAX -->
        </tbody>
    </table>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        loadEmployees();

        function loadEmployees() {
            $.ajax({
                url: '${pageContext.request.contextPath}/rest/listEmployees',
                method: 'GET',
                dataType: 'json',
                success: function (data) {
                    updateTable(data);
                },
                error: function (xhr, status, error) {
                    console.error("Errore AJAX:", status, error);
                    alert("Errore durante il caricamento dei dati.");
                }
            });
        }

        function updateTable(data) {
            var tableBody = document.getElementById('employeeTable').getElementsByTagName('tbody')[0];
            tableBody.innerHTML = "";

            data.forEach(function (e) {
                let contrattoButton = "";
                if (e.contratto && e.contratto.id) {
                    contrattoButton =
                        "<button onclick=\"location.href='showContratto?id=" + e.contratto.id + "'\" style='width: 100%; height: 100%; font-size: 18px;'>📄</button>";
                } else {
                    contrattoButton =
                        "<button onclick=\"location.href='contratto/showFormAddContratto?idEmployee=" + e.id + "'\" style='width: 100%; height: 100%; font-size: 18px;'>➕</button>";
                }

                var statusHtml = (e.contratto && e.contratto.status) ? "🟢" : "🔴";

                var row = "<tr>" +
                    "<td>" + e.fullname + "</td>" +
                    "<td>" + e.email + "</td>" +
                    "<td>" + e.gender + "</td>" +
                    "<td>" + e.hobbies + "</td>" +
                    "<td>" + e.country + "</td>" +
                    "<td>" + e.address + "</td>" +
                    "<td>" + (e.contratto?.role?.descrizione || '-') + "</td>" +
                    "<td>" + (e.contratto?.tipologia?.tipo || '-') + "</td>" +
                    "<td>" + contrattoButton + "</td>" +
                    "<td align='center'>" + statusHtml + "</td>" +
                    "<td style='padding: 0;'>" +
                        "<div style='display: flex;'>" +
                            "<button style='flex: 1;' onclick=\"location.href='employee/showFormUpdateEmployee?id=" + e.id + "'\">Update</button>" +
                            "<button style='flex: 1;' onclick=\"if(confirm('Sei sicuro di voler cancellare questo record?')) location.href='employee/deleteProcess?id=" + e.id + "'\">Delete</button>" +
                        "</div>" +
                    "</td>" +
                    "</tr>";

                tableBody.innerHTML += row;
            });
        }
    });
</script>

</body>
</html>
