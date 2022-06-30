package jsonClasses;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import model.CategoriaLibro;
import model.CategoriaLibroModel;
import model.StatoLibro;
import model.StatoLibroModel;

public class LibroModel {
	
	public static CategoriaLibro selectCategoria(Long id) throws NamingException, SQLException {
		
		CategoriaLibro cat = new CategoriaLibro();
		
		List<CategoriaLibro> lista = CategoriaLibroModel.getLista();
		
		for(CategoriaLibro c : lista) {
			if(c.getId() == id) {
				cat = c;
			}
		}
		
		return cat;
		
	}
	
	public static StatoLibro selectStato(Long id) throws NamingException, SQLException {
		
		StatoLibro state = new StatoLibro();
		
		List<StatoLibro> lista = StatoLibroModel.getLista();
		
		for(StatoLibro s : lista) {
			if(s.getId() == id) {
				state = s;
			}
		}
		
		return state;
		
	}
	
}
