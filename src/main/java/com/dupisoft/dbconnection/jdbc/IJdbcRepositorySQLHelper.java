package com.dupisoft.dbconnection.jdbc;

public interface IJdbcRepositorySQLHelper<T> {
    String generateSelectAll();
    String generateInsert();
    String generateUpdate();
    String generateDelete();
}
