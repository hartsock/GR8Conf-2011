package com.osintegrators.jpa.evil.hr;

import javax.persistence.*;

/**
 * This is a training project. This is deliberately bad
 * data design. Do not use this project as the basis for
 * production code.
 *
 * @author shartsock
 */
@Embeddable
public class Citizenship {
    private Country primary;
    private Country secondary;

    public Citizenship(Country country) {
        primary = country;
    }

    public Citizenship() {
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="name",column = @Column(name="countryOfCitizenship"))
    })
    public Country getPrimary() {
        return primary;
    }

    public void setPrimary(Country primary) {
        this.primary = primary;
    }

    public void setPrimary(String primary) {
        setPrimary(new Country(primary));
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="name",column = @Column(name="countryOfCitizenship2"))
    })
    public Country getSecondary() {
        return secondary;
    }

    public void setSecondary(Country secondary) {
        this.secondary = secondary;
    }

    public void setSecondary(String secondary) {
        setSecondary(new Country(secondary));
    }

    public Country[] toArray() {
        return new Country[]{primary,secondary};
    }

    public Country[] toArray(Country[] a) {
        a = new Country[]{primary, secondary};
        return a;
    }

    public Country toCountry() {
        return getPrimary();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Citizenship)) return false;

        Citizenship that = (Citizenship) o;

        if (primary != null ? !primary.equals(that.primary) : that.primary != null) return false;
        if (secondary != null ? !secondary.equals(that.secondary) : that.secondary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = primary != null ? primary.hashCode() : 0;
        result = 31 * result + (secondary != null ? secondary.hashCode() : 0);
        return result;
    }
}
