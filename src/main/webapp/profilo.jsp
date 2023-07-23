<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.*, model.Utente" %>

<%
Utente user = (Utente) session.getAttribute("user");
int flag = 0;
if (user == null) {
 	request.setAttribute("statusCarrello", "profilo");
	RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	dispatcher.forward(request, response);
} else if (user.getAdmin()) {
    flag = 1;
}
%>

<jsp:include page="include/head.jsp" flush="true" />

<body>
    <jsp:include page="include/header.jsp" flush="true" />

    <div class="container mt-5">
        <h2 class="text-center">Il mio Account</h2>
        <div class="row mt-4 justify-content-center">
            <div class="col-md-6">
                <div class="card mb-4">
                    <div class="card-header">
                        Dati personali
                    </div>
                    <div class="card-body">
                        <p><strong>Email:</strong> <%= user.getEmail() %></p>
                        <p><strong>Nome:</strong> <%= user.getNome() %></p>
                        <p><strong>Cognome:</strong> <%= user.getCognome() %></p>
                        <!-- Aggiungi altri dati personali se necessario -->
                    </div>
                </div>

                <div class="card">
                    <div class="card-header">
                        Ordini
                    </div>
                    <div class="card-body text-center">
                        <a class="btn btn-primary" href="OrdiniServlet">Visuallzza Ordini</a>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="card mb-4">
                    <div class="card-header">
                        Indirizzo
                    </div>
                    <div class="card-body text-center">
                        <a class="btn btn-primary" href="indirizzo.jsp">Visualizza Indirizzo</a>
                    </div>
                </div>
                
                <!-- Mostra le funzionalità aggiuntive solo agli utenti admin -->
                <% if (flag == 1) { %>
                    <div class="card mt-4">
                        <div class="card-header">
                            Funzionalità Admin
                        </div>
                        <div class="card-body">
                            <ul class="list-group list-group-flush">
                                <li class="list-group-item">
                                    <a href="aggiungiProdotto.jsp">Aggiungi Prodotto</a>
                                    <span class="badge badge-pill badge-primary">(ADMIN)</span>
                                </li>
                                <li class="list-group-item">
                                    <a href="modificaProdotto.jsp">Modifica Prodotto</a>
                                    <span class="badge badge-pill badge-primary">(ADMIN)</span>
                                </li>
                                <li class="list-group-item">
                                    <a href="eliminaProdotto.jsp">Elimina Prodotto</a>
                                    <span class="badge badge-pill badge-primary">(ADMIN)</span>
                                </li>
                                <li class="list-group-item">
                                    <a href="controllaordini.jsp">Controlla Ordini</a>
                                    <span class="badge badge-pill badge-primary">(ADMIN)</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                <% } %>
            </div>
        </div>
    </div>
    
<script>
    // Verifica se è presente il parametro nel URL
    function getUrlParam(param) {
        const urlParams = new URLSearchParams(window.location.search);
        return urlParams.get(param);
    }

    // Funzione per creare e mostrare l'alert Bootstrap
    function showAlert(message) {
        const alertDiv = document.createElement("div");
        alertDiv.classList.add("alert", "alert-danger", "my-3");
        alertDiv.textContent = message;

        const closeButton = document.createElement("button");
        closeButton.classList.add("btn-close");
        closeButton.setAttribute("data-bs-dismiss", "alert");
        alertDiv.appendChild(closeButton);

        const container = document.querySelector(".container");
        container.insertBefore(alertDiv, container.firstChild);
    }

    // Attendi che il documento HTML sia completamente caricato prima di eseguire lo script
    document.addEventListener("DOMContentLoaded", function () {
        // Controlla se è presente il parametro "errore indirizzo" nel URL
        const errorMessage = getUrlParam('errore');

        // Se il parametro "errore indirizzo" è presente, mostra l'alert
        if (errorMessage && errorMessage === "indirizzo") {
            showAlert("aggiungere un indirizzo");
        }
    });
</script>

    <jsp:include page="include/footer.jsp" flush="true" />
</body>
</html>
