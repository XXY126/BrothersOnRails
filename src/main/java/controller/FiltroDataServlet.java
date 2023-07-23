package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

import model.Ordine;
import model.OrdineSingolo;
import model.Utente;
import model.OrdiniList;
import model.Prodotto;

@WebServlet("/FiltroDataServlet")
public class FiltroDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(FiltroDataServlet.class.getName());
    private static final String error = "Errore";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente user = (Utente) session.getAttribute("user");
        RequestDispatcher dispatcher = null;
        String query ="";
        String dataInizio = request.getParameter("dataInizio");
        String dataFine = request.getParameter("dataFine");

        try (Connection connection = DbManager.getConnection();){
        	
        	query = "SELECT * FROM ordine WHERE varData>=? AND varData<=?";
        	PreparedStatement ps = connection.prepareStatement(query);
        	ps.setString(1, dataInizio);
        	ps.setString(2, dataFine);
            OrdiniList ordini = new OrdiniList();
            ResultSet rs = ps.executeQuery();
            System.out.println("debug FiltroDataServlet: Query eseguita");

            while (rs.next()) {
                System.out.println(rs.getString("id_utente"));
                Ordine o = new Ordine(rs.getInt("id_ordine"), rs.getTimestamp("varData"), rs.getDouble("totale"),
                        rs.getString("id_utente"), rs.getInt("id_indirizzo"));
                query = "SELECT * FROM popola WHERE id_ordine = ?";

                PreparedStatement ps1 = connection.prepareStatement(query);
                ps1.setInt(1, o.getId());
                ResultSet rs1 = ps1.executeQuery();
                System.out.println("debug FiltroDataServlet: Query eseguita");

                while (rs1.next()) {
                    System.out.println(rs1.getString("id_ordine"));
                    query = "SELECT * FROM prodotto WHERE IDProdotto = ?";
                    PreparedStatement ps2 = connection.prepareStatement(query);
                    ps2.setString(1, rs1.getString("id_prodotto"));
                    ResultSet rs2 = ps2.executeQuery();
                    System.out.println("debug FiltroDataServlet: Query eseguita");

                    if (rs2.next()) {
                        Prodotto p = new Prodotto(rs2.getString("IDProdotto"), rs2.getString("nome"),
                                rs2.getString("descrizione"), rs2.getString("img"), rs2.getString("id_categoria"),
                                rs2.getInt("quantita"), rs2.getDouble("costo"));
                        OrdineSingolo os = new OrdineSingolo(o.getId(), rs1.getString("id_prodotto"),
                                rs1.getInt("quantita"), rs1.getDouble("costoAcquisto"), p);
                        o.add(os);
                        System.out.println("debug FiltroDataServlet: o.add");
                    }
                }
                ordini.add(o);
            }
            System.out.println("debug FiltroDataServlet: Fine");

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

    private PreparedStatement createPreparedStatement(Connection connection, String dataInizio, String dataFine,
            String userEmail) throws SQLException {
        String query;
        PreparedStatement ps;
        if (dataFine == null || dataInizio == null) {
            query = "SELECT * FROM ordine WHERE id_utente = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, userEmail);
        } else {
            query = "SELECT * FROM ordine WHERE id_utente = ? AND varData >= ? AND varData <= ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, userEmail);
            ps.setString(2, dataInizio);
            ps.setString(3, dataFine);
        }
        return ps;
    }
}
