<!DOCTYPE html>
<%@ include file="../index.jsp" %>
<html>
<head>

    <title>Edit Produit</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            // Confirm submission
            $("form").on("submit", function (e) {
                if (!confirm("Are you sure you want to update this Produit?")) {
                    e.preventDefault();
                }
            });

            // Highlight invalid inputs dynamically
            $("input").on("blur", function () {
                if (!$(this).val()) {
                    $(this).css("border", "1px solid red");
                } else {
                    $(this).css("border", "");
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

        input[type="text"], input[type="number"] {
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
<h1>Edit Produit</h1>
<form action="${pageContext.request.contextPath}/produit/update" method="post">
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="id" value="${produit.id}"/>

    <p>
        <label for="nom">Nom:</label>
        <input type="text" name="nom" id="nom" value="${produit.nom}" required/>
    </p>

    <p>
        <label for="prix">Prix:</label>
        <input type="number" step="0.01" name="prix" id="prix" value="${produit.prix}" required/>
    </p>

    <button type="submit">Update</button>
</form>
</body>
</html>
