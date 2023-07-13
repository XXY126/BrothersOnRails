<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="include/head.jsp"%>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
<script src="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.all.min.js
"></script>
<link href="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.min.css
" rel="stylesheet">
<%String status = (String) request.getAttribute("status");%>
<script type ="text/javascript">
	if('<%= status %>' == 'failed'){
		Swal.fire("Spiacente!", "Email o Password errati", "error");
	}
</script>
<title>LogIn</title>
</head>
<body>
<%@include file="include/header.jsp" %>

  <div class="container">
    <div class="row justify-content-center mt-5">
      <div class="col-md-6">
        <h2 class="text-center mb-4">Accesso</h2>
        <form id="loginForm" action="LoginServlet" method="POST">
          <div class="form-group">
            <label for="username">Nome utente</label>
            <input type="text" class="form-control" id="username" name="username" required>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" required>
          </div>
          <button type="submit" class="btn btn-danger btn-block">Accedi</button>
          <p class="text-center mt-3">Non sei iscritto? <a href="signup.jsp">Clicca qui</a></p>
        </form>
      </div>
    </div>
  </div>
  
  
  
  <%@include file="include/footer.jsp" %>
  
    <script>
    document.getElementById("loginForm").addEventListener("submit", function(event) {
      event.preventDefault();
      var username = document.getElementById("username").value;
      var password = document.getElementById("password").value;

      if (username === "") {
        showAlert("Il nome utente è obbligatorio", "alert-danger");
        return;
      }

      if (password === "") {
        showAlert("La password è obbligatoria", "alert-danger");
        return;
      }

      // Qui puoi eseguire la logica di autenticazione o inviare i dati al server

      showAlert("Accesso effettuato con successo", "alert-success");
      document.getElementById("loginForm").reset();
    });

    function showAlert(message, className) {
      var alertDiv = document.createElement("div");
      alertDiv.className = "alert " + className;
      alertDiv.appendChild(document.createTextNode(message));

      var container = document.querySelector(".container");
      var row = document.querySelector(".row");
      container.insertBefore(alertDiv, row);

      setTimeout(function() {
        alertDiv.remove();
      }, 3000);
    }
  </script>


</body>
</html>