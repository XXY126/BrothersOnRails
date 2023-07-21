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

@WebServlet("/AddToCartServlet")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());
	private static final String error = "Errore";
	
	@SuppressWarnings("null")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = null;
		String id_prodotto =  (String) request.getParameter("idProdotto");
		HttpSession session = request.getSession();
		Utente user = (Utente) session.getAttribute("user");
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		
		System.out.println("stampa carrello");
		String query = "SELECT * FROM carrello where id_utente =?";
		System.out.println("debug: id_prodotto="+id_prodotto);
		
		try (Connection connection = DbManager.getConnection();
				Statement s = connection.createStatement();
				PreparedStatement ps = connection.prepareStatement(query);){
				ps.setString(1, user.getEmail());
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					//TODO GESTIRE QUANDO IL PRODOTTO E' GIA PRESENTE NEL CARRELLO, INCREMENTARE LA QUANTITA
					//TODO GESTIRE QUANDO LA QUANTITA E' POSSIBILE IMPOSTARLO
					
					String id_carrello = rs.getString("id_carrello");
					
					//CONTROLLO SE IL PRODTTO E' GIA PRESENTE NEL CARRELLO
					query="SELECT * FROM contiene WHERE id_carrello =? AND id_prodotto=?";
					PreparedStatement ps3 = connection.prepareStatement(query);
					ps3.setString(1, id_carrello);
					ps3.setString(2, id_prodotto);
					ResultSet rs3 = ps3.executeQuery();
					
					if(rs3.next()) {
						//PRODOTTO GIA' PRESENTE NEL CARRELLO DUNQUE BASTA INCREMENTARE LA QUANTITA
						System.out.println("debug: prodotto già presente");
						int quantita = rs3.getInt("quantita") +1;
						query = "UPDATE contiene SET quantita=? WHERE id_carrello =? AND id_prodotto=?";
						PreparedStatement ps4 = connection.prepareStatement(query);
						ps4.setInt(1, quantita);
						ps4.setString(2, id_carrello);
						ps4.setString(3, id_prodotto);
						
						int rsint = ps4.executeUpdate(); 
						
						if(rsint==0) {
							//ERRORE
						}
						carrello.addQuantita(id_prodotto, 1);
						
						
						session.setAttribute("carrello", carrello);
						dispatcher = request.getRequestDispatcher("carrello.jsp");
						
					} else {
						//PRODOTTO NON PRESENTE DUNQUE AGGIUNGERLO
						System.out.println("PRODOTTO NON PRESENTE");
						
						//INSERIMENTO DEL PRODOTTO NEL CARRELLO
						query = "INSERT INTO contiene (id_carrello, id_prodotto, quantita) VALUES (?,?,1)";
						PreparedStatement ps1 = connection.prepareStatement(query);
						ps1.setString(1, id_carrello);
						ps1.setString(2, id_prodotto);
						int rsint = ps1.executeUpdate();
						
						//QUERY PER OTTENERE LE INFORMAZIONI DELL'OGGETTO
						query = "SELECT * FROM prodotto WHERE idProdotto = ?";
						PreparedStatement ps2 = connection.prepareStatement(query);
						ps2.setString(1, id_prodotto);
						ResultSet rs2 = ps2.executeQuery();
						
						if(rs2.next()) {
							Prodotto p = new Prodotto(rs2.getString("IDProdotto"), rs2.getString("nome"), rs2.getString("descrizione"), rs2.getString("img"), rs2.getString("id_categoria"), 1, rs2.getDouble("costo"));
							carrello.add(p);
						} else {
							//ERRORE
						}
						
						session.setAttribute("carrello", carrello);
						dispatcher = request.getRequestDispatcher("carrello.jsp");
					}
				}else {
					//ERRORE
				}
				dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}

