<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.apache.commons.text.StringEscapeUtils"%>
<%@ page import="java.util.*,model.Prodotto,model.Catalogo"%>
<%@ page import="com.google.gson.Gson"%>

<head>
<title>Catalogo</title>
<jsp:include page="/include/head.jsp" flush="true" />
</head>
<%@include file="include/header.jsp" %>
<script src="scripts/jquery-3.7.0.min.js"></script>
<script src="scripts/dynamicCode.js"></script>

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