<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.*, java.text.SimpleDateFormat, model.OrdiniList, model.Ordine, model.OrdineSingolo, model.Utente" %>
<%@ page import="java.util.*, model.Utente" %>
<%@ page import="java.util.*, model.OrdineSingolo" %>
<%
    Utente user = (Utente) session.getAttribute("user");
    if (user == null){
    	request.setAttribute("permesso", "negato");
        response.sendRedirect("index.jsp");
    }
    else if (!user.getAdmin()){
    	request.setAttribute("permesso", "negato");
        response.sendRedirect("index.jsp");
    }

%>
<jsp:include page="include/head.jsp" flush="true" />
<input type="hidden" id="statoOrdiniView" value="<%= request.getAttribute("statoOrdiniView")%>">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.all.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.min.css" rel="stylesheet">
<%String statoOrdiniView = (String) request.getAttribute("statoOrdiniView");%>
<script type ="text/javascript">
	if('<%= statoOrdiniView %>' == 'vuoto'){
		Swal.fire("Spiacente!", "L'email inserito non ha nessun ordine", "error");
	}
</script>
<body>
    <jsp:include page="include/header.jsp" flush="true" />
    <div class="container">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h2>Ricerca ordini</h2>
            	
            	
            <form class="d-flex" action="OrdiniServlet" method="GET">
            <p id="button"></p>
                <input class="form-control me-2" type="text" id="filtro" name="filtro" placeholder="Inserisci email...">
                <button class="btn btn-primary" type="submit">Invio</button>
            </form>
        </div>
        <div class="row">
            <%
                OrdiniList ordiniList = (OrdiniList) session.getAttribute("ordini");
                ArrayList<Ordine> ordini = (ArrayList<Ordine>) ordiniList.getOrdiniList();
                Collections.reverse(ordini);

                for (Ordine o : ordini) {

            %>
            <div class="col-md-6">
                <div class="card mb-3">
                    <div class="card-header">
                        <h4>Ordine ID: <%= o.getId() %></h4>
                        <p>Data: <%= o.getData() %></p>
                        <p> Utente : <%= o.getUserId() %> </p>
                    </div>
                    <div class="card-body">
                        <% for (OrdineSingolo os : o.getSingoli()) { %>
                        <div class="row g-0">
                            <div class="col-md-4">
                                <img class="orderImg img-fluid"
                                    src="/BrothersOnRails/images/<%= os.getProdotto().getImg() %>">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <h5 class="card-title"><%= os.getProdotto().getNome() %> x <%= os.getQuantita() %>
                                    </h5>
                                    <p class="card-text">Totale Prodotti: &#8364 <%= String.format("%.2f",
                                        os.getCosto()) %></p>
                                </div>
                            </div>
                        </div>
                        <% } %>
                    </div>
                    <div class="card-footer">
                        <p class="card-text">Totale: &#8364 <%= String.format("%.2f", o.getTotale()) %></p>
                    </div>
                </div>
            </div>
            <%
                    }

            %>
        </div>
    </div>
    <jsp:include page="include/footer.jsp" flush="true" />
</body>

</html>
