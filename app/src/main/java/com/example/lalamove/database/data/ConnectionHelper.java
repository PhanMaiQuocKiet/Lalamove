package com.example.lalamove.database.data;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public class ConnectionHelper {
    Connection con;
    String username, password, ip, port, database;

    public Connection connectionClass() {
        ip = "192.168.0.103";
        database = "Lalamove";
        username = "sa";
        password = "123456";
        port = "1433";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" + "databasename=" + database + ";user=" + username + ";password=" + password + ";";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (Exception e) {
            Log.e("ERROR", Objects.requireNonNull(e.getMessage()));
        }
        return connection;
    }
}
