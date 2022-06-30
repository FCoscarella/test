package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class OrdineDB {
    
    //VOID INSERISCI ORDINE IN DB
    public static void insert(Ordine o) throws NamingException, SQLException {

        Connection conn = DB.getConnection();

        String query = "INSERT INTO ordine (dataCreazione, emailUtente, idLibro) "
                + " VALUES (?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setTimestamp(1, o.getDataCreazione());
        stmt.setString(2, o.getEmailUtente());
        stmt.setLong(3, o.getIdLibro());

        stmt.executeUpdate();

        conn.close();

    }
    
    //SELECT LIST libro
    public static List<Ordine> selectAll(String email) throws NamingException, SQLException {

        List<Ordine> list = new ArrayList<>();

        Connection conn = DB.getConnection();
        
        String query = "SELECT * FROM ordine WHERE emailUtente = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, email);
        
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Ordine o = new Ordine();
            
            o.setId(rs.getLong("id"));
            o.setDataCreazione(rs.getTimestamp("dataCreazione"));
            o.setEmailUtente(rs.getString("emailUtente"));
            o.setIdLibro(rs.getLong("idLibro"));
            
            list.add(o);
        }

        conn.close();

        return list;

    }

    public static void delete(Long id) throws NamingException, SQLException {
        
        Connection conn = DB.getConnection();

        String query = "DELETE FROM ordine WHERE id = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setLong(1, id);

        stmt.executeUpdate();

        conn.close();
        
    }
    
}
