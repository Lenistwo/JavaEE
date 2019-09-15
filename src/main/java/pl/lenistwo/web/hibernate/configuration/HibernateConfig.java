package pl.lenistwo.web.hibernate.configuration;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import pl.lenistwo.web.hibernate.entities.Groups;
import pl.lenistwo.web.hibernate.entities.Specializations;
import pl.lenistwo.web.hibernate.entities.Students;
import pl.lenistwo.web.hibernate.entities.StudentsGroup;


public class HibernateConfig {

    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url",
                "jdbc:mysql://localhost:3306/students?useSSL=false&serverTimezone=UTC");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        configuration.setProperty("hibernate.connection.provider_class","com.zaxxer.hikari.hibernate.HikariConnectionProvider");
        configuration.setProperty("hibernate.hikari.connectionTimeout","20000");
        configuration.setProperty("hibernate.hikari.minimumIdle","10");
        configuration.setProperty("hibernate.hikari.maximumPoolSize","20");
        configuration.setProperty("hibernate.hikari.idleTimeout","300000");
        configuration.setProperty("cachePrepStmts","true");
        configuration.setProperty("prepStmtCacheSize","250");
        configuration.setProperty("prepStmtCacheSqlLimit","2048");
        configuration.setProperty("useServerPrepStmts","true");
        configuration.setProperty("useLocalSessionState","true");
        configuration.setProperty("cacheResultSetMetadata","true");
        configuration.setProperty("cacheServerConfiguration","true");
        configuration.setProperty("elideSetAutoCommits","true");
        configuration.setProperty("maintainTimeStats","false");
        configuration.setProperty("rewriteBatchedStatements","true");
        configuration.addAnnotatedClass(Students.class);
        configuration.addAnnotatedClass(StudentsGroup.class);
        configuration.addAnnotatedClass(Groups.class);
        configuration.addAnnotatedClass(Specializations.class);

        return configuration;
    }

    public SessionFactory getSessionFactory() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().
                applySettings(getConfiguration().getProperties()).build();

        return getConfiguration().buildSessionFactory(registry);
    }

    public static Session getInstanceOfSession() {
        HibernateConfig config = new HibernateConfig();
        return config.getSessionFactory().openSession();
    }
}
