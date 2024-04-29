package com.dupisoft.dbconnection.jdbc;


import com.dupisoft.dbconnection.SupportedEngine;

public class MysqlConnectionProvider extends ConnectionProvider {

    private static final SupportedEngine MYSQL_ENGINE = SupportedEngine.MYSQL;
    public MysqlConnectionProvider(String user, String passwd, String host, int port, String schema) {
        super(user, passwd, host, port, schema);
        this.url = constructFullUrl();
    }

    private String constructFullUrl() {
        return "jdbc:" + MYSQL_ENGINE.getConnector() + "://" + this.host + ":" + this.port + "/" + this.schema;
    }
}
