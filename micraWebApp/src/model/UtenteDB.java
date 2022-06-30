package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

public class UtenteDB {

    //BOOLEAN CERCA UTENTE IN DB PER CHECK
    public static Boolean cerca(String email) throws NamingException, SQLException {

        Boolean ret = false;
        
        Connection conn = DB.getConnection();

        String query = "SELECT * FROM utente WHERE email=?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, email);

        ResultSet rs = stmt.executeQuery();

        if (rs.next())
            ret = true;

        conn.close();

        return ret;

    }

    //BOOLEAN CERCA UTENTE IN DB PER LOGIN
    public static Boolean cerca(Utente user) throws NamingException, SQLException {

        Boolean ret = false;
        
        Connection conn = DB.getConnection();

        String query = "SELECT * FROM utente WHERE email=? AND password=?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, user.getEmail());
        stmt.setString(2, user.getPassword());

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            ret = true;

            user.setNome(rs.getString("nome"));
            user.setCognome(rs.getString("cognome"));
            user.setDataNascita(rs.getDate("dataNascita").toLocalDate());
            user.setIdCategoria(rs.getInt("idCategoriaUtente"));

        }

        conn.close();

        return ret;

    }
    
    //VOID INSERISCI UTENTE IN DB
    public static void insert(Utente user) throws NamingException, SQLException{
        
        Connection conn = DB.getConnection();
        
        String query = "INSERT INTO utente (email, password, nome, cognome, datanascita, idcategoriautente) "
                    + " VALUES (?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, user.getEmail());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getNome());
        stmt.setString(4, user.getCognome());
        stmt.setDate(5, Date.valueOf(user.getDataNascita()));
        stmt.setLong(6, user.getIdCategoria());
        
        stmt.executeUpdate();
        
        conn.close();
        
    }
}
