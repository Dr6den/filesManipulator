package com.mycompany.filesmanipulator.dao;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.extras.codecs.jdk8.InstantCodec;
import com.mycompany.filesmanipulator.dao.entity.FileStatistics;
import com.mycompany.filesmanipulator.dao.entity.Flat;
import com.mycompany.filesmanipulator.dao.entity.FlatOwner;
import com.mycompany.filesmanipulator.dao.entity.TextLine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author andrew
 */
@org.springframework.context.annotation.Configuration
public class SessionManager {
    private static SessionFactory sessionFactory;
    private static Cluster cluster;
     
    @Bean(name="MySql&MariaDbConnection")
    public static Session getMysqlSession() {
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
    
    @Bean(name="CassandraSession")
    public static com.datastax.driver.core.Session getCassandraSession() {
        Cluster cluster = Cluster.builder().addContactPoints("127.0.0.1").build();
        cluster.getConfiguration().getCodecRegistry().register(InstantCodec.instance);
        return cluster.connect();
    }
}
