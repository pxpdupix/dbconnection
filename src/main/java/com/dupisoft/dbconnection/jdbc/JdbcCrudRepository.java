package com.dupisoft.dbconnection.jdbc;

import com.dupisoft.dbconnection.SQLRepositoryHelper;

public class JdbcCrudRepository<T> implements SQLRepositoryHelper<T> {

    private JdbcRowMapper<T> rowMapper;
    private JdbcRowMapper.FieldsEnum[] fields;

    public JdbcCrudRepository(JdbcRowMapper<T> rowMapper) {
        this.rowMapper = rowMapper;
        this.fields = rowMapper.getFields();
    }

    @Override
    public String generateSelectAll() {
        StringBuilder sb = new StringBuilder("SELECT ");
        for (JdbcRowMapper.FieldsEnum field : fields) {
            sb.append(field.toString()).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(" FROM ").append(rowMapper.getTableName());
        return sb.toString();
    }

    @Override
    public String generateSelectOneWhereFields(JdbcRowMapper.FieldsEnum[] fields) {
        StringBuilder sb = new StringBuilder("SELECT ");
        for (JdbcRowMapper.FieldsEnum field : this.fields) {
            sb.append(field.toString()).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(" FROM ").append(rowMapper.getTableName()).append(" WHERE ");
        for (JdbcRowMapper.FieldsEnum field : fields) {
            sb.append(field.toString());
            sb.append(" = ");
            sb.append("?, ");
            sb.append(" AND ");
        }
        sb.delete(sb.length() - 7, sb.length());
        return sb.toString();
    }

    @Override
    public String generateSelectOneByPrimaryKey(JdbcRowMapper.FieldsEnum PKfield) {
        StringBuilder sb = new StringBuilder("SELECT ");
        for (JdbcRowMapper.FieldsEnum field : this.fields) {
            sb.append(field.toString()).append(", ");
        }
        sb.setLength(sb.length() - 2);
        sb.append(" FROM ").append(rowMapper.getTableName()).append(" WHERE ");
        if (PKfield.isPrimaryKey()) {
            sb.append(PKfield.toString());
            sb.append(" = ");
            sb.append("?");
        }
        return sb.toString();
    }

    @Override
    public String generateInsert() {
        StringBuilder sb = new StringBuilder("INSERT INTO ").append(rowMapper.getTableName()).append(" (");
        StringBuilder lFields = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();
        for (JdbcRowMapper.FieldsEnum field : fields) {
            if (!field.isPrimaryKey() && !field.isAutoIncremented()) {
                lFields.append(field.toString()).append(", ");
                placeholders.append("?, ");
            }
        }
        lFields.setLength(lFields.length() - 2);
        placeholders.setLength(placeholders.length() - 2);
        sb.append(lFields).append(") VALUES (").append(placeholders).append(")");
        return sb.toString();

    }

    @Override
    public String generateUpdate() {
        return "";
    }

    @Override
    public String generateDelete() {
        return "";
    }
}
