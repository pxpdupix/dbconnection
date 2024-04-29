package com.dupisoft.dbconnection;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionProvider {
    Connection getConnection() throws SQLException;
    void closeConnection() throws SQLException;
    String getUrl();

}
