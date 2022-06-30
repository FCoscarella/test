package model;

public class Libro {
	
	private Long id;
	private String titolo;
	private String autore;
	private Integer anno;
	private Long idCategoriaLibro;
	private Long idStatoLibro;
	
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
	public Long getIdCategoriaLibro() {
		return idCategoriaLibro;
	}
	public void setIdCategoriaLibro(Long idCategoriaLibro) {
		this.idCategoriaLibro = idCategoriaLibro;
	}
	public Long getIdStatoLibro() {
		return idStatoLibro;
	}
	public void setIdStatoLibro(Long idStatoLibro) {
		this.idStatoLibro = idStatoLibro;
	}
	
	

}
