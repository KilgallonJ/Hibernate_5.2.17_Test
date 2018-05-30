/**
 * (c) Midland Software Limited 2018
 * Name     : EntityManagerRule.java
 * Author   : kilgallonj
 * Date     : 23 May 2018
 */
package hibernatetest.persistence;

import java.lang.reflect.Field;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.rules.ExternalResource;

public class EntityManagerRule extends ExternalResource {

    private EntityManagerFactory emFactory;

    private EntityManager em;

    @Override
    protected void before() {
        emFactory = Persistence.createEntityManagerFactory("hibernate-test");
        em = emFactory.createEntityManager();
    }

    @Override
    protected void after() {
        if (em != null) {
            em.close();
        }
        if (emFactory != null) {
            emFactory.close();
        }
    }

    public HibernateTestDAO initDAO() {
        final HibernateTestDAO dao = new HibernateTestDAO();

        try {
            injectEntityManager(dao);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dao;
    }

    public EntityManager getEntityManager() {
        return em;
    }

    public void persist(final Object entity) {
        final EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        try {
            em.persist(entity);
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        transaction.commit();
    }

    private void injectEntityManager(final HibernateTestDAO dao) throws Exception {
        final Field emField = dao.getClass().getDeclaredField("entityManager");
        emField.setAccessible(true);
        emField.set(dao, em);
    }
}
