package hibernatetest.persistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class HibernateTestDAO {

    @PersistenceContext(unitName = "hibernate-test")
    private EntityManager entityManager;

    public void delete(final HibernateTest entity) {
        entityManager.remove(entity);
    }
}
