<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="model.Prodotto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/head.jsp" flush="true" />

<body>
<jsp:include page="include/header.jsp" flush="true" />

<main>
    <section id="prodotto">
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-6">
                    <img src="images/${p.img}" alt="${p.nome}" class="img-thumbnail">
                </div>
                <div class="col-md-6">
                    <h3>${p.nome}</h3>
                    <p class="costo">&#8364 ${p.getPrezzo()}</p>
                    <p>${p.getDescrizione()}</p>
                    <p>Quantità disponibile: ${p.quantita}</p>
                    <a href="AddToCartServlet?idProdotto=${p.idProdotto}"><button class="btn btn-primary">Aggiungi al carrello</button></a>
                </div>
            </div>
        </div>
    </section>
</main>

<jsp:include page="include/footer.jsp" flush="true" />
</body>
</html>
