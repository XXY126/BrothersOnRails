<!DOCTYPE html>
<html>
<head>
<%@include file="include/head.jsp"%>
<title>SignUp</title>
</head>
<body>
<%@include file="include/header.jsp" %>

  <div class="container">
    <div class="row justify-content-center mt-5">
      <div class="col-md-6">
        <h2 class="text-center mb-4">Registrazione</h2>
        <form id="registrationForm" action = "SignUpServlet" method = "POST">
        <div class="form-group" >
            <label for="nome">Nome</label>
            <input type="nome" class="form-control" id="nome" name="nome" required>
          </div>
          <div class="form-group" action = "SignupServlet" method = "POST">
            <label for="email">Cognome</label>
            <input type="cognome" class="form-control" id="cognome" name="cognome" required>
          </div>
          <div class="form-group" action = "SignupServlet" method = "POST">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" required>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" minlength="8" required>
          </div>
          <div class="form-group">
            <label for="confirmPassword">Conferma password</label>
            <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
          </div>
          <button type="submit" class="btn btn-danger btn-block">Registrati</button>
          <p class="text-center mt-3">Sei già iscritto? <a href="login.jsp">Accedi qui</a></p>
        </form>
      </div>
    </div>
  </div>
  
    <script>
    document.getElementById("registrationForm").addEventListener("submit", function(event) {
      event.preventDefault();
      var email = document.getElementById("email").value;
      var password = document.getElementById("password").value;
      var confirmPassword = document.getElementById("confirmPassword").value;

      var emailError = document.getElementById("emailError");
      var passwordError = document.getElementById("passwordError");
      var confirmPasswordError = document.getElementById("confirmPasswordError");

      emailError.textContent = "";
      passwordError.textContent = "";
      confirmPasswordError.textContent = "";

      if (email === "") {
        emailError.textContent = "Inserisci un indirizzo email";
        emailError.style.color = "red";
        return;
      }

      if (password.length < 8) {
        passwordError.textContent = "La password deve contenere almeno 8 caratteri";
        passwordError.style.color = "red";
        return;
      }

      if (password !== confirmPassword) {
        confirmPasswordError.textContent = "Le password non corrispondono";
        confirmPasswordError.style.color = "red";
        return;
      }

      showAlert("Registrazione effettuata con successo", "alert-success");
      document.getElementById("registrationForm").reset();
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
  

<%@include file="include/footer.jsp" %>
</body>
</html>