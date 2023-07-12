<!DOCTYPE html>
<html>
<head>
<%@include file="include/head.jsp"%>
<title>SignUp</title>
</head>
<body>
<%@include file="include/header.jsp" %>




<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
<script src="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.all.min.js
"></script>
<link href="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.min.css
" rel="stylesheet">
<%String status = (String) request.getAttribute("status");%>
<script type ="text/javascript">
	if('<%= status %>' == 'success'){
		Swal.fire("Congratulazione!", "Account creato correttamente!", "success");
	}else if('<%= status %>' == 'duplicato'){
		Swal.fire("Spiacente!", "Email gia presente nel sito","error");
	}else if('<%= status %>' == 'Invalid_email'){
		Swal.fire("Spiacente!", "Inserire indirizzo email valido", "error");
	}
</script>





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


<%@include file="include/footer.jsp" %>
</body>
</html>