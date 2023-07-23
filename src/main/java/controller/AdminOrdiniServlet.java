package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Ordine;
import model.OrdineSingolo;
import model.Utente;
import model.OrdiniList;
import model.Prodotto;

@WebServlet("/AdminOrdiniServlet")
public class AdminOrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AdminOrdiniServlet.class.getName());
	private static final String error = "Errore";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("user");
		RequestDispatcher dispatcher = null;

		try (Connection connection = DbManager.getConnection();
			Statement s = connection.createStatement();){
			
			//ottenere tutti gli ordini
			String query = "SELECT * FROM ordine";
			PreparedStatement ps = connection.prepareStatement(query);
			OrdiniList ordini = new OrdiniList();
			ResultSet rs = ps.executeQuery();
			System.out.println("degub ordiniservlet "+ query+ " eseguita");
			
			while(rs.next()) {
				System.out.println(rs.getString("id_utente"));
				Ordine o = new Ordine(rs.getInt("id_ordine"),rs.getTimestamp("varData"), rs.getDouble("totale"), rs.getString("id_utente"), rs.getInt("id_indirizzo"));
				query="SELECT * FROM popola WHERE id_ordine = ?";
				
				PreparedStatement ps1 = connection.prepareStatement(query);
				ps1.setInt(1, o.getId());
				ResultSet rs1 = ps1.executeQuery();
				System.out.println("degub ordiniservlet "+ query+ " eseguita");
				
				while(rs1.next()) {
					System.out.println(rs1.getString("id_ordine"));
					query = "SELECT * FROM prodotto WHERE IDProdotto = ?";
					PreparedStatement ps2 = connection.prepareStatement(query);
					ps2.setString(1, rs1.getString("id_prodotto"));
					ResultSet rs2 = ps2.executeQuery();
					System.out.println("degub ordiniservlet "+ query+ " eseguita");
					
					if(rs2.next()) {
						Prodotto p = new Prodotto(rs2.getString("IDProdotto"), rs2.getString("nome"), rs2.getString("descrizione"), rs2.getString("img"), rs2.getString("id_categoria"), rs2.getInt("quantita"), rs2.getDouble("costo"));
						OrdineSingolo os = new OrdineSingolo(o.getId(), rs1.getString("id_prodotto"), rs1.getInt("quantita"), rs1.getDouble("costoAcquisto"), p);
						o.add(os);
						System.out.println("o.add");
					}
				}
				ordini.add(o);
			}
			System.out.println("deubg ordiniServlet fine");
			
			session.setAttribute("ordini", ordini);
			dispatcher = request.getRequestDispatcher("adminordini.jsp");
			dispatcher.forward(request, response);

		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (ServletException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		}
	}

}