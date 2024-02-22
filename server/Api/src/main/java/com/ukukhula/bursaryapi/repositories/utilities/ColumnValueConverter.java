package com.ukukhula.bursaryapi.repositories.utilities;

public class ColumnValueConverter {

    public static Class<?> getColumnDataType(String columnName) {

        switch (columnName.toLowerCase()) {
            case "StudentID":
                return Integer.class;
            default:
                throw new IllegalArgumentException("Unknown column: " + columnName);
        }
    }

    public static Object convertValueToType(Object value, Class<?> targetType) {

        if (targetType == Integer.class) {
            return Integer.parseInt(value.toString());
        } else if (targetType == String.class) {
            return value.toString();
        }

        throw new IllegalArgumentException("Unsupported data type: " + targetType);
    }
}
