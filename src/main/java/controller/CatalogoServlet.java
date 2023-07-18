package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Prodotto;


@WebServlet("/CatalogoServlet")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CatalogoServlet.class.getName());
	private static final String error = "Errore";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson json = new Gson();

		try (Connection connection = DbManager.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM prodotto");) {
			PrintWriter out = response.getWriter();
			ArrayList<Prodotto> catalogo = new ArrayList<>();


			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String id = rs.getString("IDProdotto");
				String nome = rs.getString("nome");
				String descrizione = rs.getString("descrizione");
				String img = rs.getString("img");
				String categoria = rs.getString("id_categoria");
				int quantita = rs.getInt("quantita");
				double prezzo = rs.getDouble("costo");
				Prodotto p = new Prodotto(id, nome, descrizione, img, categoria, quantita, prezzo);
				catalogo.add(p);
			}
			out.write(json.toJson(catalogo));
			rs.close();
		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		}
	}
}