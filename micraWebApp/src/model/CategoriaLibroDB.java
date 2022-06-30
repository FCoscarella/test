package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class CategoriaLibroDB {

    //SELECT LIST TIPOMOTORE
    public static List<CategoriaLibro> selectAll() throws NamingException, SQLException {

        List<CategoriaLibro> list = new ArrayList<>();

        Connection conn = DB.getConnection();

        String query = "SELECT * FROM categorialibro";

        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
        	CategoriaLibro a = new CategoriaLibro();

            a.setId(rs.getLong("id"));
            a.setDescrizione(rs.getString("descrizione"));

            list.add(a);
        }

        conn.close();

        return list;

    }

}
