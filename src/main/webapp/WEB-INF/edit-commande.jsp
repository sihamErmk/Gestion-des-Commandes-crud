<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../index.jsp" %>
<!DOCTYPE html>
<html>
<head>

    <title>Edit Commande</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            // Highlight invalid inputs dynamically
            $("input, select").on("blur", function () {
                if (!$(this).val()) {
                    $(this).css("border", "1px solid red");
                } else {
                    $(this).css("border", "");
                }
            });

            // Add confirmation before submitting
            $("#editCommandeForm").on("submit", function (e) {
                if (!confirm("Are you sure you want to update this Commande?")) {
                    e.preventDefault();
                }
            });
        });
    </script>
    <style>
        /* Form Styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            color: #333;
            padding: 20px;
        }

        h1 {
            color: #284167;
            text-align: center;
        }

        form {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            margin: 0 auto;
        }

        label {
            font-weight: bold;
            margin-top: 10px;
            display: block;
        }

        input[type="date"], select {
            width: 100%;
            padding: 10px;
            margin: 5px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        button {
            background-color: #284167;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }

        button:hover {
            background-color: #1f3152;
        }

        p a {
            color: #284167;
            text-decoration: none;
            font-size: 16px;
        }

        p a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<h1>Edit Commande</h1>
<form action="${pageContext.request.contextPath}/commandes/update" method="post" id="editCommandeForm">

    <!-- Hidden input to send the Commande ID -->
    <input type="hidden" name="id" value="${commande.id}" />

    <p>
        <label for="dateCommande">Date Commande:</label>
        <input type="date" name="dateCommande" id="dateCommande" value="${commande.dateCommande}" required/>
    </p>

    <p>
        <label for="clientId">Client:</label>
        <select name="clientId" id="clientId" required>
            <option value="">Select a Client</option>
            <c:forEach var="client" items="${clients}">
                <option value="${client.id}" ${client.id == commande.client.id ? 'selected="selected"' : ''}>
                        ${client.nom}
                </option>
            </c:forEach>
        </select>
    </p>

    <button type="submit">Update</button>
</form>

<p>
    <a href="${pageContext.request.contextPath}/commandes/list">Back to List</a>
</p>
</body>
</html>
