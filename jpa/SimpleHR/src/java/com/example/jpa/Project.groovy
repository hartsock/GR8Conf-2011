package com.example.jpa

import javax.persistence.Version
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.ManyToMany
import javax.persistence.Column
import javax.persistence.CascadeType

/**
 *
 * @author shartsock
 */
class Project {
    @Id
    @GeneratedValue
    Long id
    @Version
    Long version

    @Column(unique=true)
    String name

    @Column
    Date startDate
    @Column
    Date endDate

/*
    @ManyToMany(CascadeType=CascadeType.PERSIST)
    Collection<Department> departments

*/
    @ManyToMany(cascadeType=CascadeType.PERSIST)
    Collection<Person> staff
}
