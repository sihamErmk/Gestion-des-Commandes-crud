<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../index.jsp" %>
<!DOCTYPE html>
<html>
<head>

    <title>Clients List</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            // Confirm delete with jQuery
            $('a.delete-link').on('click', function(e) {
                if (!confirm('Are you sure you want to delete this client?')) {
                    e.preventDefault(); // Prevent deletion
                }
            });
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

        /* Add New Client Link Styling */
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
<h1 style="text-align:center; color:#284167;">List of Clients</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Address</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="client" items="${clients}">
        <tr>
            <td>${client.id}</td>
            <td>${client.nom}</td>
            <td>${client.adresse}</td>
            <td>${client.email}</td>
            <td>
                <a href="${pageContext.request.contextPath}/client/edit?id=${client.id}">Edit</a> |
                <a href="${pageContext.request.contextPath}/client/delete?id=${client.id}" class="delete-link">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p>
    <a href="${pageContext.request.contextPath}/client/add">Add New Client</a>
</p>
</body>
</html>
