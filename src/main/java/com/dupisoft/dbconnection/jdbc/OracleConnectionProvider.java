package com.dupisoft.dbconnection.jdbc;


import com.dupisoft.dbconnection.SupportedEngine;

public class OracleConnectionProvider extends ConnectionProvider {

    private static final SupportedEngine ORACLE_ENGINE = SupportedEngine.ORACLE;
    public OracleConnectionProvider(String user, String passwd, String host, int port, String schema) {
        super(user, passwd, host, port, schema);
        this.url = constructFullUrl();
    }

    private String constructFullUrl() {
        return "jdbc:" + ORACLE_ENGINE.getConnector() + ":thin:@//" + this.host + ":" + this.port + "/" + this.schema;
    }
}
