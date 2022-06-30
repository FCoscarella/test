package model;

import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;

public class StatoLibroModel {
    
    private static List<StatoLibro> lista = null;
    
    public static List<StatoLibro> getLista() throws NamingException, SQLException{
                
        lista = StatoLibroDB.selectAll();
        
        return lista;
    }
    
}
