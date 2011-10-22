package com.example.jpa;

import javax.persistence.*;

/**
 * A simple example JPA class
 * <br/>
 * Here we are creating just a plain JPA class of the kind
 * we could have been using back in Grails 0.6
 */

@Entity
public class Address {
    private Long id;
    private Long version;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String zip;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Column
    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    @Column
    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    @Column
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column
    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String toString() {
        return line1 + "\n" + line2 + "\n" + city + ", " + state + " " + zip;
    }
}
