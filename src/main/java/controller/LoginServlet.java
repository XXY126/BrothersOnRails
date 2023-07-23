package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
import model.Indirizzo;
import model.Prodotto;
import model.Utente;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());
	private static final String error = "Errore";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String address = "indirizzo";
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		String query = "SELECT * FROM utente WHERE email = ? and password = ?";
		
		System.out.println("debug:1");
		System.out.println(email);
		System.out.println(password);
		
		

		try (Connection connection = DbManager.getConnection();
			Statement s = connection.createStatement();
			PreparedStatement ps = connection.prepareStatement(query);){			
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("debug:2");
				String id = rs.getString("email");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				Utente user = new Utente(id, nome, cognome);
				System.out.println("debut:3");

				query = "SELECT * FROM indirizzo WHERE id_utente =?";
				System.out.println(query);
				session.setAttribute("admin", false);
				PreparedStatement ps1 = connection.prepareStatement(query);
				ps1.setString(1, id);
				System.out.println("debut:4");
				System.out.println(query);
				ResultSet rs1 = ps1.executeQuery();
				System.out.println("debut:5");

				/* quando si fa il log in, vengono caricate anche le info sull'indirizzo */
				if (rs1.next()) {
					System.out.println("debug: indirizzo esistente");
					String indirizzo = rs1.getString("via");
					String cap = rs1.getString("cap");
					String citta = rs1.getString("citta");
					String provincia = rs1.getString("provincia");
					String nazione = rs1.getString("nazione");
					String numeroCivico = rs1.getString("numeroCivico");
					Indirizzo i = new Indirizzo(indirizzo, cap, citta, provincia, nazione, numeroCivico);
					session.setAttribute(address, i);
				} else {
					System.out.println("debug: Indirizzo assente");
					/* se l'indirizzo è inesistente, ne viene creato uno di default (serve per i placeholder */
					session.setAttribute(address, new Indirizzo("Inserisci i tuoi dati", "", "", "", "",""));
				}
				
				System.out.println("debug:6");
				query = "SELECT * FROM carrello WHERE id_utente=?";
				PreparedStatement ps2 = connection.prepareStatement(query);
				ps2.setString(1, id);
				ResultSet rs2;
				rs2 =  ps2.executeQuery();
				
				System.out.println("debug:7");
				
				String carrelloid = null;
				Carrello carrello = null;
				
				//se l'utente ha un carrello assegnato lo si prende e lo si assegna 
				if(rs2.next()) {
					System.out.println("carrello presente");
				      carrelloid = rs2.getString("id_carrello");
					  carrello = new Carrello(carrelloid);
				}
				else {
					System.out.println("carrello assente");
					query="INSERT INTO carrello(id_utente) values (?)";
					PreparedStatement ps3 = connection.prepareStatement(query);
					ps3.setString(1, id);
					int rs3 = ps3.executeUpdate();
					
					query="SELECT * FROM carrello where id_utente=?";
					PreparedStatement ps4 = connection.prepareStatement(query);
					ps4.setString(1, id);
					ResultSet rs4 = ps4.executeQuery();
					
					if(rs4.next()) {
						 carrelloid = rs4.getString("id_carrello");
						 carrello = new Carrello(carrelloid);
					}
				}
				
				System.out.println("debug:8");
				
				//popolazione del carrello
				//controllo i prodotti che appartengono attualmente al carrello
				query="SELECT * FROM contiene WHERE id_carrello = ?";
				PreparedStatement ps5 = connection.prepareStatement(query);
				ps5.setString(1, carrelloid);
				ResultSet rs5 = ps5.executeQuery();
				
				System.out.println("debug:9");
				
				ArrayList<Prodotto> list =(ArrayList<Prodotto>) carrello.getCarrello();
				ArrayList<String> idProdottoList = new ArrayList<>();
				
				System.out.println("debug99");
				while(rs5.next()) {
					String id_prodotto = rs5.getString("id_prodotto");
					System.out.println("id prodotto = "+id_prodotto);
					idProdottoList.add(id_prodotto);
				}
				
				System.out.println("debug:10");
				System.out.println("debug: carrelloid ="+carrelloid);
				
				//QUERY CHE PRENDE I PRODOTTI PRESENTE NEL CARRELLO CON ID CORRISPONDENTE AL CARELLO DELL'UTENTE
				query = "SELECT prodotto.IDProdotto, prodotto.nome, prodotto.descrizione, prodotto.costo, prodotto.id_categoria, prodotto.img,contiene.quantita FROM prodotto INNER JOIN contiene ON contiene.id_prodotto=prodotto.IDProdotto WHERE contiene.id_carrello =?";
				PreparedStatement ps6 = connection.prepareStatement(query);
				ps6.setString(1, carrelloid);
				ResultSet rs6 = ps6.executeQuery();
				
				System.out.println("debug:11");
				
				while(rs6.next()) {
					if(idProdottoList.contains(rs6.getString("IDProdotto"))){
						String nomeprod = rs6.getString("nome");
						String descrizione = rs6.getString("descrizione");
						String img = rs6.getString("img");
						String categoria = rs6.getString("id_categoria");
						int quantita = rs6.getInt("quantita");
						double prezzo = rs6.getDouble("costo");
						System.out.println(nomeprod+descrizione+ img+ categoria+ quantita+ prezzo);
						Prodotto prodotto = new Prodotto(rs6.getString("IDProdotto"), nomeprod, descrizione, img, categoria, quantita, prezzo);
						list.add(prodotto);
						System.out.println("ayo");
					}
				}
				
				carrello.setCarrello(list);
				session.setAttribute("carrello", carrello);
				session.setAttribute("user", user);
				request.setAttribute("status", "success");
				session.setAttribute("admin", false);
				dispatcher = request.getRequestDispatcher("index.jsp");
				System.out.println("fine if carrello presente");
				
			} else {
				//controllo se è admin
				query = "SELECT * FROM admin WHERE email=? AND password=?";
				PreparedStatement pa = connection.prepareStatement(query);
				pa.setString(1, email);
				pa.setString(2, password);
				ResultSet rsa = pa.executeQuery();
				if(rsa.next()) {
					//account loggato è un admin
					Utente user = new Utente(rsa.getString("email"));
					session.setAttribute("user", user);
					session.setAttribute("admin", true);
					request.setAttribute("status", "failed");
					dispatcher = request.getRequestDispatcher("index.jsp");
				} else {
					System.out.println("login failed");
					request.setAttribute("status", "failed");
					dispatcher = request.getRequestDispatcher("login.jsp");
				}
			}
			dispatcher.forward(request, response);
			rs.close();
		} catch (Exception e) {
			logger.log(Level.ALL, error, e);
		}
	}
}