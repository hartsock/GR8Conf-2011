package com.osintegrators.jpa.evil.hr;

import javax.persistence.*;

/**
 * This is a training project. This is deliberately bad
 * data design. Do not use this project as the basis for
 * production code.
 *
 * @author shartsock
 */
@Entity
@Table(name = "candidate")
public class Candidate {
    private Long id;

    Application application;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name="application_identification_number")
    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}
