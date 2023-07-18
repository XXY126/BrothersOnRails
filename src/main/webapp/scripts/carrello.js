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
    
function addCart(quantita, id) {
	if (quantita != 0)
		window.location.href = "carrello.jsp?isbn=" + id;
}

function totaleParziale() {
	let product, elem1, elem2, costo, quantita, totParz, tot = 0;

	product = document.getElementById("dinamico");
	elem1 = product.getElementsByClassName("costo");
	elem2 = document.querySelectorAll('.quantita');

	for (let i = 0; i < elem1.length; i++) {
		totParz = 0;
		costo = parseFloat(elem1[i].textContent.split(' ')[1]);
		quantita = parseInt(elem2[i].value)
		totParz += costo * quantita;
		tot += totParz;

		product.getElementsByClassName("totProd")[i].innerHTML = "&#8364 " + totParz.toFixed(2);
	}

	let cassa, spedizione = 10;

	cassa = document.getElementById("cassa");
	cassa.getElementsByClassName("tot")[0].innerHTML = "&#8364 " + tot.toFixed(2);
	if (tot == 0) // se non ci sono elementi nel carrello il totale è 0
		spedizione = 0;
	cassa.getElementsByClassName("totCumul")[0].innerHTML = "&#8364 " + (tot + spedizione).toFixed(2);
}

function eliminaRiga(button) {
	let row = button.parentNode.parentNode;
	let idProdotto = button.getAttribute("data-isbn");
	let pathArray = window.location.pathname.split('/');
	let contextPath = '/' + pathArray[1];
	let url = contextPath + "/RimuoviProdotto";

	$.ajax({
		url: url,
		type: 'POST',
		data: { isbn: idProdotto },
		success: function(response) {
			// Rimuovi la riga del prodotto dal carrello nell'interfaccia utente
			row.parentNode.removeChild(row);

			dynamicCart(contextPath + "/CartServlet");
			// Aggiorna i totali
			totaleParziale();
		},
		error: function(xhr, status, error) {
			console.error(error);
		}
	});
}


function checkout(url) {
	let element = document.getElementsByClassName("totCumul")[0];
	let text = element.textContent;
	let numericValue = parseFloat(text.split(' ')[1]);

	let query = document.querySelectorAll('.quantita');
	let quantita = "";
	for (let i = 0; i < query.length; i++) {
		quantita += query[i].value;
		if (query[i + 1] != null)
			quantita += ",";
	}

	if (numericValue > 0)
		window.location.href = "AddOrdineServlet?totale=" + numericValue + "&quantita=" + quantita;
}