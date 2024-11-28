<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../index.jsp" %>
<!DOCTYPE html>
<html>
<head>

    <title>Commandes</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            // Confirm delete with jQuery
            $("a.delete-link").on("click", function (e) {
                if (!confirm("Are you sure you want to delete this Commande?")) {
                    e.preventDefault(); // Prevent deletion
                }
            });

            // Add hover effect on table rows
            $("tbody tr").hover(
                function () {
                    $(this).css("background-color", "#f0f0f0");
                },
                function () {
                    $(this).css("background-color", "");
                }
            );
        });
    </script>
    <style>
        /* Table Styling */
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #284167;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }

        /* Buttons and Links Styling */
        a {
            color: #284167;
            text-decoration: none;
            padding: 5px 10px;
        }
        a:hover {
            background-color: #f1f1f1;
            border-radius: 4px;
        }

        /* Add New Commande Link Styling */
        p a {
            background-color: #284167;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            display: inline-block;
        }
        p a:hover {
            background-color: #1f3152;
        }
    </style>
</head>
<body>
<h1 style="text-align:center; color:#284167;">List of Commandes</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Date Commande</th>
        <th>Client ID</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="commande" items="${commandes}">
        <tr>
            <td>${commande.id}</td>
            <td>${commande.dateCommande}</td>
            <td>${commande.client.id}</td>
            <td>
                <a href="${pageContext.request.contextPath}/commandes/edit?id=${commande.id}">Edit</a> |
                <a href="${pageContext.request.contextPath}/commandes/delete?id=${commande.id}" class="delete-link">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>
    <a href="${pageContext.request.contextPath}/commandes/add">Add New Commande</a>
</p>
</body>
</html>
