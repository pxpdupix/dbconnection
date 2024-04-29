package com.dupisoft.dbconnection;

import com.dupisoft.dbconnection.jdbc.JdbcRowMapper;

public interface SQLRepositoryHelper<T> {

    String generateSelectAll();

    String generateSelectOneWhereFields(JdbcRowMapper.FieldsEnum fields[]);

    String generateInsert();

    String generateUpdate();

    String generateDelete();
}
