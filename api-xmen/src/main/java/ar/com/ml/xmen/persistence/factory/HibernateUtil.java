package ar.com.ml.xmen.persistence.factory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.github.fluent.hibernate.cfg.scanner.EntityScanner;

import ar.com.ml.xmen.persistence.entity.Human;
import ar.com.ml.xmen.utils.PropertiesUtil;
import ar.com.ml.xmen.utils.encrypter.Encrypter;

public class HibernateUtil {

	private static StandardServiceRegistry registry;
	private static SessionFactory sessionFactory;
	private static Properties properties = new Properties();
	
	private HibernateUtil() {}
	
	public static SessionFactory getSessionFactory() {
		
		if (sessionFactory == null) {
			try {
				properties = PropertiesUtil.loadPropertyResource(PropertiesUtil.DATABASE_PROPERTIES);
		        
		        // Hibernate settings equivalent to hibernate.cfg.xml's properties
	            Map<String, String> dbSettings = new HashMap<>();
	            dbSettings.put(Environment.DRIVER, properties.getProperty(PropertiesUtil.DB_DRIVER_CLASS));
	            dbSettings.put(Environment.URL, Encrypter.decrypt(properties.getProperty(PropertiesUtil.DB_URL)));
	            dbSettings.put(Environment.USER, Encrypter.decrypt(properties.getProperty(PropertiesUtil.DB_USER)));
	            dbSettings.put(Environment.PASS, Encrypter.decrypt(properties.getProperty(PropertiesUtil.DB_PASSWORD)));
	            dbSettings.put(Environment.DIALECT, properties.getProperty(PropertiesUtil.DB_DIALECT));
	            dbSettings.put(Environment.SHOW_SQL, properties.getProperty(PropertiesUtil.DB_SHOW_SQL));
	            dbSettings.put(Environment.FORMAT_SQL, properties.getProperty(PropertiesUtil.DB_FORMAT_SQL));
				
	            // Create registry builder
	            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
	            registryBuilder.applySettings(dbSettings);
	            
				// Create registry
				registry = registryBuilder.build();

				// Create MetadataSources
				MetadataSources sources = new MetadataSources(registry).addAnnotatedClass(Human.class);
				
				// Add Entities Dynamically
				List<Class<?>> classes = EntityScanner.scanPackages("ar.com.ml.xmen.persistence.entity").result();
				for (Class<?> annotatedClass : classes) {
					sources.addAnnotatedClass(annotatedClass);
				}
				
				// Create Metadata
				Metadata metadata = sources.getMetadataBuilder().build();

				// Create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();

			} catch (Exception e) {
				e.printStackTrace();
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		return sessionFactory;
	}

	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}