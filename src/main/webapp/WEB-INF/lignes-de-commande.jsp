<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../index.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lignes De Commande</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            // Confirm delete with jQuery
            $("form button").on("click", function (e) {
                if (!confirm("Are you sure you want to delete this Ligne De Commande?")) {
                    e.preventDefault();
                }
            });

            // Add hover effect to table rows
            $("tbody tr").hover(
                function () {
                    $(this).css("background-color", "#f9f9f9");
                },
                function () {
                    $(this).css("background-color", "");
                }
            );
        });
    </script>
    <style>
        /* Page Styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #284167;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        th, td {
            text-align: left;
            padding: 12px;
            border: 1px solid #ddd;
        }

        th {
            background-color: #284167;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #e8e8e8;
        }

        button {
            background-color: #e74c3c;
            color: white;
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #c0392b;
        }

        a {
            color: #284167;
            text-decoration: none;
            font-size: 16px;
            display: inline-block;
            margin-top: 20px;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<h1>List of Lignes De Commande</h1>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Commande</th>
        <th>Produit</th>
        <th>Quantité</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="ligne" items="${lignesDeCommande}">
        <tr>
            <td>${ligne.id}</td>
            <td>Commande ID: ${ligne.commande.id}</td>
            <td>${ligne.produit.nom}</td>
            <td>${ligne.quantite}</td>
            <td>
                <form action="${pageContext.request.contextPath}/ligneDeCommande/delete?id=${ligne.id}" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="id" value="${ligne.id}"/>
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p>
    <a href="${pageContext.request.contextPath}/ligneDeCommande/add">Add New Ligne De Commande</a>
</p>

</body>
</html>
