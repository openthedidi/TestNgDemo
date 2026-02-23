package com.framework.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public final class JsonDataUtils {

    private JsonDataUtils() {
    }

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static Object[][] getTestData(String jsonFilePath) {
        try {
            List<HashMap<String, String>> data = objectMapper.readValue(
                    new File(jsonFilePath),
                    new TypeReference<List<HashMap<String, String>>>() {}
            );
            Object[][] result = new Object[data.size()][1];
            for (int i = 0; i < data.size(); i++) {
                result[i][0] = data.get(i);
            }
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON test data from: " + jsonFilePath, e);
        }
    }
}
