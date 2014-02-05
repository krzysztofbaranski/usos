package app;

import controler.Settings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by krzysztof on 05/02/14.
 */
public class Database {
    private static boolean _lock = false;
    public static Connection connection = null;
    public static long timeToClose = 60; // in seconds


    /**
     *
     * Make connection to database
     *
     */
    public static void connect() {
        // laduje sterownik
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch(InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        try {
            connection = DriverManager.getConnection("jdbc:postgresql:" + Settings.dbUrl, Settings.username, Settings.passwd);
        } catch(SQLException e) {
            e.printStackTrace();
        }

        Thread closeConnection = new Thread(new Runnable() {
            @Override
            public void run() {
                while(connection != null) {
                    try {
                        Thread.sleep(1000 * timeToClose);
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(!_lock && connection != null) {
                        try {
                            connection.close();
                        } catch(SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        closeConnection.setDaemon(true);
        closeConnection.start();
    }

    /**
     *
     * Lock
     *
     * zapobiega zamknieciu polaczenia, nalezy zalozyc przed czytaniem z bazy
     */
    public static void lock() {
        _lock = true;
    }

    /**
     *
     * Unlock
     *
     */
    public static void unlock() {
        _lock = false;
    }
}
