<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../index.jsp" %>
<!DOCTYPE html>
<html>
<head>

    <title>Add Commande</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            // Add confirmation before submitting
            $("#addCommandeForm").on("submit", function (e) {
                if (!confirm("Are you sure you want to add this Commande?")) {
                    e.preventDefault();
                }
            });

            // Highlight invalid inputs dynamically
            $("input, select").on("blur", function () {
                if (!$(this).val()) {
                    $(this).css("border", "1px solid red");
                } else {
                    $(this).css("border", "1px solid #ccc");
                }
            });

            // Apply consistent styling
            $("form").css({
                'background-color': '#f4f4f4',
                'padding': '20px',
                'border-radius': '8px',
                'box-shadow': '0 4px 6px rgba(0, 0, 0, 0.1)',
                'width': '400px',
                'margin': 'auto',
                'margin-top': '20px',
            });

            $("form label").css({
                'display': 'block',
                'margin-bottom': '5px',
                'color': '#284167',
                'font-weight': 'bold',
            });

            $("form input, form select").css({
                'width': '100%',
                'padding': '8px',
                'margin-bottom': '15px',
                'border': '1px solid #ccc',
                'border-radius': '4px',
            });

            $("form button").css({
                'background-color': '#284167',
                'color': 'white',
                'border': 'none',
                'padding': '10px 20px',
                'border-radius': '5px',
                'cursor': 'pointer',
            });

            $("form button:hover").css({
                'background-color': '#1f3152',
            });
        });
    </script>
</head>
<body>
<h1 style="text-align:center; color:#284167;">Add Commande</h1>
<form action="${pageContext.request.contextPath}/commandes/save" method="post" id="addCommandeForm">

    <p>
        <label for="dateCommande">Date Commande:</label>
        <input type="date" name="dateCommande" id="dateCommande" required/>
    </p>

    <p>
        <label for="clientId">Client:</label>
        <select name="clientId" id="clientId" required>
            <option value="">Select a Client</option>
            <c:forEach var="client" items="${clients}">
                <option value="${client.id}">${client.nom}</option>
            </c:forEach>
        </select>
    </p>

    <button type="submit">Save</button>
</form>
</body>
</html>
