package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
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
import model.ProdottoComparator;
import model.Indirizzo;

@WebServlet("/AddIndirizzoServlet")
public class AddIndirizzoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());
	private static final String error = "Errore";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		
		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("user");
		RequestDispatcher dispatcher = null;
		String via = request.getParameter("via");
		String numeroCivico = request.getParameter("numeroCivico");
		String cap = request.getParameter("cap");
		String citta = request.getParameter("citta");
		String provincia = request.getParameter("provincia");
		String nazione = request.getParameter("nazione");

		String query = "INSERT INTO indirizzo (id_utente, via, numeroCivico, cap, citta, provincia, nazione) VALUES(?, ?, ?, ?, ?, ?, ?)";
		try (Connection connection = DbManager.getConnection();
			Statement s = connection.createStatement();
			PreparedStatement ps = connection.prepareStatement(query);){
			
			ps.setString(1, user.getEmail());
			ps.setString(2, via);
			ps.setString(3, numeroCivico);
			ps.setString(4, cap);
			ps.setString(5, citta);
			ps.setString(6, provincia);
			ps.setString(7, nazione);
			int rs = ps.executeUpdate();
			
			Indirizzo i = new Indirizzo(via, cap, citta, provincia, nazione, numeroCivico);
			
			if(rs!=0) {
				//insert con successo
				session.setAttribute("indirizzo", i);
				dispatcher = request.getRequestDispatcher("indirizzo.jsp");
			}else {
				//ERRORE
			}
			
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}

