package controller;

import java.io.IOException;
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

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SignUpServlet.class.getName());
	private static final String error = "Errore";
	private static final String status = "status";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		RequestDispatcher dispatcher = null;
		String query1 = "SELECT email FROM utente WHERE email=?";
		String query2 = "INSERT INTO utente(nome,cognome,email,password) values(?,?,?,?)";
		String query3 = "SELECT email FROM admin WHERE email=?";
		
		try (Connection connection = DbManager.getConnection();
			PreparedStatement ps1 = connection.prepareStatement(query1);
			PreparedStatement ps2 = connection.prepareStatement(query2);
			PreparedStatement ps3 = connection.prepareStatement(query3)){
			
			System.out.println(email);
			ps1.setString(1,  email);
			System.out.println(ps1);
			ResultSet rs = ps1.executeQuery();
			ps3.setString(1, email);
			ResultSet rs3 = ps3.executeQuery();

			
			if(rs.next()) {
				request.setAttribute(status, "duplicato");
				dispatcher = request.getRequestDispatcher("signup.jsp");
				dispatcher.forward(request, response);
				return;
				/*response.sendRedirect("/signup.jsp");*/
			} else if(rs3.next()){
				request.setAttribute(status, "duplicato");
				dispatcher = request.getRequestDispatcher("signup.jsp");
				dispatcher.forward(request, response);
				return;
			}
			else {
				ps2.setString(1, nome);
				ps2.setString(2, cognome);
				ps2.setString(3, email);
				ps2.setString(4, password);
				
				int rowCount = 0;
				
				rowCount = ps2.executeUpdate();
				dispatcher = request.getRequestDispatcher("index.jsp");
				if (rowCount > 0) {
					request.setAttribute(status, "success");
				} else {
					request.setAttribute(status, "failed");
				}
				/*response.sendRedirect("/login.jsp");*/
			}
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return;
		} catch (SQLException e) {
			logger.log(Level.ALL, error, e);
		} catch (IOException e) {
			logger.log(Level.ALL, error, e);
		}
	}
}