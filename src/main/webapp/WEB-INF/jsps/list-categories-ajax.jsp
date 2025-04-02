<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="navbar.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Categories AJAX</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<div style="display: flex; justify-content: center; margin-top: 20px;">
    <table id="categoryTable" border="1" style="width: 600px; text-align: center;">
        <thead>
            <tr>
                <td></td>
                <td>
                    <button id="addCategoryBtn" style="width: 100%; padding: 5px;">Add Category</button>
                </td>
            </tr>
            <tr>
                <th id="categoryLabel">Categories</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <!-- AJAX -->
        </tbody>
    </table>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        const params = new URLSearchParams(window.location.search);
        const code = params.get('code');

        const labelMap = {
            hobbies: "Hobbies",
            country: "Countries"
        };
        $("#categoryLabel").text(labelMap[code] || "Categories");

        $("#addCategoryBtn").text("Add " + (labelMap[code] || "Category"));
        $("#addCategoryBtn").on("click", function () {
            location.href = 'category/showFormAddCategory?code=' + code;
        });

        $.ajax({
            url: '${pageContext.request.contextPath}/rest/listCategories?code=' + code,
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                const tableBody = $("#categoryTable tbody");
                tableBody.empty();

                data.forEach(function (category) {
                    const row = "<tr>" +
                        "<td>" + category.descrizione + "</td>" +
                        "<td style='padding: 0;'>" +
                            "<div style='display: flex; width: 100%;'>" +
                                "<button onclick=\"window.location.href='category/showFormUpdateCategory?id=" + category.id + "&code=" + code + "'\" style='flex: 1; padding: 5px;'>Update</button>" +
                                "<button onclick=\"if(confirm('Sei sicuro?')) window.location.href='category/deleteProcess?id=" + category.id + "&code=" + code + "'\" style='flex: 1; padding: 5px;'>Delete</button>" +
                            "</div>" +
                        "</td>" +
                    "</tr>";
                    tableBody.append(row);
                });
            },
            error: function () {
                alert("Errore nel caricamento delle categorie.");
            }
        });
    });
</script>

</body>
</html>
