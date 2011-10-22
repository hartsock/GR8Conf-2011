package com.example.jpa

import javax.persistence.OneToMany
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Column
import javax.persistence.Version
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Entity

/**
 * @author shartsock
 */
@Entity
class Department {
    @Id
    @GeneratedValue
    Long id
    @Version
    Long version

    @Column(unique=true)
    String name

    @OneToOne
    @JoinColumn(name="manager_id")
    Person manager

    @OneToMany
    Set<Person> staff

    String toString() { name }

}
