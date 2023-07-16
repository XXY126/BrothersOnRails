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
	<main>
		<section id="container">
			<div id="filtri">
				<h2>Filtra Per</h2>
				<table>
					<tbody id="categorie">

					</tbody>
				</table>
			</div>
		
<div id="filtroBottone">Mostra filtri</div>
		<section id="prodotti">
			<div id="schedeProdotto"></div>
		</section>
	</section>
	</main>
	<jsp:include page="/include/footer.jsp" flush="true" />
</body>
</html>