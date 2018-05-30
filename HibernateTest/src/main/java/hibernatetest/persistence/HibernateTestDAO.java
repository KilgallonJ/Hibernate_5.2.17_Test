/**
 * (c) Midland Software Limited 2018
 * Name     : HibernateTestDAO.java
 * Author   : kilgallonj
 * Date     : 23 May 2018
 */
package hibernatetest.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class HibernateTestDAO {

    @PersistenceContext(unitName = "hibernate-test")
    private EntityManager entityManager;

    public void delete() {
        entityManager.remove(null);
    }

    public void delete(final HibernateTest entity) {
        entityManager.remove(entity);
    }
}
