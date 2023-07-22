package controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/AddProdottoServlet")
@MultipartConfig // Per gestire i dati del form che includono file (ad es. l'immagine del prodotto)
public class AddProdottoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(AddProdottoServlet.class.getName());
    private static final String error = "Errore";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nomeProdotto = request.getParameter("nome");
        int quantitaProdotto = Integer.parseInt(request.getParameter("quantita"));
        String categoriaProdotto = request.getParameter("categoria");
        String costo = request.getParameter("costo");
        Part filePart = request.getPart("immagine");
        String nomeImmagine = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

        // Esegui il codice per salvare il nuovo prodotto nel database
        try (Connection connection = DbManager.getConnection();) {
            String query = "INSERT INTO prodotto (nome, quantita, categoria, img, costo, id_categoria) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, nomeProdotto);
            ps.setInt(2, quantitaProdotto);
            ps.setString(3, categoriaProdotto);
            ps.setString(4, nomeImmagine);
            ps.setString(5, costo);
            ps.setString(6, categoriaProdotto);
            int rs = ps.executeUpdate();
            
            if(rs==0) {
            	//errore
            	request.setAttribute("statusAggiunta", "failed");
            	response.sendRedirect("addProdotto.jsp");
            } else {
            	request.setAttribute("statusAggiunta", "success");
            	response.sendRedirect("addProdotto.jsp");
            }
        } catch (SQLException e) {
            logger.log(Level.ALL, error, e);
        }
    }
}
