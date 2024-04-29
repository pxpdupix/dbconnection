package com.dupisoft.dbconnection.jdbc;

import com.dupisoft.dbconnection.IConnectionProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

abstract class ConnectionProvider implements IConnectionProvider {
    private Connection connection;
    Properties info = new Properties();
    final String user;
    final String passwd;
    String host;
    int port;
    String schema;
    String url;

    public ConnectionProvider(String user, String passwd, String host, int port, String schema) {
        this.user = user;
        this.passwd = passwd;
        this.host = host;
        this.port = port;
        this.schema = schema;
        if (user != null) {
            info.setProperty("user", user);
        }
        if (passwd != null) {
            info.setProperty("password", passwd);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, info);
    }

    @Override
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public String getUrl() {
        return this.url;
    }
}
