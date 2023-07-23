package controller;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/CardsServlet")
public class CardsServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try (Connection conn = DbManager.getConnection();){            
            // Esegui la query per ottenere i dati dei prodotti dalla tabella "Prodotti"
            String query = "select * from prodotto LIMIT 3";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Genera le cards HTML utilizzando i dati ottenuti dalla query
            while (rs.next()) {
                String nomeProdotto = rs.getString("nome");
                String id = rs.getString("IDProdotto");
                String immagine = rs.getString("img");
                String descrizione = rs.getString("descrizione");
                double prezzo = rs.getDouble("costo");

                out.println("<div class='col-mod-4'>");
                out.println("<div class='card mb-4'>");
                out.println("<a class='linka' href='ProductServlet?idProdotto="+id+"'>");
                out.println("<img src='images/" + immagine + "' class='card-img-top' alt='" + nomeProdotto + "'>");
                out.println("</a>");
                out.println("<div class='card-body'>");
                out.println("<h5 class='card-title'>" + nomeProdotto + "</h5>");
                out.println("<p class='card-text'>" + descrizione + "</p>");
                out.println("<p class='card-price'>Prezzo: " + prezzo + " EUR</p>");
                out.println("</div>");
                out.println("</div>");
                out.println("</div>");
            }
            System.out.println("debug");
            // Chiudi le risorse
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Altri metodi del servlet, se necessario
}