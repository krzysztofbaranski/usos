package controler;

import java.sql.*;
import java.util.Vector;

/**
 * Created by krzysztof on 03/02/14.
 */
public class Utility {
    public static Vector GetData(String query) {

        // laduje sterownik
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch(InstantiationException e) {
            e.printStackTrace();
        } catch(IllegalAccessException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            // lacze sie z baza
            Connection dbcon = null;
            dbcon = DriverManager.getConnection("jdbc:postgresql://localhost/baca", "krzysztof", "");

            // medium do transmisji danych
            Statement st = dbcon.createStatement();

            // wykonuje zapytanie
            ResultSet rs = st.executeQuery(query);

            // przerabiam na vector
            Vector<Vector<Object>> ret = new Vector<Vector<Object>>();
            while(rs.next()) {
                Vector<Object> v = new Vector<Object>();
                for(int i = 1; i <= rs.getMetaData().getColumnCount(); ++i) {
                    v.add(rs.getObject(i));
                }
                ret.add(v);
            }

            return ret;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
