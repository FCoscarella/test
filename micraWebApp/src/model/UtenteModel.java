package model;

import java.time.LocalDate;

public class UtenteModel {
    
    public static Utente creaUtente(String email, String password){
        Utente user = Utente.getInstance();
        
        user.setEmail(email);
        user.setPassword(password);
        
        return user;
    }
    
    public static Utente creaUtente(
            String email, 
            String password, 
            String nome, 
            String cognome,
            LocalDate dataNascita
    ){
        Utente user = Utente.getInstance();
        
        user.setEmail(email);
        user.setPassword(password);
        user.setNome(nome);
        user.setCognome(cognome);
        user.setDataNascita(dataNascita);
        user.setIdCategoria(1);
        
        return user;
    }
    
}
