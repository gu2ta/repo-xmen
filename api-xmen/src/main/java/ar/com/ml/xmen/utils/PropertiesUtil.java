package ar.com.ml.xmen.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	// Property DB
	public static final String DATABASE_PROPERTIES = "database.properties";
	
	// Parametros DB
	public static final String DB_DRIVER_CLASS = "driver_class";
	public static final String DB_URL = "url";
	public static final String DB_USER = "username";
	public static final String DB_PASSWORD = "password";
	public static final String DB_DIALECT = "dialect";
	public static final String DB_SHOW_SQL = "show_sql";
	public static final String DB_FORMAT_SQL = "format_sql";
	
	public static Properties loadPropertyRemote(String propertyName) throws Exception {
		
		Properties properties = null;
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(PropertiesUtil.class.getResourceAsStream(propertyName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return properties;
	}
	
	public static Properties loadPropertyResource(String propertyName) throws Exception {
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();
		
		try (InputStream resourceStream = loader.getResourceAsStream(propertyName)) {
			properties.load(resourceStream);
		} catch (Exception e) {
            e.printStackTrace();
        }
		
        return properties;
	}
	
}
