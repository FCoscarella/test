package model;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import javax.naming.NamingException;

public class OrdineModel {
    
    private static List<Ordine> lista = null;
    
    public static Ordine creaOrdine(Timestamp dataCreazione, String emailUtente, Long idLibro) {
        Ordine ordine = new Ordine();
        
        ordine.setDataCreazione(dataCreazione);
        ordine.setEmailUtente(emailUtente);
        ordine.setIdLibro(idLibro);
        
        return ordine;
    }
    
    public static List<Ordine> getLista(String email) throws NamingException, SQLException{
        
        lista = OrdineDB.selectAll(email);
        
        return lista;
    }
    
}
