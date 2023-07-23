<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.*, java.text.SimpleDateFormat, model.OrdiniList, model.Ordine, model.OrdineSingolo, model.Utente" %>
<%@ page import="java.util.*, model.Utente" %>
<%@ page import="java.util.*, model.OrdineSingolo" %>
<%
    // Recupera l'oggetto Utente dalla sessione
    Utente user = (Utente) session.getAttribute("user");
    
    // Verifica se l'utente è autenticato e se è un amministratore
    if (user == null || !user.getAdmin()) {
        // Se l'utente non è autenticato o non è un amministratore, reindirizza alla pagina di login
        request.setAttribute("permesso", "negato");
        response.sendRedirect("index.jsp");
    }
%>
<%@ page import="java.util.*, model.Utente" %>
<%@ page import="java.util.*, model.OrdineSingolo" %>
<%
    // Recupera lo stato degli ordini dalla richiesta
    String statoOrdiniView = (String) request.getAttribute("statoOrdiniView");
%>
<!-- Includi l'head del documento -->
<jsp:include page="include/head.jsp" flush="true" />
<!-- Includi l'header della pagina -->
<jsp:include page="include/header.jsp" flush="true" />

<!-- Script e link per lo stile di SweetAlert2 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.all.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.min.css" rel="stylesheet">

<!-- Script per visualizzare un messaggio di errore se la ricerca per email non restituisce risultati -->
<script type="text/javascript">
    if('<%= statoOrdiniView %>' == 'vuoto'){
        Swal.fire("Spiacente!", "L'email inserito non ha nessun ordine", "error");
    }
</script>

<body>
    <div class="container">
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h2>Ricerca ordini</h2>
            <!-- Form per la ricerca per email -->
            <form class="d-flex" action="OrdiniServlet" method="GET">
                <p id="button"></p>
                <input class="form-control me-2" type="text" id="filtro" name="filtro" placeholder="Inserisci email...">
                <button class="btn btn-primary" type="submit">Invio</button>
            </form>
        </div>
        <!-- Form per la ricerca per date di inizio e fine -->
        <form class="d-flex mt-3" action="FiltroDataServlet" method="GET">
            <p id="button"></p>
            <input class="form-control me-2" type="date" id="dataInizio" name="dataInizio" required>
            <input class="form-control me-2" type="date" id="dataFine" name="dataFine" required>
            <button class="btn btn-primary" type="submit">Cerca per date</button>
        </form>
        <div class="row">
            <%
                // Recupera l'elenco degli ordini dalla sessione
                OrdiniList ordiniList = (OrdiniList) session.getAttribute("ordini");
                ArrayList<Ordine> ordini = (ArrayList<Ordine>) ordiniList.getOrdiniList();
                // Inverti l'ordine degli ordini per visualizzare i più recenti per primi
                Collections.reverse(ordini);

                // Itera attraverso gli ordini e visualizza le informazioni
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
    <!-- Includi il footer della pagina -->
    <jsp:include page="include/footer.jsp" flush="true" />
</body>
</html>
