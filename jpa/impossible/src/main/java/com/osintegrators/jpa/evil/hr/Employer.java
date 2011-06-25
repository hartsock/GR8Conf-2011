package com.osintegrators.jpa.evil.hr;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;

/**
 * @author shartsock
 */

@Entity
@NamedNativeQueries(value = {
        @NamedNativeQuery(
            name = "employmentHistoryQuery",
            query = Employer.employmentHistoryQuery,
            resultClass = Employer.class
        )
})
public class Employer {
    Long id;
    Long applicationId;
	String company;
	String title;
	Date startDate;
	Date endDate;
	String supervisor;
    Boolean okay;

    public Employer() {
        super();
    }

    public Employer(Long applicationId, String company, String title, Date startDate, Date endDate, String supervisor, Boolean okay) {
        super();
        this.applicationId = applicationId;
        this.company = company;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.supervisor = supervisor;
        this.okay = okay;
    }

    public Employer(Object[] params) {
        int ii = 0;
        this.id = ((BigInteger) params[ii++]).longValue();
        this.applicationId = ((Integer) params[ii++]).longValue();
        this.company = (String) params[ii++];
        this.startDate= (Date) params[ii++];
        this.endDate= (Date) params[ii++];
        this.title= (String) params[ii++];
        this.supervisor= (String) params[ii++];
        Object contact = params[ii++];
        if(contact != null) {
            this.okay = contact.toString().equals("true")?true:false;
        }
    }

    @Id
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column
    public Boolean getOkay() {
        return okay;
    }

    public void setOkay(Boolean okay) {
        this.okay = okay;
    }

    public void setApplicationId(Long id) {
        this.applicationId = id;
    }

    @Column
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
    @Column
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
    @Column
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
    @Column
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    @Column
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

    /**
     * NOTE: This is bad design, but so is our data table. We should either
     * choose to completely encapsulate the employer collection fetch and
     * wholly hide the fact we are using entityManager or we should
     * move the other direction and encapsulate this in a service. I chose
     * this hybrid approach here because it serves the purpose of a tutorial
     * in this technology to expose the action.
     * <p/>
     * This should be mappable using the @SqlResultSet query
     * @param em
     * @return
     */
    public static Collection<Employer> employmentHistory(EntityManager em, Long applicationId) {
        List<Employer> results = em.createNativeQuery(employmentHistoryQuery,Employer.class)
                 .setParameter("applicationId", applicationId)
                 .getResultList();
        return results;
    }
    public static final String employmentHistoryQuery = "select (id * 10) + 1 as id\n" +
                ",  id as applicationId\n" +
                ",  previousEmployer1Name as company\n" +
                ",  previousEmployer1StartDate as startDate\n" +
                ",  previousEmployer1EndDate as endDate\n" +
                ",  previousEmployer1Title as title\n" +
                ",  previousEmployer1SupervisorName as supervisor\n" +
                ",  previousEmployer1SupervisorPhone as phone\n" +
                ",  previousEmployer1SupervisorEmail as email\n" +
                ",  previousEmployer1SupervisorOkayToContact as okay\n" +
                " from application where id = :applicationId \n" +
                "\n" +
                "union\n" +
                "\n" +
                "select (id * 10) + 2 as id\n" +
                ", id as applicationId\n" +
                ",  previousEmployer2Name as company\n" +
                ",  previousEmployer2StartDate as startDate\n" +
                ",  previousEmployer2EndDate as endDate\n" +
                ",  previousEmployer2Title as title\n" +
                ",  previousEmployer2SupervisorName as supervisor\n" +
                ",  previousEmployer2SupervisorPhone as phone\n" +
                ",  previousEmployer2SupervisorEmail as email\n" +
                ",  previousEmployer2SupervisorOkayToContact as okay\n" +
                " from application where id = :applicationId \n" +
                "\n" +
                "union\n" +
                "\n" +
                "select (id * 10) + 3 as id\n" +
                ", id as applicationId\n" +
                ",  previousEmployer3Name as company\n" +
                ",  previousEmployer3StartDate as startDate\n" +
                ",  previousEmployer3EndDate as endDate\n" +
                ",  previousEmployer3Title as title\n" +
                ",  previousEmployer3SupervisorName as supervisor\n" +
                ",  previousEmployer3SupervisorPhone as phone\n" +
                ",  previousEmployer3SupervisorEmail as email\n" +
                ",  previousEmployer3SupervisorOkayToContact as okay\n" +
                " from application where id = :applicationId \n" +
                "\n" +
                "union\n" +
                "\n" +
                "select (id * 10) + 4 as id\n" +
                ", id as applicationId\n" +
                ",  previousEmployer4Name as company\n" +
                ",  previousEmployer4StartDate as startDate\n" +
                ",  previousEmployer4EndDate as endDate\n" +
                ",  previousEmployer4Title as title\n" +
                ",  previousEmployer4SupervisorName as supervisor\n" +
                ",  previousEmployer4SupervisorPhone as phone\n" +
                ",  previousEmployer4SupervisorEmail as email\n" +
                ",  previousEmployer4SupervisorOkayToContact as okay\n" +
                " from application where application.id = :applicationId \n";
}
