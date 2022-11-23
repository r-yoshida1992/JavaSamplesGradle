package com.example.enums;

public enum ColumnType {
    TINYINT("Byte"),
    SMALLINT("Short"),
    MEDIUMINT("Integer"),
    INT("Integer"),
    BIGINT("Long"),
    FLOAT("Float"),
    DOUBLE("Double"),
    DECIMAL("Double"),
    CHAR("String"),
    VARCHAR("String"),
    TINYBLOB("String"),
    BLOB("String"),
    MEDIUMBLOB("String"),
    LONGBLOB("String"),
    TINYTEXT("String"),
    TEXT("String"),
    MEDIUMTEXT("String"),
    LONGTEXT("String"),
    DATE("String"),
    TIME("String"),
    DATETIME("String"),
    TIMESTAMP("String"),
    YEAR("String");

    private final String objectType;

    ColumnType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectType() {
        return objectType;
    }
}
