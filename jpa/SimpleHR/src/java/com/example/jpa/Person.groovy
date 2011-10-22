package com.example.jpa;

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.GeneratedValue
import javax.persistence.Version
import javax.persistence.Column
import javax.persistence.OneToOne
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.CascadeType;

/**
 *
 * @author shartsock
 */
@Entity
public class Person {
    @Id
    @GeneratedValue
    Long id
    @Version
    Long version
    @Column
    String name
    @OneToOne
    @JoinColumn(name="address_id")
    Address address

    @ManyToMany(CascadeType=CascadeType.PERSIST)
    Collection<Project> projects

    String toString() { name }
}
