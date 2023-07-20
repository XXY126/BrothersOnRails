package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import com.google.gson.Gson;

import model.Indirizzo;
import model.Utente;
import model.ListIndirizzi;

@WebServlet("/IndirizzoServlet")
public class IndirizzoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        Gson json = new Gson();
        Utente user = (Utente) session.getAttribute("user");
        
        String query = "SELECT * FROM indirizzo WHERE id_utente=?";
        
		try (Connection connection = DbManager.getConnection();
				PreparedStatement ps = connection.prepareStatement(query);){
			ps.setString(1, user.getEmail());
			ResultSet rs = ps.executeQuery();
			System.out.println("debug indirizzo 1");
			ListIndirizzi list = new ListIndirizzi();
			while(rs.next()) {
				Indirizzo i = new Indirizzo(rs.getString("via"),
						rs.getString("CAP"),
						rs.getString("citta"),
						rs.getString("provincia"),
						rs.getString("nazione"),
						rs.getString("numeroCivico"));
				list.addIndirizzo(i);
			}
			System.out.println("debug indirizzo 2");
			
			if(list.getList().isEmpty()) {
				//se l'utente non ha nessun indirizzo salvato
				out.write("null");
				System.out.println("debug indirizzo: lista vuota");
				return;
			}
			System.out.println("debug indirizzo: lista non vuota");
			out.write(json.toJson(list.getList()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
