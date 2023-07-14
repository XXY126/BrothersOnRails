    function updateTotal(input) {
      const row = input.parentNode.parentNode;
      const price = row.querySelector('td:nth-child(3)').innerText.replace('$', '');
      const quantity = input.value;
      const total = price * quantity;
      row.querySelector('.total').innerText = '$' + total;

      let grandTotal = 0;
      const totalElements = document.querySelectorAll('.total');
      for (let i = 0; i < totalElements.length; i++) {
        grandTotal += parseFloat(totalElements[i].innerText.replace('$', ''));
      }
      document.getElementById('grand-total').innerText = '$' + grandTotal;
    }