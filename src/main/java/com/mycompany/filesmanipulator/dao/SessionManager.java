package com.mycompany.filesmanipulator.dao;

import com.mycompany.filesmanipulator.dao.entity.FileStatistics;
import com.mycompany.filesmanipulator.dao.entity.Flat;
import com.mycompany.filesmanipulator.dao.entity.FlatOwner;
import com.mycompany.filesmanipulator.dao.entity.TextLine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author andrew
 */
public class SessionManager {
    private static SessionFactory sessionFactory;
     
    public static Session getSession() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(FileStatistics.class);
            configuration.addAnnotatedClass(TextLine.class);
            configuration.addAnnotatedClass(FlatOwner.class);
            configuration.addAnnotatedClass(Flat.class);
            ServiceRegistry serviceRegistry
                = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
             
            // builds a session factory from the service registry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);           
        }
         
        return sessionFactory.openSession();
    }
}
