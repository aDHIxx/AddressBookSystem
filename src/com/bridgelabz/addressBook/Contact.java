package com.bridgelabz.addressBook;

import java.util.Objects;

/*
 * @name: Contact
 * @desc: class to store contact details
 */
class Contact implements Comparable<Contact> {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private int zip;
    private long phoneNumber;
    private String email;

    /*
     @name: com.bridgelabz.addressBook.Contact
     @desc: Constructor to initialize contact details
     @param: firstName, lastName, address, city, state, zip, phoneNumber, email
     */
    public Contact(String firstName, String lastName, String address, String city, String state, int zip, long phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /*
    @name: editContact
    @desc: to edit contact details
    @param: newContact
    @return: void
     */
    public void editContact(Contact newContact) {
        this.firstName = newContact.getFirstName();
        this.lastName = newContact.getLastName();
        this.address = newContact.getAddress();
        this.city = newContact.getCity();
        this.state = newContact.getState();
        this.zip = newContact.getZip();
        this.phoneNumber = newContact.getPhoneNumber();
        this.email = newContact.getEmail();
    }
    //getter methods
    public String getEmail() {
        return email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public int getZip() {
        return zip;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    /*
    @name: equals
    @desc: to check if two contacts are equal
    @param: obj
    @return: boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact contact = (Contact) obj;
        return Objects.equals(firstName, contact.firstName) &&
                Objects.equals(lastName, contact.lastName);
    }
    @Override
    public int compareTo(Contact other) {
        String thisName = this.getFirstName() + " " + this.getLastName();
        String otherName = other.getFirstName() + " " + other.getLastName();
        return thisName.compareToIgnoreCase(otherName);
    }
}

