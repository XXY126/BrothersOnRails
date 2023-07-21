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
		RequestDispatcher dispatcher = null;
		Utente user = (Utente) session.getAttribute("user");
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		PrintWriter out = response.getWriter();
		String totale = request.getParameter("totale");
		
		int address_id = -1;

		try (Connection connection = DbManager.getConnection();){		
			String query = "";
			
			String[] values = request.getParameter("quantita").split(",");
			int i = 0;
			for (Prodotto p : carrello.getCarrello()) {
				System.out.println("quantita[" +i+"] = "+values[i]);
				p.setQuantita(Integer.parseInt(values[i]));
				i++;
				
				query = "SELECT * FROM prodotto WHERE IDProdotto = ?";
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, p.getIdProdotto());
				ResultSet rs = ps.executeQuery();
				System.out.println("debug AddOrdine:" +query+ " eseguita");
				if(rs.next()) {
					System.out.println("p.getQuantita = "+p.getQuantita());
					System.out.println("rs.getIntQuantita = " + rs.getInt("quantita"));
					if(p.getQuantita()>rs.getInt("quantita")) {
						out.print("errore quantita");
						dispatcher = request.getRequestDispatcher("profilo.jsp");
						dispatcher.forward(request, response);
					}
				}else {
					//errore
				}
			}
			query="SELECT * FROM indirizzo where id_utente = ?";
			PreparedStatement ps2 = connection.prepareStatement(query);
			ps2.setString(1, user.getEmail());
			String id_indirizzo = null;
			ResultSet rs2 = ps2.executeQuery();
			if(rs2.next()) {
				System.out.println("debug AddOrdine: indirizzo");
				id_indirizzo = rs2.getString("id_indirizzo");
			}else {
				System.out.println("debug AddOrdine: errore indirizzo");
				out.print("errore indirizzo");
				dispatcher = request.getRequestDispatcher("profilo.jsp");
				dispatcher.forward(request, response);
			}
			
			
			
			//INSERT DELL'ORDINE
			query = "INSERT INTO ordine (totale, varData, id_utente, id_indirizzo) VALUES(?, ?, ?, ?)";
			
			PreparedStatement ps1 = connection.prepareStatement(query);
			ps1.setString(1, totale);
			ps1.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps1.setString(3, user.getEmail());
			ps1.setString(4, id_indirizzo);
			
			int rs1 = ps1.executeUpdate();
			
			if (rs1==0) {
				out.print("errore inserimento");
				dispatcher = request.getRequestDispatcher("profilo.jsp");
				dispatcher.forward(request, response);
			}
			
			//ELIMINARE QUANTITA DAL DB
			for(Prodotto p : carrello.getCarrello()) {
				query = "SELECT * FROM prodotto WHERE IDProdotto = ?";
				PreparedStatement pp = connection.prepareStatement(query);
				pp.setString(1, p.getIdProdotto());
				ResultSet rp = pp.executeQuery();
				System.out.println("debug: "+query+" eseguita");
				int quantita=0;
				if(rp.next()) {
					quantita = rp.getInt("quantita");
				}
				query = "UPDATE prodotto SET quantita = ? WHERE IDProdotto = ?";
				System.out.println("quantita = "+quantita);
				PreparedStatement pp1 = connection.prepareStatement(query);
				pp1.setInt(1, quantita-p.getQuantita());
				pp1.setString(2, p.getIdProdotto());
				int rp1 = pp1.executeUpdate();
				
				if(rp1 == 0) {
					System.out.println("errore update");
					out.print("errore update");
					dispatcher = request.getRequestDispatcher("profilo.jsp");
					dispatcher.forward(request, response);
				}
				System.out.println("debug: "+query+" eseguita");
			}
			
			//INSERT TABELLA POPOLA
			query = "SELECT * FROM ordine WHERE id_utente = ? ORDER BY varData DESC";
			PreparedStatement ps3 = connection.prepareStatement(query);
			ps3.setString(1, user.getEmail());
			ResultSet rs3 = ps3.executeQuery();
			if(rs3.next()) {
				String id_ordine = rs3.getString("id_ordine");
				for (Prodotto p : carrello.getCarrello()) {
					query = "INSERT INTO popola (id_ordine, id_prodotto, quantita, costoAcquisto) values (?, ?, ?, ?)";
					PreparedStatement ps4 = connection.prepareStatement(query);
					ps4.setString(1, id_ordine);
					ps4.setString(2, p.getIdProdotto());
					ps4.setInt(3, p.getQuantita());
					ps4.setDouble(4, p.getPrezzo());
					
					int rs4 = ps4.executeUpdate();
					if(rs4==0) {
						//errore
					}
				}
			}
			
			//SVUOTARE IL CARRELLO SIA DALLA SESSIONE CHE NEL DATABASE
			query="DELETE FROM contiene WHERE id_carrello = ?";
			PreparedStatement ps5 = connection.prepareStatement(query);
			ps5.setString(1, carrello.getId());
			System.out.println(carrello.getId());
			ps5.executeUpdate();
			
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
		} 
	}

}
