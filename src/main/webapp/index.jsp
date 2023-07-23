<!DOCTYPE html>
<html>
<head>
  <title>Homepage</title>
  <%@include file="include/head.jsp" %>
  <link rel="stylesheet" href="CSS/index.css">
</head>
<body>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.all.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.min.css" rel="stylesheet">
<%String status = (String) request.getAttribute("status");%>
<%String permesso = (String) request.getAttribute("permesso");%>
<script type ="text/javascript">
	if('<%= status %>' == 'success'){
		Swal.fire("Benvenuto!", "Accesso avvenuto con successo", "success");
	} else if('<%= status %>' == 'logout'){
		Swal.fire("LogOut", "LogOut avvenuto con successo", "success");
	}
	if('<%=permesso%>' == 'negato')
		Swal.fire("LogOut", "LogOut avvenuto con successo", "success");
</script>


<!-- Includo l'header, la barra superiore di ricerca -->
<%@include file="include/header.jsp"%>

  <!-- Slider di immagini -->
  <section class="slider-image center-block">
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="images/treno1.jpeg" class="d-block w-100" alt="Slide1">
        </div>
        <div class="carousel-item">
          <img src="images/treno2.jpeg" class="d-block w-100" alt="Slide2">
        </div>
        <div class="carousel-item">
          <img src="images/treno3.jpeg" class="d-block w-100" alt="Slide3">
        </div>
      </div>
      <a class="carousel-control-prev" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div>
  </section>

  <!-- Riga dei prodotti -->
  <section class="product-row center-block" id="product-row">
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <div class="card">
            <img src="images/treno1.jpeg" class="card-img-top" alt="Product 1">
            <div class="card-body">
              <h5 class="card-title">Product 1</h5>
              <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
              <a href="#" class="btn bg-danger text-white ">Buy Now</a>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card">
            <img src="images/treno2.jpeg" class="card-img-top" alt="Product 2">
            <div class="card-body">
              <h5 class="card-title">Product 2</h5>
              <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
              <a href="#" class="btn bg-danger text-white">Buy Now</a>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card">
            <img src="images/treno3.jpeg" class="card-img-top" alt="Product 3">
            <div class="card-body">
              <h5 class="card-title">Product 3</h5>
              <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
              <a href="#" class="btn bg-danger text-white">Buy Now</a>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    
  </section>
  <%@include file="include/footer.jsp" %>
  <script>
    $(document).ready(function() {
      $('.carousel').carousel();
    });
    
    
  </script>
</body>
</html>
