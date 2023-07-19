package controller;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

@WebServlet("/AddOrdineServlet")
public class AddOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AddOrdineServlet.class.getName());
	private static final String error = "Errore";
	
	@SuppressWarnings("resource")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Statement s = null;
		PreparedStatement ps = null;
		
		RequestDispatcher dispatcher = null;
		Utente user = (Utente) session.getAttribute("user");
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		
		int address_id = -1;

		try (Connection connection = DbManager.getConnection();){		
			String query = "SELECT id FROM address WHERE id_utente = " + user.getEmail();
			s = connection.createStatement();
			ResultSet rs = s.executeQuery(query);

			if (rs.next())
				address_id = rs.getInt("id");
			else {
				request.setAttribute("status", "noAddress");
				dispatcher = request.getRequestDispatcher("indirizzo.jsp");
				dispatcher.forward(request, response);
				return;
			}

			Calendar c = Calendar.getInstance();
			java.util.Date javaDate = c.getTime();
			java.sql.Date sqlDate = new Date(javaDate.getTime());

			double totale = Double.parseDouble(request.getParameter("totale"));
			SecureRandom random = new SecureRandom();
			int ordine_id = 10000 + random.nextInt(90000);

			query = "INSERT INTO ordini (id, data, totale, site_user_id, stato_ordine_id, metodo_spedizione_id, address_id) values(?, ?, ?, ?, ?, ?, ?)";
			ps = connection.prepareStatement(query);
			ps.setInt(1, ordine_id);
			ps.setDate(2, sqlDate);
			ps.setDouble(3, totale);
			ps.setString(4, user.getEmail());
			ps.setInt(5, 1); // completato
			ps.setInt(6, 1); // posteItaliane
			ps.setInt(7, address_id);
			ps.executeUpdate();

			String[] values = request.getParameter("quantita").split(",");
			int i = 0;

			for (Prodotto p : carrello.getCarrello()) {
				p.setQuantita(Integer.parseInt(values[i]));
				i++;
			}
			
			for (Prodotto p : carrello.getCarrello()) {
				query = "INSERT INTO ordine_singolo (quantità, totale_parziale, ordini_id, prodotti_isbn, prodotti_nome, prodotti_img) values("
						+ p.getQuantita() + ", " + (p.getPrezzo() * p.getQuantita()) + ", " + ordine_id + ", '"
						+ p.getIdProdotto() + "', '" + p.getNome() + "', '" + p.getImg() + "')";
				
				s.executeUpdate(query);
				query= "SELECT quantita FROM prodotti WHERE isbn = ?";
				ps = connection.prepareStatement(query);
				ps.setString(1,p.getIdProdotto());
				rs = ps.executeQuery();
				if(rs.next()) {
					query= "UPDATE prodotti SET quantita = ? WHERE isbn = ?";
					ps = connection.prepareStatement(query);
					ps.setInt(1, (rs.getInt("quantita")-p.getQuantita()));
					ps.setString(2, p.getIdProdotto());
					ps.executeUpdate();
					ps.close();
				}
			}
			carrello.empty();
			session.setAttribute("carrello", carrello);

			dispatcher = request.getRequestDispatcher("profilo.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (ServletException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		} catch (NumberFormatException e) {
			logger.log(Level.ALL, error, e);
		} finally {
			try {
				s.close();
				ps.close();
			} catch (SQLException e) {
				logger.log(Level.ALL, error, e);
			} catch (NullPointerException e) {
				logger.log(Level.ALL, error, e);
			}
		}
	}

}
