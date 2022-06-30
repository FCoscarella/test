package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

class StatoLibroDB {
    
    //SELECT LIST STATO LIBRO
    static List<StatoLibro> selectAll() throws NamingException, SQLException {

        List<StatoLibro> list = new ArrayList<>();

        Connection conn = DB.getConnection();

        String query = "SELECT * FROM statolibro";

        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            StatoLibro s = new StatoLibro();

            s.setId(rs.getLong("id"));
            s.setDescrizione(rs.getString("descrizione"));

            list.add(s);
        }

        conn.close();

        return list;

    }
    
}
