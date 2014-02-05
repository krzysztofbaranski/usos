package controler;

import java.sql.*;
import java.util.Vector;

/**
 * Created by krzysztof on 03/02/14.
 */
public class Utility {

    /**
     *
     * get data
     *
     * @param query
     * @return
     */
    public static Vector<Vector<Object>> getData(String query) {

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
            dbcon = DriverManager.getConnection("jdbc:postgresql:" + Settings.dbUrl, Settings.username, Settings.passwd);

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

            //zamykam polaczenie
            rs.close();
            st.close();
            dbcon.close();

            return ret;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * insert, update or delete data
     *
     * @param insert
     * @return
     */
    public static void updateData(String insert) {

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
            dbcon = DriverManager.getConnection("jdbc:postgresql:" + Settings.dbUrl, Settings.username, Settings.passwd);

            // medium do transmisji danych
            Statement st = dbcon.createStatement();

            // wykonuje wstawianie
            st.executeUpdate(insert);

            //zamykam polaczenie
            st.close();
            dbcon.close();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
