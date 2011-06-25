package com.osintegrators.jpa.evil.hr;

import java.util.Date;

import javax.persistence.*;
@Entity
@Table(name = "application")
public class Application {
	private Long id;
	private Long version;
    private Date startedDate = new Date();
	private Date editedDate = new Date();
	private Name name;

    private Country residence;
    private Citizenship citizenship;
    private Address address;
    private Address mailingAddress;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

    @Version
    @Column(name = "version")
    public Long getVersion() {
        return this.version;
    }
    void setVersion(Long version) {
        this.version = version;
    }

	@Column(name="startedDate")
	public Date getStartedDate() {
		return startedDate;
	}
	public void setStartedDate(Date startedDate) {
		this.startedDate = startedDate;
	}
	
	@Column(name="editedDate")
	public Date getEditedDate() {
		return editedDate;
	}
	public void setEditedDate(Date editedDate) {
		this.editedDate = editedDate;
	}

	@Embedded
	public Name getName() {
		return name;
	}
	public void setName(Name name) {
		this.name = name;
	}

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="name",column = @Column(name="countryOfResidence"))
    })
    public Country getResidence() {
        return residence;
    }

    public void setResidence(Country residence) {
        this.residence = residence;
    }

    @Embedded
    public Citizenship getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(Citizenship citizenship) {
        this.citizenship = citizenship;
    }

    public void setCitizenship(String citizenship) {
        setCitizenship(new Citizenship(new Country(citizenship)));
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="line1",column = @Column(name="addressLine1")),
            @AttributeOverride(name="line2",column = @Column(name="addressLine2")),
            @AttributeOverride(name="city",column = @Column(name="addressCity")),
            @AttributeOverride(name="state",column = @Column(name="addressStateOrProvence")),
            @AttributeOverride(name="postalCode",column = @Column(name="addressPostalCodeOrZipCode")),
            @AttributeOverride(name="country",column = @Column(name="addressPoliticalState")),
    })
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="line1",column = @Column(name="mailingAddressLine1")),
            @AttributeOverride(name="line2",column = @Column(name="mailingAddressLine2")),
            @AttributeOverride(name="city",column = @Column(name="mailingAddressCity")),
            @AttributeOverride(name="state",column = @Column(name="mailingAddressStateOrProvence")),
            @AttributeOverride(name="postalCode",column = @Column(name="mailingAddressPostalCodeOrZipCode")),
            @AttributeOverride(name="country",column = @Column(name="mailingAddressPoliticalState")),
    })
    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }
}
