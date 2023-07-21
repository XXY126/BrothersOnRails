<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="include/head.jsp" />

<jsp:include page="include/header.jsp" />

<div class="container mt-5">
    <h2 class="text-center">Aggiungi Indirizzo</h2>
    <div class="row mt-4">
        <div class="col-md-6 mx-auto">
            <form action="AddIndirizzoServlet" method="post">
                <div class="form-group">
                    <label for="via">Via:</label>
                    <input type="text" class="form-control" id="via" name="via" required>
                </div>
                <div class="form-group">
                    <label for="numeroCivico">Numero Civico:</label>
                    <input type="text" class="form-control" id="numeroCivico" name="numeroCivico" required>
                </div>
                <div class="form-group">
                    <label for="cap">CAP:</label>
                    <input type="text" class="form-control" id="cap" name="cap" required>
                </div>
                <div class="form-group">
                    <label for="citta">Città:</label>
                    <input type="text" class="form-control" id="citta" name="citta" required>
                </div>
                <div class="form-group">
                    <label for="provincia">Provincia:</label>
                    <input type="text" class="form-control" id="provincia" name="provincia" required>
                </div>
                <div class="form-group">
                    <label for="nazione">Nazione:</label>
                    <input type="text" class="form-control" id="nazione" name="nazione" required>
                </div>
                <button type="submit" class="btn btn-primary">Aggiungi Indirizzo</button>
            </form>
        </div>
    </div>
</div>

<%String status = (String) request.getAttribute("status");%>
<script type ="text/javascript">
	if('<%= status %>' == 'failed'){
		Swal.fire("Spiacente!", "Deve inserire un indirizzo", "error");
	}
	window.onload = function() {
	    if(!window.location.hash) {
	        window.location = window.location + '#loaded';
	        window.location.reload();
	    }
	}
</script>

<jsp:include page="include/footer.jsp" />
