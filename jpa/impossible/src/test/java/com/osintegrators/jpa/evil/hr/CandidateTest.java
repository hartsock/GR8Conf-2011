package com.osintegrators.jpa.evil.hr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is a training project. This is deliberately bad
 * data design. Do not use this project as the basis for
 * production code.
 *
 * @author shartsock
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
@TransactionConfiguration
@Transactional
public class CandidateTest {
    @PersistenceContext(name = "testPersistenceUnit")
    EntityManager entityManager;

    @Test
    public void testCandidateToApplication() throws Exception {
        Name name = new Name();
        name.setFirstName("Someone");
        name.setLastName("Unique");
        Application application = new Application();
        application.setName(name);
        application.setResidence(new Country("Uruguay"));
        application.setCitizenship("Uruguay");
        entityManager.persist(application);

        // in our program applications will exist long before candidates.

        Candidate candidate = new Candidate();
        candidate.setApplication(application);
        entityManager.persist(candidate);
        Candidate fetched = entityManager.find(Candidate.class,candidate.getId());
        assert fetched.getId().equals(candidate.getId());
        assert candidate.getApplication().getName().equals(fetched.getApplication().getName());
    }
}
