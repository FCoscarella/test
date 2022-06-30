package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

public class LibroDB {
	
	//INSERISCI LIBRO IN DB
	public static void insert(Libro a) throws NamingException, SQLException {

        Connection conn = DB.getConnection();

        String query = "INSERT INTO libro (titolo, autore, anno, idcategorialibro, idstatolibro) "
                + " VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, a.getTitolo());
        stmt.setString(2, a.getAutore());
        stmt.setInt(3, a.getAnno());
        stmt.setLong(4, a.getIdCategoriaLibro());
        stmt.setLong(5, a.getIdStatoLibro());

        stmt.executeUpdate();

        conn.close();

    }
    
    //VOID AGGIORNA AUTO IN DB
    public static void update(Libro a) throws NamingException, SQLException {
        
        Connection conn = DB.getConnection();

        String query = "UPDATE libro "
                + " SET titolo = ?, autore = ?, anno = ?, idcategorialibro = ? "
                + " WHERE id = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, a.getTitolo());
        stmt.setString(2, a.getAutore());
        stmt.setInt(3, a.getAnno());
        stmt.setLong(4, a.getIdCategoriaLibro());
        stmt.setLong(5, a.getId());

        stmt.executeUpdate();

        conn.close();
        
    }
    
    //SELECT LIST AUTO
    public static List<Libro> selectAll(Integer stato) throws NamingException, SQLException {

        List<Libro> list = new ArrayList<>();

        Connection conn = DB.getConnection();
        
        String query = "";
        if(stato == 1){
            query = "SELECT * FROM libro WHERE idStatoLibro = 1";
        }else if(stato == 0){
            query = "SELECT * FROM libro";
        }

        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Libro l = new Libro();
            
            l.setId(rs.getLong("id"));
            l.setTitolo(rs.getString("titolo"));
            l.setAutore(rs.getString("autore"));
            l.setAnno(rs.getInt("anno"));
            l.setIdCategoriaLibro(rs.getLong("idCategoriaLibro"));
            l.setIdStatoLibro(rs.getLong("idStatoLibro"));
            
            list.add(l);
        }

        conn.close();

        return list;

    }
    
    //SELECT AUTO BY ID
    public static Libro select(Long id) throws NamingException, SQLException {

        Connection conn = DB.getConnection();
        
        String query = "SELECT * FROM libro WHERE id = ?";
        
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setLong(1, id);
        
        ResultSet rs = stmt.executeQuery();

        Libro l = new Libro();
        while (rs.next()) {
        	l.setId(rs.getLong("id"));
            l.setTitolo(rs.getString("titolo"));
            l.setAutore(rs.getString("autore"));
            l.setAnno(rs.getInt("anno"));
            l.setIdCategoriaLibro(rs.getLong("idCategoriaLibro"));
            l.setIdStatoLibro(rs.getLong("idStatoLibro"));
        }

        conn.close();

        return l;

    }

    //CAMBIA STATO AUTO
    public static void changeState(Long id, Integer state) throws NamingException, SQLException {
        
        Connection conn = DB.getConnection();

        String query = "UPDATE libro SET idstatolibro = ? WHERE id = ?";

        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setLong(1, state);
        stmt.setLong(2, id);

        stmt.executeUpdate();

        conn.close();
        
    }
}
