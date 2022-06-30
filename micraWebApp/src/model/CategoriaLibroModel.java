package model;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

public class CategoriaLibroModel {
    
    private static List<CategoriaLibro> lista = null;
    
    public static List<CategoriaLibro> getLista() throws NamingException, SQLException{
                
        lista = CategoriaLibroDB.selectAll();
        
        return lista;
    }
    
}
