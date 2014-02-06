package controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import app.Database;
import app.Window;

import javax.swing.*;
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
    public static Vector<Vector<Object>> getDataWithException(String query) throws SQLException {
        try {
            Database.lock();
            if(Database.connection == null || Database.connection.isClosed())
                try {
                    Database.connect();
                } catch(SQLException e) {
                    int confirm = JOptionPane.showOptionDialog(Window.mainFrame,
                            "Nie można połączyć się z bazą danych! Spróbować ponownie?",
                            "Error", JOptionPane.YES_NO_OPTION,
                            JOptionPane.ERROR_MESSAGE, null, null, null);
                    if (confirm == JOptionPane.NO_OPTION) {
                        System.exit(1);
                    }
                }

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
            throw new SQLException();
        }
    }


    /**
     *  get data or show the message about an error
     * @param query
     * @return
     * @throws Throwable
     */

    public static Vector<Vector<Object>> getData(String query) {
        while(true)
        {
            try {
                return getDataWithException(query);
            } catch (Throwable e){
                //TODO
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ie){
                    //do nothing, no harm will come if the query will be repeated a bit earlier
                }
            }
        }
    }
    /**
     * insert, update or delete data
     *
     * @param insert
     * @return
     */
    public static void updateData(String insert) throws SQLException {
        try {
            Database.lock();
            if(Database.connection == null || Database.connection.isClosed())
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
            throw new SQLException();
        }
    }

    private static String myToString(Object o)
    {
        if(o.getClass() == String.class)
            return "\'" + o + "\'";
        return o.toString();
    }

    public static boolean insertSchema (String relation, Object... values)
    {
        StringBuilder valuesString = new StringBuilder();
        StringBuilder columnsString = new StringBuilder();
        boolean start = true;
        int count = 0;
        for (Object o : values)
            if(count++ % 2 == 0) {
                if(start)
                    columnsString.append("" + o);
                else
                    columnsString.append(", " + o);
            }
            else {
                if(start) {
                    start = false;
                    valuesString.append("" + myToString(o));
                }
                else
                    valuesString.append(", " + myToString(o));
            }

        System.out.println(columnsString);
        System.out.println(valuesString);
        try {
            Utility.updateData("insert into " + relation + " (" + columnsString + ") values (" + valuesString + ")");
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
