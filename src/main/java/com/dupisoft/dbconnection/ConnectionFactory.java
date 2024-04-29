package com.dupisoft.dbconnection;

import com.dupisoft.dbconnection.jdbc.MysqlConnectionProvider;
import com.dupisoft.dbconnection.jdbc.OracleConnectionProvider;
import com.dupisoft.dbconnection.jdbc.PostgresConnectionProvider;

import java.sql.Connection;
import java.sql.ConnectionBuilder;
import java.sql.SQLException;
import java.sql.ShardingKey;

public class ConnectionFactory {

    public static ConnectionFactoryBuilder engine(SupportedEngine supportedEngine) {
        return new ConnectionFactoryBuilder(supportedEngine);
    }
    public static class ConnectionFactoryBuilder implements ConnectionBuilder {
        private static final String DEFAULT_HOST = "localhost";
        private static final String DEFAULT_SCHEMA = "accesodatos";
        private final SupportedEngine dataBase;
        private String host = DEFAULT_HOST;
        private String schema = DEFAULT_SCHEMA;
        private Integer port;
        String user;
        String passwd;
        String url;

        private ConnectionFactoryBuilder(SupportedEngine supportedEngine) {
            this.dataBase = supportedEngine;
        }

        public ConnectionFactoryBuilder host(String value) {
            this.host = value;
            return this;
        }

        public ConnectionFactoryBuilder port(int value) {
            this.port = value;
            return this;
        }

        public ConnectionFactoryBuilder schema(String value) {
            this.schema = value;
            return this;
        }

        @Override
        public ConnectionFactoryBuilder user(String username) {
            this.user = username;
            return this;
        }

        @Override
        public ConnectionFactoryBuilder password(String password) {
            this.passwd = password;
            return this;
        }

        @Override
        public ConnectionBuilder shardingKey(ShardingKey shardingKey) {
            return null;
        }

        @Override
        public ConnectionBuilder superShardingKey(ShardingKey superShardingKey) {
            return null;
        }

        public Connection build() throws SQLException {
            IConnectionProvider connectionProvider = null;

            if (this.port == null || this.port == 0 ) {
                this.port = dataBase.getDefaultPort();
            }
            switch (dataBase) {
                case MYSQL -> connectionProvider = new MysqlConnectionProvider(this.user, this.passwd, this.host, this.port, this.schema);
                case POSTGRESQL -> connectionProvider = new PostgresConnectionProvider(this.user, this.passwd, this.host, this.port, this.schema);
                case ORACLE -> connectionProvider = new OracleConnectionProvider(this.user, this.passwd, this.host, this.port, this.schema);
                default -> throw new IllegalArgumentException("Unsupported database type: " + dataBase);
            }
            this.url = connectionProvider.getUrl();

            return connectionProvider.getConnection();
        }
    }
}
