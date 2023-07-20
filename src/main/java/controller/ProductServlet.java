package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import model.Prodotto;

@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ProductServlet.class.getName());
	private static final String error = "Errore";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("idProdotto");
		PrintWriter out = response.getWriter();
		Gson json = new Gson();
		System.out.println("debug product servlet: id prodotto: "+ id);
		
		try (Connection connection = DbManager.getConnection();
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM prodotto WHERE IDProdotto = ?");){	
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			
			System.out.println("productservlet debug: 1");

			if (rs.next()) {
				String nome = rs.getString("nome");
				String descrizione = rs.getString("descrizione");
				String img = rs.getString("img");
				String categoria = rs.getString("id_categoria");
				int quantita = rs.getInt("quantita");
				double prezzo = rs.getDouble("costo");
				
				Prodotto p = new Prodotto(id, nome, descrizione, img, categoria, quantita, prezzo);
				request.setAttribute("prodotto", p);
				RequestDispatcher dispatcher = request.getRequestDispatcher("prodotto.jsp");
				request.setAttribute("p", p);
				dispatcher.forward(request, response);
				System.out.println("debub: Fine ProductServlet");
			}
			rs.close();
		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (ServletException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		}
	}
}
