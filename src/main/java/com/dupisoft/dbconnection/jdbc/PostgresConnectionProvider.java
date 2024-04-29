package com.dupisoft.dbconnection.jdbc;

import com.dupisoft.dbconnection.SupportedEngine;

public class PostgresConnectionProvider extends ConnectionProvider {

    private static final SupportedEngine POSTGRESQL_ENGINE = SupportedEngine.POSTGRESQL;

    public PostgresConnectionProvider(String user, String passwd, String host, int port, String schema) {
        super(user, passwd, host, port, schema);
        this.url = constructFullUrl();
    }

    private String constructFullUrl() {
        return "jdbc:" + POSTGRESQL_ENGINE.getConnector() + "://" + this.host + ":" + this.port + "/" + this.schema;
    }
}
