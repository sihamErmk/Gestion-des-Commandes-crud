<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="../index.jsp" %>
  <title>Add Client</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script>
    $(document).ready(function () {
      // Style the form dynamically
      $('form').css({
        'background-color': '#f4f4f4',
        'padding': '20px',
        'border-radius': '8px',
        'box-shadow': '0 4px 6px rgba(0, 0, 0, 0.1)',
        'width': '400px',
        'margin': 'auto',
        'margin-top': '20px',
      });

      $('form label').css({
        'display': 'block',
        'margin-bottom': '5px',
        'color': '#284167',
        'font-weight': 'bold',
      });

      $('form input').css({
        'width': '100%',
        'padding': '8px',
        'margin-bottom': '15px',
        'border': '1px solid #ccc',
        'border-radius': '4px',
      });

      $('form button').css({
        'background-color': '#284167',
        'color': 'white',
        'border': 'none',
        'padding': '10px 20px',
        'border-radius': '5px',
        'cursor': 'pointer',
      });

      $('form button:hover').css({
        'background-color': '#1f3152',
      });

      $('p a').css({
        'color': '#284167',
        'text-decoration': 'none',
        'font-weight': 'bold',
      });

      $('p a:hover').css({
        'text-decoration': 'underline',
      });
    });
  </script>
</head>
<body>
<h1 style="text-align:center; color:#284167;">Add Client</h1>
<form method="post" action="${pageContext.request.contextPath}/client/save">
  <p>
    <label for="nom">Name:</label>
    <input type="text" name="nom" id="nom" required/>
  </p>
  <p>
    <label for="adresse">Address:</label>
    <input type="text" name="adresse" id="adresse" required/>
  </p>
  <p>
    <label for="email">Email:</label>
    <input type="email" name="email" id="email" required/>
  </p>
  <button type="submit">Save</button>
</form>
<p style="text-align:center;">
  <a href="${pageContext.request.contextPath}/client/list">Back to List</a>
</p>
</body>
</html>
