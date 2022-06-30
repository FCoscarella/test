package model;

import java.time.LocalDate;

public class Utente {
    
    private static Utente instance = null;
    
    private String email;
    private String password;
    private String nome;
    private String cognome;
    private LocalDate dataNascita;
    private Integer idCategoria;
    
    public static Utente getInstance(){
        if(instance == null){
            instance = new Utente();
        }
        return instance;
    }

    public static void setInstance(Utente instance) {
        Utente.instance = instance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
    
}
