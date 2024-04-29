package com.dupisoft.dbconnection.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class JdbcRowMapper<T extends Object> {

    public interface FieldsEnum {

        public boolean isPrimaryKey();
        public boolean isAutoIncremented();
    }

    private FieldsEnum[] fields;
    private String tableName;

    protected void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return this.tableName;
    }

    protected void setFields(FieldsEnum[] fields) {
        this.fields = fields;
    }

    public FieldsEnum[] getFields() {
        return fields;
    }

    public Collection<String> getFieldsNames() {
        Collection<String> fieldNames = new ArrayList<>();
        for (JdbcRowMapper.FieldsEnum field : getFields()) {
            fieldNames.add(field.toString().toLowerCase());
        }
        return fieldNames;
    }

    public String[] getIDFields() {
        List<String> ids = new ArrayList<>();
        for (FieldsEnum f : fields) {
            if (f.isPrimaryKey()) {
                // Must be indicated in lower case in Progress
                ids.add(f.toString().toLowerCase());
            }
        }
        return ids.toArray(new String[0]);
    }

    public T mapRow(ResultSet rs, int i) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
