<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Roles AJAX</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"
            integrity="sha512-bLT0Qm9VnAYZDflyKcBaQ2gg0hSYNQrJ8RilYldYQ1FxQYoCLtUjuuRuZo+fjqhx/qtq/1itJ0C2ejDxltZVFg=="
            crossorigin="anonymous"></script>
</head>
<body>

<div style="display: flex; justify-content: center; margin-top: 20px;">
    <table id="roleTable" border="1">
        <thead>
            <tr>
                <td colspan="2"></td>
                <td>
                    <button onclick="location.href='role/showFormAddRole'" style="width: 100%;">Add Role</button>
                </td>
            </tr>
            <tr>
                <th>Descrizione</th>
                <th>Stipendio Minimo</th>
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
        loadRoles();

        function loadRoles() {
            $.ajax({
                url: '${pageContext.request.contextPath}/rest/listRoles',
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
            const tableBody = document.getElementById('roleTable').getElementsByTagName('tbody')[0];
            tableBody.innerHTML = "";

            data.forEach(function (r) {
                const row = "<tr>" +
                    "<td>" + r.descrizione + "</td>" +
                    "<td>" + r.stipendioMin + " €</td>" +
                    "<td style='padding: 0;'>" +
                        "<div style='display: flex;'>" +
                            "<button style='flex: 1;' onclick=\"location.href='role/showFormUpdateRole?id=" + r.id + "'\">Update</button>" +
                            "<button style='flex: 1;' onclick=\"if(confirm('Sei sicuro di voler eliminare?')) location.href='role/deleteProcess?id=" + r.id + "'\">Delete</button>" +
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
