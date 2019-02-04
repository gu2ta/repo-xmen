package ar.com.ml.xmen.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	
	// Property DB
	public static final String DATABASE_PROPERTIES = "database.properties";
	
	// Parametros DB - Development connection
	public static final String DB_DRIVER_CLASS_DEV = "driver_class_development";
	public static final String DB_URL_DEV = "url_development";
	public static final String DB_USER_DEV = "username_development";
	public static final String DB_PASSWORD_DEV = "password_development";
	public static final String DB_DIALECT_DEV = "dialect_development";
	
	// Parametros DB - Production connection
	public static final String DB_DRIVER_CLASS_PROD = "driver_class_production";
	public static final String DB_URL_PROD = "url_production";
	public static final String DB_USER_PROD = "username_production";
	public static final String DB_PASSWORD_PROD = "password_production";
	public static final String DB_DIALECT_PROD = "dialect_production";
	
	// Otros Parametros DB
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
