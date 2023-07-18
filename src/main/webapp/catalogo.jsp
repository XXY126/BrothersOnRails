<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.commons.text.StringEscapeUtils"%>
<%@ page import="java.util.*,model.Prodotto,model.Catalogo"%>
<%@ page import="com.google.gson.Gson"%>

<head>
<title>Catalogo</title>
<jsp:include page="/include/head.jsp" flush="true" />
<script src="scripts/jquery-3.7.0.js"></script>
<script src="scripts/dynamicCode.js"></script>
</head>


	<script>
		$(document).ready(function() {
			dynamicCatalog("<%=request.getContextPath()%>/CatalogoServlet");
			dynamicCategorie("<%=request.getContextPath()%>/CategoriaServlet");
		});
	</script>
	
<body>
	<jsp:include page="/include/header.jsp" flush="true"/>
    <main class="container mt-4">
        <div class="row">
            <div class="col-md-3">
                <div class="card mb-4">
                    <div class="card-header">
                        <h5 class="mb-0">Filtra Per</h5>
                    </div>
                    <div class="card-body">
                        <table class="table table-sm">
                            <tbody id="categorie">
                            <!-- Le categorie verranno caricate dinamicamente qui-->
                            </tbody>
                        </table>
                    </div>
                </div>
                <button class="btn btn-primary">Mostra filtri</button>
            </div>
            <div class="col-md-9">
                <div class="row" id="schedeProdotto">
                    <!-- i prodotti verrano caricati dinamicamente qui -->
                </div>
            </div>
        </div>
    </main>
	<jsp:include page="/include/footer.jsp" flush="true" />
</body>
</html>