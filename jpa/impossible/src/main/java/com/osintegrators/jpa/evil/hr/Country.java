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
public class Country {
    private String name;

    public Country() {
        super();
    }

    public Country(String s) {
        super();
        name = s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;

        Country country = (Country) o;

        if (name != null ? !name.equals(country.name) : country.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
