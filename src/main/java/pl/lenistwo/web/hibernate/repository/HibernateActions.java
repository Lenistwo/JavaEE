package pl.lenistwo.web.hibernate.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateActions {

    private final Session session;

    public HibernateActions(Session session) {
        this.session = session;
    }

    public void saveOrUpdateObject(Object object) {
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
        }
    }

    public Object getSingleObject(String table, String idName, int id) {
        return session.createQuery("from " + table + " where " + idName + " =" + id).uniqueResult();
    }

    public void deleteObject(String table, String idName, int id) {
        Object object = session.createQuery("from " + table + " where " + idName + " =" + id).uniqueResult();
        if (object == null) {
            return;
        }
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(object);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    public List getAllObjects(String table) {
        Query query = session.createQuery("from " + table);
        return query.getResultList();
    }
}
