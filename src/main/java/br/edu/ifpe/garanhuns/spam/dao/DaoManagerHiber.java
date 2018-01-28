package br.edu.ifpe.garanhuns.spam.dao;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.resource.transaction.spi.TransactionStatus;

/**
 *
 * @author Ester
 */
public class DaoManagerHiber {

    private static DaoManagerHiber myself = null;
    private SessionFactory sessionFactory;
    private Session s;

    private DaoManagerHiber() {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
            s = sessionFactory.openSession();
        } catch (Throwable th) {
            System.err.println("Enitial SessionFactory creation failed" + th);
            throw new ExceptionInInitializerError(th);
        }
    }

    public static DaoManagerHiber getInstance() {
        if (myself == null) {
            myself = new DaoManagerHiber();
        }

        return myself;
    }

    public void persist(Object o) {
        Transaction tr = null;
        try {

            s = sessionFactory.openSession();
            tr = s.beginTransaction();
            s.save(o);
            s.flush();
            tr.commit();
        } catch (RuntimeException e) {
            if (tr != null) {
                tr.rollback();
            }
            throw e;
        } finally {
            s.close();
        }
    }

    public List recover(String hql) {
        List l = null;
        try {
            s = sessionFactory.openSession();
            Query q = s.createQuery(hql);
            l = q.list();
            return l;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            s.close();
        }
    }

    public List recover(String hql, long id) {
        Transaction tr = null;
        List l = null;
        try {
            s = sessionFactory.openSession();
            Query q = s.createQuery(hql);
            q.setLong("id", id);
            s.flush();
            l = q.list();
            return l;
        } catch (RuntimeException e) {
            if (tr != null) {
                tr.rollback();
            }
            throw e;
        } finally {
            s.close();
        }
    }

    public List recoverSQL(String sql) {
        Transaction tr = null;
        List l = null;
        try {
            s = sessionFactory.openSession();
            Query query = s.createSQLQuery(sql);
            s.flush();
            l = query.list();
            return l;
        } catch (RuntimeException e) {
            if (tr != null) {
                tr.rollback();
            }
            throw e;
        } finally {
            s.close();
        }
    }

    public void update(Object o) {
        Transaction tr = null;
        try {
            s = sessionFactory.openSession();
            tr = s.beginTransaction();
            s.merge(o);
            s.flush();
            tr.commit();
        } catch (RuntimeException e) {
            if (tr != null) {
                tr.rollback();
            }
            throw e;
        } finally {
            s.close();
        }
    }

    public void delete(Object o) {
        Transaction tr = null;

        if (s.isOpen()) {
            if (s.getTransaction().getStatus() == TransactionStatus.ACTIVE) {
                s.getTransaction().commit();
            }
            s.close();
        }

        s = sessionFactory.openSession();
        tr = s.beginTransaction();

        s.delete(o);

        tr.commit();
    }

}
