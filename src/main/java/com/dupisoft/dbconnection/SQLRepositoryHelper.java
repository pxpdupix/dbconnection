package com.dupisoft.dbconnection;

public interface SQLRepositoryHelper<T> {

    String generateSelectAll();

    String generateInsert();

    String generateUpdate();

    String generateDelete();
}
