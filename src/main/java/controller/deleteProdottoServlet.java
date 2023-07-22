package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;
import model.Prodotto;
import model.Utente;
import model.Indirizzo;

@WebServlet("/deleteProdottoServlet")
public class deleteProdottoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(deleteProdottoServlet.class.getName());
	private static final String error = "Errore";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String idProdotto = request.getParameter("idProdotto");
		String query = "UPDATE prodotto SET eliminato = '1' WHERE IDProdotto = ?";

		try (Connection connection = DbManager.getConnection();){	
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, idProdotto);
			int rs = ps.executeUpdate();
			
			if(rs==0) {
				//errore
				response.setContentType("text/plain");
				response.getWriter().println("failed");
				System.out.println("fallimento");
			} else {
				response.setContentType("text/plain");
				response.getWriter().println("success");
				System.out.println("successo");
			}
			
		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (NumberFormatException e) {
			logger.log(Level.ALL, error, e);
		} 
	}

}
