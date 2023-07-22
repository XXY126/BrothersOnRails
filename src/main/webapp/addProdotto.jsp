<!DOCTYPE html>
<html>
<head>
<%@ page import="java.util.*, model.Utente" %>
	<%
    	Boolean isAdmin = (Boolean) session.getAttribute("admin");
    	if (isAdmin == null || !isAdmin) {
        	response.sendRedirect("index.jsp");
    	}
	%>

<input type="hidden" id="statusAggiunta" value="<%= request.getAttribute("statusAggiunta")%>">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.all.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.min.css" rel="stylesheet">
<%String statusAggiunta = (String) request.getAttribute("statusAggiunta");%>
<script type ="text/javascript">

		if('<%= statusAggiunta %>' == 'failed')
			Swal.fire("Spiacente!", "ERRORE DURANTE AGGIUNTA PRODOTTO", "error");
		else if('<%= statusAggiunta %>' == 'success')
				Swal.fire("Ottimo!", "AGGIUNTA DEL PRODOTTO ANDATA A BUON FINE", "success");
	}
	
</script>

    <meta charset="UTF-8">
    <title>Nuovo Prodotto</title>
    <%@include file="include/head.jsp" %>

</head>
<body>
<jsp:include page="include/header.jsp" flush="true"/>

    <div class="container mt-4">
        <h1>Nuovo Prodotto</h1>
        <form action="AddProdottoServlet" method="POST" enctype="multipart/form-data">
            <div class="form-group">
                <label for="nome">Nome Prodotto</label>
                <input type="text" class="form-control" id="nome" name="nome" required>
            </div>
            <div class="form-group">
                <label for="quantita">Quantità</label>
                <input type="number" class="form-control" id="quantita" name="quantita" required>
            </div>
            <div class="form-group">
                <label for="costo">Costo</label>
                <input type="number" class="form-control" id="costo" name="costo" required>
            </div>
            <div class="form-group">
                <label for="immagine">Immagine</label>
                <input type="file" class="form-control" id="immagine" name="immagine" required>
            </div>
            <div class="form-group">
                <label for="categoria">Categoria</label>
                <select class="form-control" id="categoria" name="categoria" required>
                    <option value="" disabled selected>Seleziona una categoria</option>
                    <!-- Le opzioni di categoria verranno caricate dinamicamente tramite lo script JavaScript -->
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Aggiungi Prodotto</button>
        </form>
    </div>
	<%@include file="include/footer.jsp" %>
</body>
<script type="text/javascript">

	document.addEventListener('DOMContentLoaded', function () {
		
    	// Carica dinamicamente le categorie nel menu a tendina "categoria" utilizzando la servlet "CategoriaServlet"
    	fetch('CategoriaServlet')
        	.then(response => response.json())
        	.then(categorie => {
            	const categoriaSelect = document.getElementById('categoria');
            	categorie.forEach(categoria => {
                const option = document.createElement('option');
                option.value = categoria;
                option.textContent = categoria;
                categoriaSelect.appendChild(option);
            });
        })
        .catch(error => console.error(error));
	});

</script>


</html>
