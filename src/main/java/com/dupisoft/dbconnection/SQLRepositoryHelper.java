package com.dupisoft.dbconnection;

import com.dupisoft.dbconnection.jdbc.JdbcRowMapper;

public interface SQLRepositoryHelper<T> {

    String generateSelectAll();

    String generateSelectOneByPrimaryKey(JdbcRowMapper.FieldsEnum field);

    String generateSelectOneWhereFields(JdbcRowMapper.FieldsEnum[] fields);

    String generateInsert();

    String generateUpdate();

    String generateUpdateFields(JdbcRowMapper.FieldsEnum[] fields);

    String generateDelete();
}
