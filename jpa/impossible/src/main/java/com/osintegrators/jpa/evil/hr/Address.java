package com.osintegrators.jpa.evil.hr;

import javax.persistence.Embeddable;

/**
 * This is a training project. This is deliberately bad
 * data design. Do not use this project as the basis for
 * production code.
 *
 * @author shartsock
 */
@Embeddable
public class Address {
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    public Address() {
        super();
    }

    public Address(String line1, String line2, String city, String state, String zip, String country) {
        super();
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.postalCode = zip;
        this.country = country;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
