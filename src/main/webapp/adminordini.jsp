<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.*, java.text.SimpleDateFormat, model.OrdiniList, model.Ordine, model.OrdineSingolo, model.Utente" %>
<%@ page import="java.util.*, model.Utente" %>
<%@ page import="java.util.*, model.OrdineSingolo" %>
<%
    Utente user = (Utente) session.getAttribute("user");
    if(user == null)
        response.sendRedirect("login.jsp");
    else if (!user.getAdmin())
        response.sendRedirect("index.jsp");
%>
<jsp:include page="include/head.jsp" flush="true"/>

<body>
<jsp:include page="include/header.jsp" flush="true"/>
<div class="container">
    <h2>I miei ordini</h2>
    <div class="row">
        <% 
            OrdiniList ordiniList = (OrdiniList) session.getAttribute("ordini");
            ArrayList<Ordine> ordini = (ArrayList<Ordine>) ordiniList.getOrdiniList();
            Collections.reverse(ordini);

            for(Ordine o : ordini){
                if(o.getUserId().equals(user.getEmail())){
        %>
        <div class="col-md-6">
            <div class="card mb-3">
                <div class="card-header">
                    <h4>Ordine ID: <%= o.getId() %></h4>
                    <p>Data: <%= o.getData() %></p>
                </div>
                <div class="card-body">
                    <% for(OrdineSingolo os : o.getSingoli()){ %>
                    <div class="row g-0">
                        <div class="col-md-4">
                            <img class="orderImg img-fluid" src="/BrothersOnRails/images/<%= os.getProdotto().getImg() %>">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body">
                                <h5 class="card-title"><%= os.getProdotto().getNome() %> x <%= os.getQuantita() %></h5>
                                <p class="card-text">Totale Prodotti: &#8364 <%= String.format("%.2f", os.getCosto()) %></p>
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
            }
        %>
    </div>
</div>
<jsp:include page="include/footer.jsp" flush="true"/>
</body>
</html>
