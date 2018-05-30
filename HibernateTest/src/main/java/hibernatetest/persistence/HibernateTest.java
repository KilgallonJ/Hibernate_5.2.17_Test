/**
 * (c) Midland Software Limited 2018
 * Name     : HibernateTest.java
 * Author   : kilgallonj
 * Date     : 23 May 2018
 */
package hibernatetest.persistence;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "hibernate_test")
public class HibernateTest {

    @Id
    @Column(name = "id")
    @Type(type = "uuid-char")
    private UUID id;

    public HibernateTest(final UUID id) {
        this.id = id;
    }
}
