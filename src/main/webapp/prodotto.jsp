<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.Prodotto" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="include/head.jsp" flush="true" />

<body>
    <jsp:include page="include/header.jsp" flush="true" />

    <main>
        <section id="prodotto">
            <div class="container mt-5">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card mb-3">
                            <div class="row g-0">
                                <div class="col-md-4">
                                    <img src="images/${p.img}" alt="${p.nome}" class="img-thumbnail">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h3 class="card-title">${p.nome}</h3>
                                        <p class="costo">&#8364 ${p.getPrezzo()}</p>
                                        <p class="card-text">Quantità disponibile: ${p.quantita}</p>
                                        <a href="AddToCartServlet?idProdotto=${p.idProdotto}" class="btn btn-primary">Aggiungi al carrello</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Descrizione</h4>
                                <p class="card-text">${p.getDescrizione()}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>

    <jsp:include page="include/footer.jsp" flush="true" />
</body>
</html>
