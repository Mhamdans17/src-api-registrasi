package com.api.registrasi.util;

public class ValidationUtil {

    private ValidationUtil() {

    }

    public static void validateNotNullOrEmpty(String value, String fieldName) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException(fieldName + " tidak boleh kosong");
        }
    }
}
