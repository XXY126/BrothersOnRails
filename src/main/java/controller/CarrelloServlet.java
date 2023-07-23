package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Carrello;
import model.Prodotto;
import model.Utente;

@WebServlet("/CartServlet")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CarrelloServlet.class.getName());
	private static final String error = "Errore";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		String id = request.getParameter("id");
		Utente user = (Utente) session.getAttribute("user");
		Gson json = new Gson();
		System.out.println(id);
		System.out.println(user.getEmail());
		
		
		String query = "SELECT * FROM carrello WHERE id_utente = ?";

		try (Connection connection = DbManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);) {
			PrintWriter out = response.getWriter();
			if (user == null)
				response.sendRedirect("login.jsp");
			
				
				carrello.toString();
				out.write(json.toJson(carrello.getCarrello()));
				System.out.println("fine servlet Carrello");
			//}

		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		}
	}
}