package rest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

import com.google.gson.*;
import com.google.gson.stream.JsonReader;

import jsonClasses.Libro;
import jsonClasses.LibroDB;

@Path("/libreria")
public class ServletRest {

	@GET
	@Path("/societa")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSocietyJSON() {

		String r = "";
		java.nio.file.Path path = Paths.get(
				"C:\\Users\\Onix\\Documents\\2022.04.26 - Progetto Test Micra EAR (Eclipse 2020-06)\\micraWebApp\\src\\resources\\jsonUnicoSociSocieta.json");

		try {

			Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);

			r = new JsonParser().parse(reader).toString();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return Response.ok(r).build();

	}

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
			r = "Si è verificato un errore Naming";
		} catch (SQLException ex) {
			r = "Si è verificato un errore SQL";
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
			r = "Si è verificato un errore Naming";
		} catch (SQLException ex) {
			r = "Si è verificato un errore SQL";
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
