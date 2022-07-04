package jsonClasses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import model.DB;

public class LibroDB {
	
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
            
            l.setCategoriaLibro(LibroModel.selectCategoria(rs.getLong("idCategoriaLibro")));
            
            l.setStatoLibro(LibroModel.selectStato(rs.getLong("idStatoLibro")));
            
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
            l.setCategoriaLibro(LibroModel.selectCategoria(rs.getLong("idCategoriaLibro")));
            l.setStatoLibro(LibroModel.selectStato(rs.getLong("idStatoLibro")));
        }

        conn.close();

        return l;

    }
    
    //INSERISCI LIBRO IN DB
  	public static Libro insert(Libro libro) throws NamingException, SQLException {

          Connection conn = DB.getConnection();

          String query = "INSERT INTO libro (titolo, autore, anno, idcategorialibro, idstatolibro) "
                  + " VALUES (?, 'a', 2000, 1, 1)";

          PreparedStatement stmt = conn.prepareStatement(query);
          stmt.setString(1, libro.getTitolo());
//          stmt.setString(2, libro.getAutore());
//          stmt.setInt(3, libro.getAnno());
//          stmt.setLong(4, libro.getCategoriaLibro().getId());
//          stmt.setLong(5, libro.getStatoLibro().getId());

          stmt.executeUpdate();

          conn.close();
          
          return libro;

      }
    
}
