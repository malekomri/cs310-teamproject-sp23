package edu.jsu.mcis.cs310.tas_sp23.dao;

import java.io.*;
import java.util.Properties;

public class DAOProperties {

    private static final String PROPERTIES_FILE = "dao.properties";
    private static final Properties PROPERTIES = new Properties();

    private final String prefix;

    static {

        InputStream file = null;

        try {

            file = DAOProperties.class.getResourceAsStream(PROPERTIES_FILE);
            PROPERTIES.load(file);

        } catch (IOException e) {
            throw new DAOException(e.getMessage());
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    throw new DAOException(e.getMessage());
                }
            }
        }

    }

    public DAOProperties(String prefix) {

        this.prefix = prefix;

    }

    public String getProperty(String key) {

        String fullKey = prefix + "." + key;
        String property = PROPERTIES.getProperty(fullKey);

        if (property != null && property.trim().isEmpty()) {
            property = null;
        }

        return property;

    }

}
