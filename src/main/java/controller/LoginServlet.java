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

@WebServlet("/Login")
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

		try (Connection connection = DbManager.getConnection();
			Statement s = connection.createStatement();
			PreparedStatement ps = connection.prepareStatement(query);){			
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				String id = rs.getString("email");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				Utente user = new Utente(id, nome, cognome);

				query = "SELECT * FROM indirizzo WHERE id_utente = " + id;
				rs = s.executeQuery(query);

				/* quando si fa il log in, vengono caricate anche le info sull'indirizzo */
				if (rs.next()) {
					String indirizzo = rs.getString("via");
					String cap = rs.getString("cap");
					String citta = rs.getString("citta");
					String provincia = rs.getString("provincia");
					String nazione = rs.getString("nazione");
					String numeroCivico = rs.getString("numeroCivico");
					Indirizzo i = new Indirizzo(indirizzo, cap, citta, provincia, nazione, numeroCivico);
					session.setAttribute(address, i);
				} else {
					/* se l'indirizzo è inesistente, ne viene creato uno di default (serve per i placeholder */
					session.setAttribute(address, new Indirizzo("Inserisci i tuoi dati", "", "", "", "",""));
				}
				
				query = "SELECT * FROM carrello WHERE id_utente=" + id;
				rs =  s.executeQuery(query);
				
				String carrelloid = null;
				Carrello carrello = null;
				
				//se l'utente ha un carrello assegnato lo si prende e lo si assegna 
				if(rs.next()) {
				      carrelloid = rs.getString("id_carrello");
					  carrello = new Carrello(carrelloid);
				}
				else {
					query = "INSERT INTO carrello(utente_id) VALUES (" + id + ")";
					s.executeUpdate(query);
					query = "SELECT * FROM carrello WHERE id_utente=" + id;
					rs = s.executeQuery(query);
					if(rs.next()) {
						 carrelloid = rs.getString("id");
						 carrello = new Carrello(carrelloid);
					}
				}
				
				//popolazione del carrello
				//controllo i prodotti che appartengono attualmente al carrello
				query = "SELECT * FROM contiene WHERE carrello_id=" + carrelloid;
				rs = s.executeQuery(query);
				
				ArrayList<Prodotto> list = (ArrayList<Prodotto>) carrello.getCarrello();
				ArrayList<String> idProdottoList = new ArrayList<>();
				while(rs.next()) {
					String id_prodotto = rs.getString("id_prodotto");
					idProdottoList.add(id_prodotto);
				}
				
				//QUERY CHE PRENDE I PRODOTTI PRESENTE NEL CARRELLO CON ID CORRISPONDENTE AL CARELLO DELL'UTENTE
				query = "SELECT prodotto.nome, prodotto.costo, prodotto.id_categoria, prodotto.img,contiene.quantita FROM prodotto INNER JOIN contiene ON contiene.id_prodotto=prodotto.IDProdotto WHERE contiene.id_carrello ="+carrelloid;
				rs = s.executeQuery(query);
				
				
				
				while(rs.next()) {
					if(idProdottoList.contains(rs.getString("IDProdotto"))){
						String nomeprod = rs.getString("nome");
						String descrizione = rs.getString("descrizione");
						String img = rs.getString("img");
						String categoria = rs.getString("id_categoria");
						int quantita = rs.getInt("quantita");
						double prezzo = rs.getDouble("prezzo");
						Prodotto prodotto = new Prodotto(rs.getString("id_prodotto"), nomeprod, descrizione, img, categoria, quantita, prezzo);
						list.add(prodotto);
					}
				}
				carrello.setCarrello(list);
				session.setAttribute("carrello", carrello);
				session.setAttribute("user", user);
				dispatcher = request.getRequestDispatcher("index.jsp");
				
			} else {
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
			}
			dispatcher.forward(request, response);
			rs.close();
		} catch (Exception e) {
			logger.log(Level.ALL, error, e);
		}
	}
}
