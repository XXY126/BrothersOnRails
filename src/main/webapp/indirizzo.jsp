<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.all.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.12/dist/sweetalert2.min.css" rel="stylesheet">
 <% if(session.getAttribute("user")==null){
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
		return;
 }
 %>
<%String status = (String) request.getAttribute("status");%>
<script type ="text/javascript">
	if('<%= status %>' == 'success'){
		Swal.fire("Congratulazione!", "Account creato correttamente!", "success");
	}else if('<%= status %>' == 'NoIndirizzo'){
		Swal.fire("Spiacente!", "Deve prima impostare un indirizzo","error");
	}
</script>

<head>
    <title>Indirizzo</title>
    <jsp:include page="include/head.jsp" />
</head>

<body>
    <jsp:include page="include/header.jsp" />

    <div class="container mt-5">
        <h2 class="text-center">Indirizzi</h2>
        
        <div class="col-md-6 mx-auto card-container">
            <div class="card">
                <div class="card-body">
                    <div id="responseContent"></div>
                    <a href="AddIndirizzo.jsp" class="btn btn-primary">Aggiungi Indirizzo</a>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="include/footer.jsp" />

    <script>
        // Funzione AJAX per chiamare la servlet lato server
        $(document).ready(function () {
            $.ajax({
                url: "IndirizzoServlet", // Sostituisci con il nome della tua servlet
                type: "GET",
                contentType: 'application/json; charset=utf-8',
            }).done((response) =>{
                if(response == "null"){
                    $("#responseContent").html('NESSUN INDIRIZZO');
                } else {
                    response = JSON.parse(response);
                    let contenutoHtml = "";
                    for (const indirizzo of response) {
                        contenutoHtml += '<div class="card">';
                        contenutoHtml += '<div class="card-body">';
                        contenutoHtml += '<h4 class="card-title">Indirizzo:</h4>';
                        contenutoHtml += '<p class="card-text">' + indirizzo.via + ', ' + indirizzo.numeroCivico + '</p>';
                        contenutoHtml += '<p class="card-text">' + indirizzo.cap + ' ' + indirizzo.citta + ' (' + indirizzo.provincia + '), ' + indirizzo.nazione + '</p>';
                        contenutoHtml += '</div>';
                        contenutoHtml += '</div>';
                    }
                    $("#responseContent").append(contenutoHtml);
                }
            });
        });
    </script>
</body>

</html>
