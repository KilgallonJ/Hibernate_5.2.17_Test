package hibernatetest.persistence;

import static org.junit.Assert.fail;

import java.util.UUID;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class HibernateTestDAOTest {

    @Rule
    public EntityManagerRule rule = new EntityManagerRule();

    private HibernateTestDAO dao;

    @Before
    public void setup() {
        dao = rule.initDAO();
    }

    @Test
    public void testDeleteNullEntity() {
        HibernateTest entity = null;
        try {
            dao.delete(entity);
        } catch (IllegalArgumentException e) {
            if (rule.getEntityManager().getTransaction().isActive()) {
                fail("Transaction is still active when it should have been rolled back.");
            }
        }
    }

    @Test
    public void testDeleteDetachedEntity() {
        HibernateTest entity = new HibernateTest(UUID.randomUUID());
        rule.persist(entity);
        rule.getEntityManager().detach(entity);
        try {
            dao.delete(entity);
        } catch (IllegalArgumentException e) {
            if (rule.getEntityManager().getTransaction().isActive()) {
                fail("Transaction is still active when it should have been rolled back.");
            }
        }
    }
}
