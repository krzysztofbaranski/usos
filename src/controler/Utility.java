package controler;

import app.Database;

import javax.xml.crypto.Data;
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
    public static Vector<Vector<Object>> getData(String query) throws Throwable {
        try {
            Database.lock();
            if(Database.connection == null)
                Database.connect();

            // medium do transmisji danych
            Statement st = Database.connection.createStatement();

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

            // zamykam medium
            rs.close();
            st.close();

            Database.unlock();

            return ret;
        } catch(SQLException e) {
            Database.unlock();
            throw e.getCause();
        }
    }

    /**
     * insert, update or delete data
     *
     * @param insert
     * @return
     */
    public static void updateData(String insert) {
        try {
            Database.lock();
            if(Database.connection == null)
                Database.connect();

            // medium do transmisji danych
            Statement st = Database.connection.createStatement();

            // wykonuje wstawianie
            st.executeUpdate(insert);

            //zamykam polaczenie
            st.close();
            Database.unlock();
        } catch(SQLException e) {
            Database.unlock();
            e.printStackTrace();
        }
    }
}
