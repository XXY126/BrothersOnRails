<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <%
        Boolean isAdmin = (Boolean) session.getAttribute("admin");
        if (isAdmin == null || !isAdmin) {
            response.sendRedirect("index.jsp");
        }
    %>
<head>
    <title>Modifica Prodotto</title>
    <jsp:include page="include/head.jsp" flush="true"/>
</head>
<body>
    <jsp:include page="include/header.jsp" flush="true"/>
    <div class="container mt-4">
        <h1>Modifica Prodotto</h1>
        <form action="ModificaProdottoServlet" method="post" onsubmit="return checkForm()">
            <input type="hidden" name="idProdotto" value="${param.id}" />
            <div class="mb-3">
                <label for="descrizione" class="form-label">Descrizione:</label>
                <input type="text" class="form-control" name="descrizione" value="${param.descrizione}" />
            </div>
            <div class="mb-3">
                <label for="categoria" class="form-label">Categoria:</label>
                <input type="text" class="form-control" name="categoria" value="${param.categoria}" />
            </div>
            <div class="mb-3">
                <label for="quantita" class="form-label">Quantità:</label>
                <input type="number" class="form-control" name="quantita" value="${param.quantita}" />
            </div>
            <div class="mb-3">
                <label for="prezzo" class="form-label">Prezzo:</label>
                <input type="number" class="form-control" name="prezzo" value="${param.prezzo}" step="0.01" />
            </div>
            <div class="mb-3">
                <label for="img" class="form-label">Immagine:</label>
                <img src="images/${param.img}" />
            </div>
            
            <button type="submit" class="btn btn-primary">Salva Modifiche</button>
        </form>
    </div>
    <jsp:include page="include/footer.jsp" flush="true"/>

    <!-- Includi le librerie JavaScript di Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Includi le librerie JavaScript di SweetAlert 2 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.15.6/dist/sweetalert2.all.min.js"></script>
    <script>
        function checkForm() {
            // Controlla se almeno un campo è stato modificato
            var form = document.getElementsByTagName("form")[0];
            var hasChanged = false;

            for (var i = 0; i < form.elements.length; i++) {
                var input = form.elements[i];
                if (input.type !== "hidden" && input.value.trim() !== input.defaultValue.trim()) {
                    hasChanged = true;
                    break;
                }
            }

            if (!hasChanged) {
                // Visualizza l'avviso con SweetAlert 2 se nessuna modifica è stata effettuata
                Swal.fire({
                    icon: 'warning',
                    title: 'Attenzione!',
                    text: 'Nessuna modifica effettuata.',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: 'OK'
                });
                
                // Blocca l'invio del form
                return false;
            }

            // Procedi con l'invio del form se ci sono state modifiche
            return true;
        }
    </script>
</body>
</html>
