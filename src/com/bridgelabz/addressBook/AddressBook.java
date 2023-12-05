package com.bridgelabz.addressBook;

import java.util.ArrayList;
import java.util.List;

/*
 * @name: AddressBook
 * @desc: class to store contacts
 */
class AddressBook {
    private List<Contact> contacts;

    /*
     @name: com.bridgelabz.addressBook.AddressBook
     @desc: Constructor to initialize contacts
     */
    public AddressBook() {
        contacts = new ArrayList<>();
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
}
