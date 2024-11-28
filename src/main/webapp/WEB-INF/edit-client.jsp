<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../index.jsp" %>
<!DOCTYPE html>
<html>
<head>

    <title>Edit Client</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            // Highlight the input fields on focus
            $('input').on('focus', function() {
                $(this).css('background-color', '#e0f7fa');
            }).on('blur', function() {
                $(this).css('background-color', '');
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

        input[type="text"], input[type="email"] {
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
<h1>Edit Client</h1>
<form method="post" action="${pageContext.request.contextPath}/client/update">
    <input type="hidden" name="id" value="${client.id}"/>
    <p>
        <label for="nom">Name:</label>
        <input type="text" name="nom" id="nom" value="${client.nom}" required/>
    </p>
    <p>
        <label for="adresse">Address:</label>
        <input type="text" name="adresse" id="adresse" value="${client.adresse}" required/>
    </p>
    <p>
        <label for="email">Email:</label>
        <input type="email" name="email" id="email" value="${client.email}" required/>
    </p>
    <button type="submit">Save</button>
</form>
<p>
    <a href="${pageContext.request.contextPath}/client/list">Back to List</a>
</p>
</body>
</html>
