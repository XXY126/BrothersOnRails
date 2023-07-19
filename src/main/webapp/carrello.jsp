<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%@ page import="java.util.*,model.Prodotto,model.Carrello"%>
 <% if(session.getAttribute("user")==null)
		response.sendRedirect("login.jsp");
 %>
<jsp:include page="include/head.jsp" flush="true"/>
<body>
	<jsp:include page="include/header.jsp" flush="true"/>
	<script src="scripts/carrello.js"></script>
	<script src="scripts/dynamicCode.js"></script>
	<script>
		document.addEventListener("DOMContentLoaded", dynamicCart("<%=request.getContextPath()%>/CartServlet?id=<%=request.getParameter("id")%>"));
	</script>
	
<main>
  <section id="container">
    <h2 class="mb-4">Carrello</h2>
    <div class="table-responsive">
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>Elimina</th>
            <th>Immagine</th>
            <th>Prodotto</th>
            <th>Prezzo</th>
            <th>Quantità</th>
            <th>Totale</th>
          </tr>
        </thead>
        <tbody id="dinamico">
          <!-- Il contenuto dinamico viene aggiunto tramite JavaScript -->
        </tbody>
      </table>
    </div>
  </section>

  <section id="bottom">
    <div id="cassa">
      <h5 class="mb-3">TOTALE</h5>
      <div class="totale mb-2">
        <h6>Prodotti: </h6>
        <p class="tot">&#8364 totale</p>
      </div>
      <div class="totale mb-2">
        <h6>Spedizione: </h6>
        <p>&#8364 10</p>
      </div>
      <hr>
      <div class="totale mb-2">
        <h6>Totale: </h6>
        <p class="totCumul">&#8364 totale</p>
      </div>
      <button onclick="checkout()" class="btn btn-primary"> Procedi al Pagamento</button>
    </div>
  </section>
</main>
<jsp:include page="include/footer.jsp" flush="true"/>	
</body>
</html>