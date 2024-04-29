package com.dupisoft.dbconnection;

public enum SupportedEngine {
    ORACLE("oracle", 1521),
    POSTGRESQL("postgresql", 5432),
    MYSQL("mysql", 3306),
    SQLSERVER("sqlserver", 1433);

    private final String connector;
    private final int defaultPort;
    private SupportedEngine(final String connector, final int defaultPort) {
        this.connector = connector;
        this.defaultPort = defaultPort;
    }

    public String getConnector() {
        return connector;
    }

    public int getDefaultPort() {
        return defaultPort;
    }

    public static SupportedEngine valueOfStr(String value) {
        for (SupportedEngine item : values()) {
            if (item.getConnector().equalsIgnoreCase(value)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Invalid int value (" + value + ") for SupportedEngine");
    }
}
