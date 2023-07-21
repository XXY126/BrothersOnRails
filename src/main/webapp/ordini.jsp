<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.*, java.text.SimpleDateFormat,model.OrdiniList,model.Ordine,model.OrdineSingolo, model.Utente" %>    
<%@ page import="java.util.*,model.Utente"%>
<%@ page import="java.util.*,model.OrdineSingolo"%>
<% 
    Utente user = (Utente) session.getAttribute("user");
    if(user == null)
        response.sendRedirect("login.jsp");
%>
<jsp:include page="include/head.jsp" flush="true"/>
<script>
    <% 
        OrdiniList ordiniList = (OrdiniList) session.getAttribute("ordini");
        ArrayList<Ordine> ordini = (ArrayList<Ordine>) ordiniList.getOrdiniList();
        Collections.reverse(ordini);
        String stato = "Annullato";
            
        String contenutoHtml = "";
        for(Ordine o : ordini){
            if(o.getUserId() == user.getEmail()){
                contenutoHtml += "<div class=\"card mb-3\">";
                for(OrdineSingolo os : o.getSingoli()){
                    contenutoHtml += "<div class=\"row g-0\">";
                    contenutoHtml += "<div class=\"col-md-4\">";
                    contenutoHtml += "<img class=\"orderImg img-fluid\" src=\"/BrothersOnRails/images/"+ os.getProdotto().getImg() +"\">";
                    contenutoHtml += "</div>";
                    contenutoHtml += "<div class=\"col-md-8\">";
                    contenutoHtml += "<div class=\"card-body\">";
                    contenutoHtml += "<h5 class=\"card-title\">" + os.getProdotto().getNome() + " x" + os.getQuantita() +"</h5>";
                    contenutoHtml += "<p class=\"card-text\">Totale Prodotti: &#8364 " + String.format("%.2f", os.getCosto()) + "</p>";
                    contenutoHtml += "</div>";
                    contenutoHtml += "</div>";
                    contenutoHtml += "</div>";
                }
                contenutoHtml += "<div class=\"card-footer\">";
                contenutoHtml += "<p class=\"card-text\">Totale: &#8364 " + String.format("%.2f", o.getTotale()) + "</p>";
                contenutoHtml += "</div>";
                contenutoHtml += "</div>";    
            }
        }
    %> 

    const content = '<%=contenutoHtml.replace("'", "\\'").replace("\n", "\\n")%>';
    $(document).ready(function(){
        document.getElementById("container").innerHTML = content;
    });
</script>

<body>
<jsp:include page="include/header.jsp" flush="true"/>
    <div class="container">
        <h2>I miei ordini</h2>
        <div id="container" class="card-columns">
        </div>
    </div>
<jsp:include page="include/footer.jsp" flush="true"/>
</body>
</html>
