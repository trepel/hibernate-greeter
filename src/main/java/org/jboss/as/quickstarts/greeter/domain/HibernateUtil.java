package org.jboss.as.quickstarts.greeter.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory buildSessionFactory() {
        try {

            Configuration configuration = new Configuration();
//            configuration.configure();

            configuration
                .setProperty("hibernate.connection.datasource", "java:jboss/datasources/ExampleDS")
                .setProperty("hibernate.connection.username", "sa")
                .setProperty("hibernate.connection.password", "sa")
                .setProperty("hibernate.connection.autocommit", "false")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
                .setProperty("hibernate.hbm2ddl.auto", "")
                .setProperty("hibernate.current_session_context_class", "jta")
                .setProperty("hibernate.transaction.jta.platform", "JBossAS")
//                .setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.CMTTransactionFactory")
                .setProperty("show_sql", "true")
                .setProperty("format_sql", "true")
                .addAnnotatedClass( User.class );

            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
            SessionFactory f = configuration.buildSessionFactory(serviceRegistry);

            Session session = f.getCurrentSession();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS USERS (ID INT auto_increment PRIMARY KEY, "
                + "USERNAME VARCHAR(255), "
                + "FIRSTNAME VARCHAR(255), "
                + "LASTNAME VARCHAR(255))").executeUpdate();

            session.createSQLQuery("CREATE SEQUENCE IF NOT EXISTS HIBERNATE_SEQUENCE").executeUpdate();

            session.createSQLQuery("INSERT INTO USERS (ID, USERNAME, FIRSTNAME, LASTNAME) "
                + "values (-1, 'jdoe', 'John', 'Doe')").executeUpdate();
            session.createSQLQuery("INSERT INTO USERS (ID, USERNAME, FIRSTNAME, LASTNAME) "
                + "values (-2, 'emuster', 'Erika', 'Mustermann')").executeUpdate();

            return f;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}
