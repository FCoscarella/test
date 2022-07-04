package rest;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.GsonBuilder;

import jsonClasses.Libro;
import jsonClasses.LibroDB;

@Path("/libreria")
public class ServletRest {
	
	//ripulite librerie non usate
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListaLibri() {

		List<Libro> bookList = null;

		String r = "";

		try {
			bookList = LibroDB.selectAll(0);
			if (bookList.size() > 0) {
				r += "{ \"books\":";
				r += new GsonBuilder().setPrettyPrinting().create().toJson(bookList);
				r += "}";
			} else {
				r = "Nessun libro in lista";
			}
		} catch (NamingException ex) {
			r = "Si e' verificato un errore Naming";
		} catch (SQLException ex) {
			r = "Si e' verificato un errore SQL";
		}

		return Response.ok(r).build();

	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLibro(@PathParam("id") String id) {

		Libro book = null;
		Long bookId = Long.parseLong(id);

		String r = "";

		try {
			book = LibroDB.select(bookId);
			if (book != null) {
				r = new GsonBuilder().setPrettyPrinting().create().toJson(book);
			} else {
				r = "Nessun libro trovato";
			}
		} catch (NamingException ex) {
			r = "Si e' verificato un errore Naming";
		} catch (SQLException ex) {
			r = "Si e' verificato un errore SQL";
		}

		return Response.ok(r).build();

	}

//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Libro postLibro(Libro book) {
//		
//		Libro ret = null;
//		
//		try {
//			ret = LibroDB.insert(book);
//		} catch (NamingException ex) {
//			
//		} catch (SQLException ex) {
//			
//		}
//		
//		return ret;
//	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.TEXT_PLAIN + "; charset=utf-8")
	@Produces(MediaType.TEXT_PLAIN + "; charset=utf-8")
	public String postLibro(String json) {
		return json == null ? json : "null";
	}

}
