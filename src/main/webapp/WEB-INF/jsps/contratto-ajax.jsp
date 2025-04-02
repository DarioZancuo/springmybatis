<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contratto AJAX</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<div style="display: flex; justify-content: center; margin-top: 20px;">
    <table id="contrattoTable" border="1" style="width: 600px; text-align: left; border-collapse: collapse;">
        <tbody>
            <!-- AJAX CONTENT -->
        </tbody>
    </table>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        const contextPath = '${pageContext.request.contextPath}';
        const urlParams = new URLSearchParams(window.location.search);
        const idContratto = urlParams.get("id");

        if (!idContratto) {
            $("#contrattoTable tbody").html("<tr><td colspan='2' style='text-align:center;'>ID contratto mancante.</td></tr>");
            return;
        }

        $.ajax({
            url: contextPath + '/rest/getContratto?id=' + idContratto,
            method: 'GET',
            dataType: 'json',
            
            success: function (contratto) {
                console.log("Contratto caricato:", contratto);

                const status = contratto.status ? "🟢 Attivo" : "🔴 Terminato";
                const tipo = contratto.tipologia && contratto.tipologia.tipo ? contratto.tipologia.tipo : "-";
                const ruolo = contratto.role && contratto.role.descrizione ? contratto.role.descrizione : "-";
                const stipendio = contratto.stipendio != null ? contratto.stipendio + "€" : "-";
                const assunzione = contratto.dataAssunzione || "-";
                const dimissione = contratto.dataDimissione || "-";

                let tableHtml = "";
                tableHtml += "<tr><td><b>Status</b></td><td>" + status + "</td></tr>";
                tableHtml += "<tr><td><b>Categoria</b></td><td>" + tipo + "</td></tr>";
                tableHtml += "<tr><td><b>Ruolo</b></td><td>" + ruolo + "</td></tr>";
                tableHtml += "<tr><td><b>Stipendio</b></td><td>" + stipendio + "</td></tr>";
                tableHtml += "<tr><td><b>Data Assunzione</b></td><td>" + assunzione + "</td></tr>";
                tableHtml += "<tr><td><b>Data Dimissione</b></td><td>" + dimissione + "</td></tr>";
                tableHtml += "<tr><td colspan='2' style='text-align:center;'>" +
                             "<button onclick=\"location.href='" + contextPath + "/contratto/showFormUpdateContratto?id=" + contratto.id + "'\" style='width:100%;'>Update</button></td></tr>";
                tableHtml += "<tr><td colspan='2' style='text-align:center;'>" +
                             "<button onclick=\"if(confirm('Sei sicuro di voler ELIMINARE?')) location.href='" + contextPath + "/contratto/deleteLogicalProcess?id=" + contratto.id + "'\" style='width:100%;'>Delete</button></td></tr>";
                tableHtml += "<tr><td colspan='2' style='text-align:center;'>" +
                             "<button onclick=\"location.href='" + contextPath + "/showEmployees'\" style='width:100%;'>Back to List</button></td></tr>";

                $("#contrattoTable tbody").html(tableHtml);
            },
            error: function (xhr) {
                $("#contrattoTable tbody").html("<tr><td colspan='2' style='text-align:center; color:red;'>Errore: " + xhr.responseText + "</td></tr>");
            }
        });
    });
</script>

</body>
</html>
