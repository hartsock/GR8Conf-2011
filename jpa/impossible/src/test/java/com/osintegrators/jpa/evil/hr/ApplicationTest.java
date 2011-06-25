package com.osintegrators.jpa.evil.hr;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@TransactionConfiguration
@Transactional
public class ApplicationTest {
    @PersistenceContext(name = "testPersistenceUnit")
    EntityManager entityManager;

    @Resource
    BasicDataSource dataSource;

    public void setUp() {

    }

    @Test
    public void testApplicationPersist() {
        Application application = new Application();
        long initialCount = countApplications();
        entityManager.persist(application);
        long secondCount = countApplications();
        assert 0 < application.getId();
        assert initialCount < secondCount;
        entityManager.remove(application);
        long thirdCount = countApplications();
        assert initialCount == thirdCount;
    }

    private long countApplications() {
        return ((Number) entityManager.createQuery(
                "select count(a.id) from com.osintegrators.jpa.evil.hr.Application as a"
        ).getSingleResult()).longValue();
    }

    @Test
    public void testApplicationFillout() {
        Application application = new Application();

        Date date = new Date();
        application.setStartedDate(date);
        application.setEditedDate(date);

        long initialCount = countApplications();
        entityManager.persist(application);
        long secondCount = countApplications();
        assert( initialCount < secondCount );

        assert 0 < application.getId();
        Long id = application.getId();
        assert application.getStartedDate().equals(date);
        assert application.getEditedDate().equals(date);

        Application fetched = entityManager.find(Application.class,id);
        assert confirm(application,fetched);
    }

    @Test
    public void testNameInApplication() {
        Name name = new Name();
        name.setFirstName("John");
        name.setLastName("Smith");
        Application application = new Application();
        application.setName(name);
        entityManager.persist(application);
        Application fetched = entityManager.find(Application.class,application.getId());
        assert confirm(application,fetched);
        assert fetched.getName().getFirstName().equals("John");
        assert fetched.getName().getLastName().equals("Smith");
    }

    @Test
    public void testCountryOfResidence() {
        Name name = new Name();
        name.setFirstName("John");
        name.setLastName("Smith");
        Application application = new Application();
        application.setName(name);
        application.setResidence(new Country("Uruguay"));
        entityManager.persist(application);
        Application fetched = entityManager.find(Application.class,application.getId());
        assert confirm(application,fetched);
        assert application.getResidence().equals(fetched.getResidence());
    }

    @Test
    public void testCitizenship() {
        Name name = new Name();
        name.setFirstName("John");
        name.setLastName("Smith");
        Application application = new Application();
        application.setName(name);
        application.setResidence(new Country("Uruguay"));
        application.setCitizenship("Uruguay");
        entityManager.persist(application);
        Application fetched = entityManager.find(Application.class,application.getId());
        assert confirm(application,fetched);
        assert application.getResidence().equals(fetched.getResidence());
        assert application.getCitizenship().equals(fetched.getCitizenship());
    }

    @Test
    public void testAddresses() {
        Name name = new Name();
        name.setFirstName("John");
        name.setLastName("Smith");
        Application application = new Application();
        application.setName(name);
        Address address = new Address("123 first street", null, "Springfield", "IL", " 62703", "US");
        application.setAddress(address);
        Address mailing = new Address("PO BOX 123", null, "Springfield", "IL", " 62703", "US");
        application.setMailingAddress(mailing);
        entityManager.persist(application);
        Application fetched = entityManager.find(Application.class,application.getId());
        assert confirm(application,fetched);
        assert application.getAddress().equals(fetched.getAddress());
        assert application.getMailingAddress().equals(fetched.getMailingAddress());
    }

    @Test
    public void testEmploymentHistory() throws SQLException, ParseException {
        Application application = new Application();
        entityManager.persist(application);
        PreparedStatement statement = dataSource.getConnection().prepareStatement("update application set " +
             /* 1 */   "previousEmployer1Name = ? " +
             /* 2 */   ",previousEmployer1StartDate = ? " +
             /* 3 */   ",previousEmployer1EndDate = ? " +
             /* 4 */   ",previousEmployer1Title= ? " +
             /* 5 */   ",previousEmployer1SupervisorName= ? " +
             /* 6 */   ",previousEmployer1SupervisorPhone= ? " +
             /* 7 */   ",previousEmployer1SupervisorEmail= ? " +
             /* 8 */   ",previousEmployer1SupervisorOkayToContact= ? " +
             /*+1 */   "where id = ? "
        );
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        statement.setString(1,"Joe's Crab Shack");
        statement.setDate(2, new java.sql.Date( formatter.parse("2009-10-01").getTime() ));
        statement.setDate(3, new java.sql.Date( formatter.parse("2011-01-10").getTime() ));
        statement.setString(4, "Dish Washer");
        statement.setString(5, "Joe");
        statement.setString(6,"555.1234");
        statement.setString(7,"joe@example.com");
        statement.setBoolean(8,false);
        statement.setLong(9,application.getId());
        statement.executeUpdate();
        Collection<Employer> history = Employer.employmentHistory(entityManager,application.getId());
        assert history.size() > 0;
        Iterator<Employer> iter = history.iterator();
        assert iter.hasNext();
        Employer employer;
        employer = iter.next();
        assert employer != null;
        assert employer.getCompany().equals("Joe's Crab Shack");
    }

    boolean confirm(Application a, Application b) {
        assert a.getId() == b.getId();
        assert a.getStartedDate().equals(b.getStartedDate());
        assert a.getEditedDate().equals(b.getEditedDate());
        assert a.getName().equals(b.getName());
        assert a.getCitizenship().equals(b.getCitizenship());
        assert a.getResidence().equals(b.getResidence());
        return true;
    }
}
