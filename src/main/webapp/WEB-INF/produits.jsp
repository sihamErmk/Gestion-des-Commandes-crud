<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../index.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Produits List</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            // Confirm delete with jQuery
            $("a.delete-link").on("click", function (e) {
                if (!confirm("Are you sure you want to delete this Produit?")) {
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
            margin: 0;
        }

        h1 {
            text-align: center;
            color: #2c3e50;
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
            background-color: #34495e;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #ecf0f1;
        }

        tr:hover {
            background-color: #bdc3c7;
        }

        a {
            color: #2980b9;
            text-decoration: none;
            font-size: 16px;
        }

        a:hover {
            text-decoration: underline;
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

        /* Centering table and content */
        .container {
            width: 80%;
            margin: 0 auto;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            table {
                font-size: 14px;
            }
            th, td {
                padding: 8px;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h1>List of Produits</h1>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Prix</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="produit" items="${produits}">
            <tr>
                <td>${produit.id}</td>
                <td>${produit.nom}</td>
                <td>${produit.prix}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/produit/edit?id=${produit.id}">Edit</a> |
                    <a href="${pageContext.request.contextPath}/produit/delete?id=${produit.id}" class="delete-link">
                        Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <p>
        <a href="${pageContext.request.contextPath}/produit/add">Add New Produit</a>
    </p>
</div>

</body>
</html>
