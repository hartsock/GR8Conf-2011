package com.example.jpa.util

import org.codehaus.groovy.grails.commons.ApplicationHolder

/**
 * functionally non-cohesive tools for working with data
 */
class AddressUtil {

    def populate() {
        populateAddresses()
        populatePeople()
        populateDepartments()
    }

    def populateDepartments() {
        def file = ApplicationHolder.application.parentContext.getResource("classpath:bootstrap/departments.txt").getFile()
        file.text.split(/\n|\r/).each { String line ->
            def cols = ['name', 'department']
            def vals = line.split(/,/).toList()

            def params = [:]
            2.times {
                params[cols[it]] = vals[it].trim()
            }

            def person = Person.findByName(params.name)
            assert person != null
            def department = Department.findByName(params.department)
            assert department != null

            department.manager = person
            assert department.save()
        }
    }

    def populatePeople() {
        def file = ApplicationHolder.application.parentContext.getResource("classpath:bootstrap/people.txt").getFile()
        file.text.split(/\n|\r/).each { String line ->
            def cols = ['name', 'city', 'department']
            def vals = line.split(/,/).toList()

            def params = [:]
            3.times {
                params[cols[it]] = vals[it].trim()
            }
            def address = Address.findByCity(params.city)
            assert address != null
            def department = Department.findByName(params.department)
            if(!department) {
                // department = new Department(name:params.department)
                assert department.save()
            }
            assert department != null
            // def person = new Person(name:params.name,address:address)
            assert person.save()
            department.addToStaff(person)
            assert department.save()
        }
    }

    /**
     * populates the database with all the addresses found in bootstrap/addresses.txt parsing each line
     * as an address line format is "$line1, $city, $state $zipcode"
     * @return
     */
    def populateAddresses() {
        def file = ApplicationHolder.application.parentContext.getResource("classpath:bootstrap/addresses.txt").getFile()
        file.text.split(/\n|\r/).each { String line ->
            def cols = ['line1', 'city', 'state', 'zip']
            def vals = line.split(/,/).toList()
            def stateZip = vals[2].trim().split(/\s/).toList()

            vals << stateZip.pop()
            vals[2] = stateZip.pop()

            def params = [:]
            4.times {
                params[cols[it]] = vals[it].trim()
            }
            // new Address(params).save()
        }
    }

}
