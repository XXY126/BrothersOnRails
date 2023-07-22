<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%
    	Boolean isAdmin = (Boolean) session.getAttribute("admin");
    	if (isAdmin == null || !isAdmin) {
        	response.sendRedirect("index.jsp");
    	}
	%>
    <meta charset="UTF-8">
    <title>Amministratore - Catalogo Prodotti</title>
    <%@include file="include/head.jsp" %>
</head>
<body>
	<%@include file="include/header.jsp" %>
    <div class="container mt-4">
        <h1>Amministratore - Catalogo Prodotti</h1>
        
        <a href="addProdotto.jsp" class="btn btn-primary mb-3">Aggiungi Prodotto</a>

        
        <c:forEach var="prodotto" items="${catalogo}">
            <div class="card mb-3">
                <div class="row g-0">
                    <div class="col-md-4">
                        <img src="images/${prodotto.img}" alt="${prodotto.nome}" class="img-fluid">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body">
                            <h5 class="card-title">${prodotto.nome}</h5>
                            <p class="card-text">${prodotto.descrizione}</p>
                            <p class="card-text">Categoria: ${prodotto.categoria}</p>
                            <p class="card-text">Quantità disponibile: ${prodotto.quantita}</p>
                            <p class="card-text">Prezzo: ${prodotto.prezzo}</p>
                            
                            <a onclick="confirm(${prodotto.idProdotto})" class="btn btn-danger">Elimina</a>
                            <a href="modifica_prodotto?id=${prodotto.idProdotto}" class="btn btn-primary">Modifica</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <%@include file="include/footer.jsp" %>
<script type="text/javascript">
    function confirm(idProdotto) {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                // Effettua una richiesta AJAX con Fetch API
 				fetch('deleteProdottoServlet', {
  					method: 'POST',
  					headers: {
    					'Content-Type': 'application/x-www-form-urlencoded'
  					},
  					body: 'idProdotto=' + encodeURIComponent(idProdotto),
  					credentials: 'include'
					})
					.then(response => response.text())
					.then(data => {
						console.log('AAAAAAA');
  					// Gestisci la risposta dalla servlet
  					if (data === 'success') {
    				// L'eliminazione è avvenuta con successo, puoi aggiornare la pagina
    					console.log('Prodotto eliminato con successo');
    					location.reload(); // Ricarica la pagina dopo l'eliminazione
  					} else {
    				// Si è verificato un errore durante l'eliminazione del prodotto
    					console.log('Errore durante l\'eliminazione del prodotto');
  					}
					})
					.catch(error => console.error(error));


                Swal.fire(
                    'Deleted!',
                    'Your file has been deleted.',
                    'success'
                );
            }
        });
    }
</script>

</body>
</html>
