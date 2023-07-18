/*import{} from "jquery-3.7.0.js"*/


function dynamicCatalog(url) {
	$.ajax({
		url: url,
		type: 'GET',
		contentType: 'application/json; charset=utf-8'
	}).done((response) => {
		response = JSON.parse(response);
		let contenutoHtml = "";

        for (const p of response) {
            contenutoHtml += '<div class="col-md-4 mb-4">';
            contenutoHtml += '<div class="card scheda" data-categoria="' + p.categoria + '">';
            contenutoHtml += '<a href="ProductServlet?isbn=' + p.idProdotto + '"><img src="/BrothersOnRails/images/' + p.img + '" class="card-img-top trash"></a>';
            contenutoHtml += '<div class="card-body">';
            contenutoHtml += '<h4 class="card-title pname">' + p.nome + '</h4>';
            contenutoHtml += '<p class="card-text">&#8364 ' + p.prezzo.toFixed(2) + '</p>';
            contenutoHtml += '<a href="#" onclick="addCart(' + p.quantita + ', \'' + p.idProdotto + '\')" class="btn btn-primary">Carrello</a>';
            contenutoHtml += '</div>';
            contenutoHtml += '</div>';
            contenutoHtml += '</div>';
        }


		$("#schedeProdotto").append(contenutoHtml);
	});
}

function dynamicCategorie(url) {
	$.ajax({
		url: url,
		type: 'GET',
		contentType: 'application/json; charset=utf-8'
	}).done((response) => {
		response = JSON.parse(response);

		let filtriCategoria = "<tr> <td> <h4> Categoria </h4> </td> </tr>";
		for (const categoria of response) {
			filtriCategoria += "<tr> <td>";
			filtriCategoria += "<input type=\"checkbox\" class=\"cat\"  value=\"" + categoria + "\"name=\"categoria\" onchange=\"searchAndFilter()\">";
			filtriCategoria += "<label class=\"secondset\">" + categoria + "</label>";
			filtriCategoria += "</td> </tr>";
		}
		$("#categorie").append(filtriCategoria);
	});
}

function searchAndFilter() {
  let input, filter, schede, product;
  input = document.getElementById("search-input");
  filter = input.value.toUpperCase();
  schede = document.getElementById("schedeProdotto");
  product = schede.querySelectorAll(".scheda");

  const selectedCategories = Array.from(document.querySelectorAll('input.cat:checked')).map(input => input.value);
  const selectedGenres = Array.from(document.querySelectorAll('input.gen:checked')).map(input => input.value);

  for (const item of product) {
    let a = item.querySelector(".pname");
    let textValue = a.textContent || a.innerText;
    const prodottoCategoria = item.dataset.categoria;
    const prodottoGenere = item.dataset.genere;

    const nameMatches = textValue.toUpperCase().indexOf(filter) > -1;
    const categoryMatches = selectedCategories.length === 0 || selectedCategories.includes(prodottoCategoria);
    const genreMatches = selectedGenres.length === 0 || selectedGenres.includes(prodottoGenere);

    if (filter && (selectedCategories.length > 0 || selectedGenres.length > 0)) {
      // Se è presente una ricerca per nome e filtri attivi, considera solo i prodotti che corrispondono a entrambi
      if (nameMatches && categoryMatches && genreMatches) {
        item.style.display = "";
      } else {
        item.style.display = "none";
      }
    } else if (filter) {
      // Se è presente solo una ricerca per nome, considera solo i prodotti che corrispondono al nome
      if (nameMatches) {
        item.style.display = "";
      } else {
        item.style.display = "none";
      }
    } else if (selectedCategories.length > 0 || selectedGenres.length > 0) {
      // Se ci sono solo filtri attivi, considera solo i prodotti che corrispondono ai filtri
      if (categoryMatches && genreMatches) {
        item.style.display = "";
      } else {
        item.style.display = "none";
      }
    } else {
      // Se non ci sono né ricerca né filtri attivi, mostra tutti i prodotti
      item.style.display = "";
    }
  }
}

  function dynamicCart(url) {
    $.ajax({
      url: url,
      type: 'GET',
      contentType: 'application/json; charset=utf-8'
    }).done((response) => {
      response = JSON.parse(response);
      let contenutoHtml = "";

      if (response.url === undefined) {
        for (const p of response) {
          contenutoHtml += '<tr>';
          contenutoHtml += '<td><button data-isbn="' + p.idProdotto + '" onclick="eliminaRiga(this)"><img src="images/trash.ico" class="trash"></button></td>';
          contenutoHtml += '<td><img class="img-thumbnail" src="' + p.img + '"></td>';
          contenutoHtml += '<td>' + p.nome + '</td>';
          contenutoHtml += '<td><p class="costo">&#8364 ' + p.prezzo.toFixed(2) + '</p></td>';
          contenutoHtml += '<td><h5><input type="number" min="1" max="' + p.quantita + '" class="form-control quantita" onchange="totaleParziale()" value="1"></h5></td>';
          contenutoHtml += '<td><h5 class="totProd">totale</h5></td>';
          contenutoHtml += '</tr>';
        }
        $("#dinamico").append(contenutoHtml);
        totaleParziale();
      } else {
        window.location.assign(response.url);
      }
    });
  }