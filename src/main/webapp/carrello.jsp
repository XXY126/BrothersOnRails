<!DOCTYPE html>
<html lang="en">
<head>
<%@include file="/include/head.jsp" %>
  <title>Carrello</title>
  <style>
    .quantity-input {
      width: 50px;
      text-align: center;
    }
  </style>
  <script src="scripts/carrello.js"></script>
</head>
<%@include file="/include/header.jsp" %>
<body>
  <div class="container">
    <h1>Carrello</h1>

    <table class="table table-bordered">
      <thead>
        <tr>
          <th>Prodotto</th>
          <th>Quantità</th>
          <th>Prezzo</th>
          <th>Totale</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>Prodotto 1</td>
          <td>
            <input type="number" min="1" class="form-control quantity-input" value="1" onchange="updateTotal(this)">
          </td>
          <td>$10</td>
          <td class="total">$10</td>
        </tr>
        <tr>
          <td>Prodotto 2</td>
          <td>
            <input type="number" min="1" class="form-control quantity-input" value="1" onchange="updateTotal(this)">
          </td>
          <td>$15</td>
          <td class="total">$15</td>
        </tr>
      </tbody>
    </table>

    <h3>Totale: <span id="grand-total">$25</span></h3>
    <button class="btn btn-primary">Checkout</button>
  </div>
  <%@include file="/include/footer.jsp" %>
</body>
</html>