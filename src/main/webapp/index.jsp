<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Application de Gestion</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #284167;
            padding: 30px 20px;
        }
        .navbar .logo {
            color: white;
            font-size: 24px;
            font-weight: bold;
            text-decoration: none;
        }
        .navbar ul {
            list-style: none;
            margin: 0;
            padding: 0;
            display: flex;
        }
        .navbar ul li {
            margin: 0 10px;
        }
        .navbar ul li a {
            text-decoration: none;
            color: white;
            padding: 8px 15px;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }
        .navbar ul li a:hover {
            background-color: #2d4872;
        }
        .toggle-btn {
            display: none;
            flex-direction: column;
            cursor: pointer;
        }
        .toggle-btn div {
            background-color: white;
            height: 3px;
            width: 25px;
            margin: 3px 0;
        }
        @media screen and (max-width: 768px) {
            .navbar ul {
                display: none;
                flex-direction: column;
                width: 100%;
                background-color: #2b5065;
                position: absolute;
                top: 60px;
                left: 0;
            }
            .navbar ul li {
                text-align: center;
                margin: 10px 0;
            }
            .navbar ul.active {
                display: flex;
            }
            .toggle-btn {
                display: flex;
            }
        }
    </style>
    <script>
        $(document).ready(function () {
            $(".toggle-btn").on("click", function () {
                $(".navbar ul").toggleClass("active");
            });
        });
    </script>
</head>
<body>
<div class="navbar">
    <a href="${pageContext.request.contextPath}" class="logo">Gestion des Commandes</a>
    <ul>
        <li><a href="${pageContext.request.contextPath}/client">Clients</a></li>
        <li><a href="${pageContext.request.contextPath}/produit">Produits</a></li>
        <li><a href="${pageContext.request.contextPath}/commandes">Commandes</a></li>
        <li><a href="${pageContext.request.contextPath}/ligneDeCommande">Lignes de Commande</a></li>
    </ul>
    <div class="toggle-btn">
        <div></div>
        <div></div>
        <div></div>
    </div>
</div>
</body>
</html>
