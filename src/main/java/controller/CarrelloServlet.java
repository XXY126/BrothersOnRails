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
		String isbn = request.getParameter("isbn");
		Utente user = (Utente) session.getAttribute("user");
		Gson json = new Gson();
		System.out.println(isbn);
		System.out.println(user.getEmail());
		
		String query = "SELECT * FROM carrello WHERE id_utente = ?";

		try (Connection connection = DbManager.getConnection();
			PreparedStatement ps = connection.prepareStatement(query);) {
			PrintWriter out = response.getWriter();
			if (user == null)
				response.sendRedirect("login.jsp");
			/*
			else {
				//Cerca il carrello dell'utente
				ps.setString(1, user.getEmail());
				ResultSet rs = ps.executeQuery();
				
				String idCarrello = rs.getString("id_carrello");
				
				//Cerca i prodotti presenti nel carrello
				query = "SELECT * FROM contiene where id_carrello = ";
				PreparedStatement ps1 = connection.prepareStatement(query);
				ps1.setString(1, idCarrello);
				ResultSet rs1 = ps1.executeQuery();
				
				String idProdotto;
				int quantita;
				while(rs1.next()) {
					idProdotto = rs1.getString("id_prodotto");
					quantita = rs1.getInt("quantita");
					
					
				}
				*/
				
				/*
				ps.setString(1, isbn);
				
				
				if (rs.next()) {
					String nome = rs.getString("nome");
					String descrizione = rs.getString("descrizione");
					String img = rs.getString("immagine_prod");
					String categoria = rs.getString("categoria_nome");
					quantita = rs.getInt("quantita");
					double prezzo = rs.getDouble("prezzo");
					Prodotto prodotto = new Prodotto(isbn, nome, descrizione, img, categoria, quantita, prezzo);

					// controlla che ci sia solo una ripetizione per ogni prodotto 
					int flag = 0;
					for (Prodotto p : carrello.getCarrello()) {
						if (prodotto.getIdProdotto().equals(p.getIdProdotto()))
							flag = 1;
					}
					
					

					if (flag == 0) {
						carrello.add(prodotto);
						session.setAttribute("carrello", carrello);
					}
					*/
				//}
				
				//rs.close();
				carrello.toString();
				out.write(json.toJson(carrello.getCarrello()));
			//}

		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		}
	}
}