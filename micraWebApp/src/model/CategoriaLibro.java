package model;

public class CategoriaLibro {
    
    private Long id;
    private String descrizione;

    public CategoriaLibro(Long id, String descrizione) {
		this.id = id;
		this.descrizione = descrizione;
	}

	public CategoriaLibro() {
		
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    
}
