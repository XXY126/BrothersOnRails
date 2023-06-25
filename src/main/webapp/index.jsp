<!DOCTYPE html>
<html>
<head>
  <title>Homepage</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

  <style>
    .btn-primary, .btn-primary:hover, .btn-primary:active, .btn-primary:visited {
    background-color: #ff0000 !important;
    }

    .product-row {
      padding: 40px 0;
      background-color: #f9f9f9;
    }

    .footer {
      background-color: #333;
      color: #fff;
      padding: 20px 0;
    }

    #logo{
      height: 150px;
      width: 150px;
    }

  </style>
</head>
<body>
  <!-- Barra superiore -->
  <header class="h-40 d-inline-block">
    <div class="container">
      <div class="row align-items-center">
        <div class="col-6 col-md-4">
          <img id="logo" src="logo.png" alt="Logo" height="40">
        </div>
        <div class="col-6 col-md-8 text-right justify-content-end">
          <a class="btn btn-danger" href="#" role="button">Link</a>
          <a class="btn btn-danger" href="#" role="button">Link</a>
          <a class="btn btn-danger" href="#" role="button">Link</a>
        </div>
      </div>
    </div>
  </header>

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
          <img src="treno1.jpeg" class="d-block w-100" alt="Slide 1">
        </div>
        <div class="carousel-item">
          <img src="treno2.jpeg" class="d-block w-100" alt="Slide 2">
        </div>
        <div class="carousel-item">
          <img src="treno3.jpg" class="d-block w-100" alt="Slide 3">
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
            <img src="treno1.jpeg" class="card-img-top" alt="Product 1">
            <div class="card-body">
              <h5 class="card-title">Product 1</h5>
              <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
              <a href="#" class="btn bg-danger text-white ">Buy Now</a>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card">
            <img src="treno2.jpeg" class="card-img-top" alt="Product 2">
            <div class="card-body">
              <h5 class="card-title">Product 2</h5>
              <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
              <a href="#" class="btn bg-danger text-white">Buy Now</a>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card">
            <img src="treno3.jpg" class="card-img-top" alt="Product 3">
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

  <!-- Footer -->
  <footer class="footer text-center">
    <div class="container">
      <p>&copy; 2023 Company Name. All rights reserved.</p>
    </div>
  </footer>

  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.0/dist/js/bootstrap.bundle.min.js"></script>
  <script>
    $(document).ready(function() {
      $('.carousel').carousel();
    });
  </script>
</body>
</html>
