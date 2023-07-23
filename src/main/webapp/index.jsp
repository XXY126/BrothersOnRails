<!DOCTYPE html>
<html>

<head>
    <title>Pagina di Home</title>
    <%@include file="include/head.jsp" %>
    <!-- Aggiungi qui il link al file CSS di Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Aggiungi qui il link al tuo file CSS personalizzato -->
    <link rel="stylesheet" href="CSS/index.css">
    <!-- Aggiungi qui le librerie di SweetAlert per mostrare popup di notifica -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10.16.3/dist/sweetalert2.all.min.js"></script>
</head>

<body>
    <!-- Header -->
    <header>
        <%@include file="include/header.jsp"%>
    </header>

    <!-- Slider per le foto -->
   <div id="slider" class="carousel slide" data-ride="carousel" data-interval="2000"> <!-- Imposta l'intervallo a 2000 millisecondi -->
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="images/treno1.jpeg" alt="Slide 1">
                <div class="carousel-caption">
                    <h2>Brothers on Rails</h2>
                </div>
            </div>
            <div class="carousel-item">
                <img src="images/treno2.jpeg" alt="Slide 2">
                <div class="carousel-caption">
                    <h2>Brothers on Rails</h2>
                </div>
            </div>
            <div class="carousel-item">
                <img src="images/treno3.jpeg" alt="Slide 3">
                <div class="carousel-caption">
                    <h2>Brothers on Rails</h2>
                </div>
            </div>
        </div>
        <!-- ... Il resto del codice del carousel ... -->
    </div> 
    
    <!-- Parallax Section -->
    <section class="parallax-section">
        <div class="parallax-image"></div>
        <div class="parallax-content">
            <h2><i style="color: black;">Prodotti</i></h2>
        <div class="container" id="pablo">
		    <div class="row justify-content-center align-items-center" id="cardsContainer">

		    </div>
		</div>

        </div>
        
    </section>

    <!-- Footer -->
    <footer>
        <!-- Inserisci qui il contenuto del footer -->
          <%@include file="include/footer.jsp" %>
    </footer>


	<script type="text/javascript">
        $(document).ready(function() {
            $.get("CardsServlet", function(data) {
                $("#cardsContainer").html(data);
            });
        });
    </script>
    <!-- Script per il login -->
    <script type="text/javascript">
        // Codice JavaScript per mostrare le notifiche di login/logout
        <%-- Utilizzo dei tag JSP per ottenere lo status del login e il permesso --%>
        <%String status = (String) request.getAttribute("status");%>
        <%String permesso = (String) request.getAttribute("permesso");%>
        if ('<%= status %>' == 'success') {
            Swal.fire("Benvenuto!", "Accesso avvenuto con successo", "success");
        } else if ('<%= status %>' == 'logout') {
            Swal.fire("LogOut", "LogOut avvenuto con successo", "success");
        }
        if ('<%= permesso %>' == 'negato')
            Swal.fire("Accesso Negato", "Non hai i permessi per accedere a questa pagina.", "error");
    </script>

    <!-- Aggiungi qui i link alle librerie JavaScript di Bootstrap -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.10.2/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    
</body>

</html>