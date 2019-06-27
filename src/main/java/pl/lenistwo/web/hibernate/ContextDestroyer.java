package pl.lenistwo.web.hibernate;

import org.hibernate.Session;
import pl.lenistwo.web.hibernate.configuration.HibernateConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextDestroyer implements ServletContextListener {

    private final Session session;

    public ContextDestroyer() {
        session = HibernateConfig.getInstanceOfSession();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        session.close();
        System.exit(-1);
    }
}
