package org.aom.utils;

import jakarta.persistence.EntityManagerFactory;
import org.aom.config.MyCustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  : Abhishek
 * @since   : 2024-04-10, Wednesday
 **/
public class HibernateUtils {
    public static final EntityManagerFactory createEntityManagerFactory(){
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.format_sql", "true");
        //props.put("hibernate.hbm2ddl.auto", "validate");
        //props.put("hibernate.hbm2ddl.auto", "create");
        props.put("hibernate.hbm2ddl.auto", "none");
        EntityManagerFactory emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(new MyCustomPersistenceUnitInfo(), props);
        return emf;
    }

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = HibernateUtils.createEntityManagerFactory();
        System.out.println("entityManagerFactory = " + entityManagerFactory);
    }

    /**
     * Create EntityManagerFactory using persistence xml
     * @return
     */
//    public static final EntityManagerFactory createEntityManagerFactory(){
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit", new HashMap());
//        return emf;
//    }


//    private static SessionFactory sessionFactory;
//    public static final SessionFactory createSessionFactoryFromXmlFile() {
//        try {
//            if (sessionFactory == null) {
//                System.out.println(">>>>>>>>>>>>Creating session factory using hibernate xml file");
//                Configuration configuration = new Configuration();
//                configuration.configure("hibernate.cfg.xml");   //this file name is by convention, hence not necessary to specify the file name if you are using this file name for your configuration file.
//                configuration.addAnnotatedClass(Movie.class);
//                sessionFactory = configuration.buildSessionFactory();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Exception creating SessionFactory with exception: " + e.getMessage());
//        }
//        return sessionFactory;
//    }
}
