package com.bridgelabz.addressBook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @name: AddressBook
 * @desc: class to store contacts
 */
class AddressBook implements Serializable {
    private String name;

    private static final long serialVersionUID = 1L;

    private List<Contact> contacts;
    private Map<String, List<Contact>> cityDictionary;
    private Map<String, List<Contact>> stateDictionary;

    /*
     @name: com.bridgelabz.addressBook.AddressBook
     @desc: Constructor to initialize contacts
     */
    public AddressBook(String addressBookName) {
        this.name=addressBookName;
        contacts = new ArrayList<>();
        cityDictionary = new HashMap<>();
        stateDictionary = new HashMap<>();
    }

    /*
     @name: addContact
     @desc: add contact
     @param: newContact
     @return: void
     */
    public void addContact(Contact newContact) {
        boolean isDuplicate = contacts.stream()
                .anyMatch(contact -> contact.equals(newContact));

        if (!isDuplicate) {
            contacts.add(newContact);
            cityDictionary.computeIfAbsent(newContact.getCity(), k -> new ArrayList<>()).add(newContact);
            stateDictionary.computeIfAbsent(newContact.getState(), k -> new ArrayList<>()).add(newContact);
            System.out.println("Contact added successfully!");
        } else {
            System.out.println("Duplicate entry! Contact with the same name already exists.");
        }
    }

    /*
     @name: getContacts
     @desc: get contacts
     @return: contacts
     */
    public List<Contact> getContacts() {
        return contacts;
    }

    /*
     @name: editContact
     @desc: edit contact
     @param: existingContact, updatedContact
     @return: void
     */
    public void editContact(Contact existingContact, Contact updatedContact) {
        if (contacts.contains(existingContact)) {
            contacts.remove(existingContact);
            contacts.add(updatedContact);
            System.out.println("Contact edited successfully!");
        } else {
            System.out.println("Contact not found.");
        }
    }

    /*
     @name: deleteContact
     @desc: delete contact
     @param: contactToDelete
     @return: void
     */
    public void deleteContact(Contact contactToDelete) {
        if (contacts.contains(contactToDelete)) {
            contacts.remove(contactToDelete);
            System.out.println("Contact deleted successfully!");
        } else {
            System.out.println("Contact not found.");
        }
    }

    public Map<String, List<Contact>> getCityDictionary() {
        return cityDictionary;
    }

    public Map<String, List<Contact>> getStateDictionary() {
        return stateDictionary;
    }

    /*
     * @name: getName
     * @desc: get name of the address book
     * @return: name
     */
    public String getName() {
        return name;
    }
}
