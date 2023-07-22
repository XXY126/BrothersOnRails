package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/ModificaProdottoServlet")
public class ModificaProdottoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recupera i parametri dal form
        String idProdotto = request.getParameter("idProdotto");
        String descrizione = request.getParameter("descrizione");
        String categoria = request.getParameter("categoria");
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        String imgFileName = "";

        // Connessione al database e aggiornamento del prodotto
        try (Connection conn = DbManager.getConnection()) {
            String updateQuery = "UPDATE prodotti SET descrizione=?, categoria=?, quantita=?, prezzo=? WHERE IDProdotto=?";
            PreparedStatement statement = conn.prepareStatement(updateQuery);
            statement.setString(1, descrizione);
            statement.setString(2, categoria);
            statement.setInt(3, quantita);
            statement.setDouble(4, prezzo);
            statement.setString(6, idProdotto);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gestire eventuali errori di connessione o query
        }

        // Puoi redirezionare l'utente ad una pagina di conferma o di visualizzazione prodotto dopo la modifica
        // In questo esempio, lo reindirizziamo alla pagina principale del sito
        response.sendRedirect("index.jsp");
    }

}
