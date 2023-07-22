package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/ModificaProdottoServlet")
public class ModificaProdottoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CategoriaServlet.class.getName());
	private static final String error = "Errore";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idProdotto = request.getParameter("idProdotto");
        String descrizione = request.getParameter("descrizione");
        String categoria = request.getParameter("categoria");
        int quantita = Integer.parseInt(request.getParameter("quantita"));
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        
        System.out.println("idProdotto:"+idProdotto);
        System.out.println(descrizione);
        System.out.println(categoria);
        System.out.println(quantita);
        System.out.println(prezzo);
        
		try (Connection connection = DbManager.getConnection();) {
            String updateQuery = "UPDATE prodotto SET descrizione=?, categoria=?, quantita=?, costo=? WHERE IDProdotto=?";
            PreparedStatement statement = connection.prepareStatement(updateQuery);
            statement.setString(1, descrizione);
            statement.setString(2, categoria);
            statement.setInt(3, quantita);
            statement.setDouble(4, prezzo);
            statement.setString(5, idProdotto);
            System.out.print(statement);
            statement.executeUpdate();
            
            System.out.println(updateQuery + "effettuata");
		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		}

        response.sendRedirect("index.jsp");
    }

}
