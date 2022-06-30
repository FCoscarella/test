package model;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

public class LibroModel {
	
	private static List<Libro> lista = null;
    
    public static Libro creaLibro(String titolo, String autore, Integer anno, Long idCategoriaLibro){
    	Libro libro = new Libro();
        
    	libro.setTitolo(titolo);
        libro.setAutore(autore);
        libro.setAnno(anno);
        libro.setIdCategoriaLibro(idCategoriaLibro);
        libro.setIdStatoLibro(1L);
        
        return libro;
    }

    public static Libro creaLibro(Long id, String titolo, String librore, Integer anno, Long idCategoriaLibro) {
    	Libro libro = new Libro();
        
        libro.setId(id);
        libro.setTitolo(titolo);
        libro.setAutore(librore);
        libro.setAnno(anno);
        libro.setIdCategoriaLibro(idCategoriaLibro);
        libro.setIdStatoLibro(1L);
        
        return libro;
    }
    
    public static List<Libro> getLista(Integer categoriaUtente) throws NamingException, SQLException{
        
        Integer stato = 1;
        
        if(categoriaUtente == 2){
            stato = 0;
        }
        
        lista = LibroDB.selectAll(stato);
        
        return lista;
    }
}
