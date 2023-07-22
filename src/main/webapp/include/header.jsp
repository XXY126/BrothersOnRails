  <style>

    #logo{
      height: 150px;
      width: 150px;
    }
    
    
  </style>
  <%@ page import="java.util.*,model.Utente" %>
<!-- Barra superiore -->
<nav class="navbar navbar-light bg-light">
      <div class="col-12 col-md-4"> <!-- Modifica la classe della colonna -->
        <img id="logo" src="images/logo.jpeg" href="index.jsp" alt="Logo" height="40">
      </div>
      <div class="col-12 col-md-8 text-right justify-content-md-start"> <!-- Modifica la classe della colonna -->
        <a class="btn btn-danger" href="/BrothersOnRails/index.jsp" role="button">Home</a>

        
        <% Utente user = (Utente)session.getAttribute("user");%>
        <%if(user==null) {%>
            <a class="btn btn-danger" href="/BrothersOnRails/catalogo.jsp" role="button">Catalogo</a>
        	<a class="btn btn-danger" href="/BrothersOnRails/login.jsp" role="button">LogIn</a>
        	<a class="btn btn-danger" href="/BrothersOnRails/carrello.jsp" role="button">Carrello</a>
        <%} else { if(user.getAdmin()==true){%>
        	<a class="btn btn-danger" href="/BrothersOnRails/catalogoAdmin.jsp" role="button">AdminCatalogo</a>
        	<a class="btn btn-danger" href="/BrothersOnRails/ordiniAdmin.jsp" role="button">AdminOrdini</a>
        	<a class="btn btn-danger" href="/BrothersOnRails/LogOutServlet" role="button">LogOut</a>
        	<%}else{ %>
        	<a class="btn btn-danger" href="/BrothersOnRails/catalogo.jsp" role="button">Catalogo</a>
        	<a class="btn btn-danger" href="/BrothersOnRails/profilo.jsp" role="button">Profilo</a>
        	<a class="btn btn-danger" href="/BrothersOnRails/LogOutServlet" role="button">LogOut</a>
        	<a class="btn btn-danger" href="/BrothersOnRails/carrello.jsp" role="button">Carrello</a>
        <%}}%>
      </div>
      
      
</nav>
