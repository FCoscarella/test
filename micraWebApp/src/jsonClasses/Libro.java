package jsonClasses;

import model.CategoriaLibro;
import model.StatoLibro;

public class Libro {
	
	private Long id;
	private String titolo;
	private String autore;
	private Integer anno;
	private CategoriaLibro categoriaLibro;
	private StatoLibro statoLibro;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	
	public Integer getAnno() {
		return anno;
	}
	public void setAnno(Integer anno) {
		this.anno = anno;
	}
	
	public CategoriaLibro getCategoriaLibro() {
		return categoriaLibro;
	}
	public void setCategoriaLibro(CategoriaLibro categoriaLibro) {
		this.categoriaLibro = categoriaLibro;
	}
	
	public StatoLibro getStatoLibro() {
		return statoLibro;
	}
	public void setStatoLibro(StatoLibro statoLibro) {
		this.statoLibro = statoLibro;
	}

}
