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
    document.addEventListener('DOMContentLoaded', function() {
      var filtroBottone = document.getElementById('filtroBottone');
      var filtri = document.getElementById('filtri');

      filtroBottone.addEventListener('click', function() {
        if (filtri.style.display === 'none') {
          filtri.style.display = 'block';
          filtroBottone.textContent = 'Nascondi filtri';
        } else {
          filtri.style.display = 'none';
          filtroBottone.textContent = 'Mostra filtri';
        }
      });
    });
</script>

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
                <!-- Add Bootstrap Card for Filters -->
                <div class="card mb-4">
                    <div class="card-header">
                        <h5 class="mb-0">Filtra Per</h5>
                    </div>
                    <div class="card-body">
                        <table class="table table-sm">
                            <tbody id="categorie">
                                <!-- Categories will be dynamically added here -->
                            </tbody>
                        </table>
                    </div>
                </div>
                <button class="btn btn-primary" id="filtroBottone">Mostra filtri</button>
            </div>
            <div class="col-md-9">
                <div class="row" id="schedeProdotto">
                    <!-- Product cards will be dynamically added here -->
                </div>
            </div>
        </div>
    </main>
	<jsp:include page="/include/footer.jsp" flush="true" />
</body>
</html>