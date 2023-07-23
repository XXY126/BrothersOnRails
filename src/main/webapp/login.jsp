<!DOCTYPE html>
<html>
<head>
<%@include file="include/head.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.all.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.min.css" rel="stylesheet">
<%String status = (String) request.getAttribute("status");%>
<%String statusCarrello = (String) request.getAttribute("statusCarrello");%>
<script type ="text/javascript">
	if('<%= statusCarrello %>' == 'true'){
		Swal.fire("Spiacente!", "E' obbligatoria fare l'accesso", "error");
	}
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
            <input type="text" class="form-control" id="email" name="email" required>
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
</body>
</html>