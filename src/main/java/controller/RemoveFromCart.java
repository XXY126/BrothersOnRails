package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Carrello;
import model.Prodotto;


@WebServlet("/RimuoviProdotto")
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		String idProdotto = request.getParameter("idProdotto");
		String idCarrello = carrello.getId();
		ArrayList<Prodotto> list = (ArrayList<Prodotto>) carrello.getCarrello();	
		RequestDispatcher dispatcher = null;
		System.out.println("idCarrello" + idCarrello);

		for(Prodotto p : list) {
			if(idProdotto != null) {
				if(p.getIdProdotto().equals(idProdotto)) {
					String query = "DELETE FROM contiene WHERE id_prodotto =? AND id_carrello = ?";
					try (Connection connection = DbManager.getConnection();
							Statement s = connection.createStatement();
							PreparedStatement ps = connection.prepareStatement(query);){
							ps.setString(1, idProdotto);
							ps.setString(2, idCarrello);
							int rs = ps.executeUpdate();
							
							if(rs!=0) {
								System.out.println("debug: riga eliminata");
							} else {
								System.out.println("debug: nessana cancellazione");
							}
					
					} catch (SQLException e) {
						e.printStackTrace();
					}
					list.remove(p);
					break;
				}
			}else return;
		}
		
		dispatcher = request.getRequestDispatcher("carrello.jsp");
		
		carrello.setCarrello(list);
		session.setAttribute("carrello", carrello);	
		
		dispatcher.forward(request, response);
	}

}
