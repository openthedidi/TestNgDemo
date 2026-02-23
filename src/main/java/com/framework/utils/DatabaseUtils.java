package com.framework.utils;

import com.framework.constants.FrameworkConstants;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public final class DatabaseUtils {

    private DatabaseUtils() {
    }

    public static Object[][] getTestData(String query) {
        Properties props = loadDbProperties();
        String url = props.getProperty("db.url");
        String username = props.getProperty("db.username");
        String password = props.getProperty("db.password");
        String driver = props.getProperty("db.driver");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("DB driver not found: " + driver, e);
        }

        List<HashMap<String, String>> dataList = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                HashMap<String, String> map = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    map.put(metaData.getColumnLabel(i), rs.getString(i));
                }
                dataList.add(map);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query: " + query, e);
        }

        Object[][] result = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            result[i][0] = dataList.get(i);
        }
        return result;
    }

    private static Properties loadDbProperties() {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(FrameworkConstants.DB_PROPERTIES_PATH)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load db.properties from: "
                    + FrameworkConstants.DB_PROPERTIES_PATH, e);
        }
        return props;
    }
}
