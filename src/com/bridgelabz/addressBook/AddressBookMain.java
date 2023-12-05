package com.bridgelabz.addressBook;

import java.util.*;

public class AddressBookMain {
    private static final int ADD_ADDRESS_BOOK = 1;
    private static final int SELECT_ADDRESS_BOOK = 2;
    private static final int ADD_CONTACT = 3;
    private static final int EDIT_CONTACT = 4;
    private static final int DELETE_CONTACT = 5;
    private static final int DISPLAY_CONTACTS = 6;
    private static final int PRINT_ADDRESS_BOOKS = 7;
    private static final int EXIT = 8;


    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program!");
        //creating a hashmap to store address books
        //key: address book name, value: address book
        Map<String, AddressBook> addressBooks = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        AddressBook currentAddressBook = null;

        while (true) {
            System.out.print("Select an option: \t");
            System.out.print("1. Add Address Book \t");
            System.out.print("2. Select Address Book \t");
            System.out.print("3. Add contact \t");
            System.out.print("4. Edit contact \t");
            System.out.print("5. Delete contact \t");
            System.out.print("6. Display contacts \t");
            System.out.print("7. Display Address Books \t");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case ADD_ADDRESS_BOOK:
                    addAddressBook(addressBooks, scanner);
                    break;
                case SELECT_ADDRESS_BOOK:
                    printAvailableAddressBooks(addressBooks);
                    currentAddressBook = selectAddressBook(addressBooks, scanner);
                    break;
                case ADD_CONTACT:
                    if (currentAddressBook != null) {
                        addContact(currentAddressBook, scanner);
                    } else {
                        System.out.println("Please select an address book first.");
                    }
                    break;
                case EDIT_CONTACT:
                    assert currentAddressBook != null;
                    editContact(currentAddressBook, scanner);
                    break;
                case DELETE_CONTACT:
                    assert currentAddressBook != null;
                    deleteContact(currentAddressBook, scanner);
                    break;
                case DISPLAY_CONTACTS:
                    displayContacts(currentAddressBook.getContacts());
                    break;
                case PRINT_ADDRESS_BOOKS:
                    printAddressBooks(addressBooks);
                    break;
                case EXIT:
                    System.out.println("Exiting !");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
    /*
     @name: printAvailableAddressBooks
     @desc: print available address books
     @param: addressBooks
     @return: void
     */
    private static void printAddressBooks(Map<String, AddressBook> addressBooks) {
        System.out.println("All Address Books and Contacts:");
        for (Map.Entry<String, AddressBook> entry : addressBooks.entrySet()) {
            String addressBookName = entry.getKey();
            AddressBook addressBook = entry.getValue();

            System.out.println("Address Book: " + addressBookName);
            displayContacts(addressBook.getContacts());
            System.out.println("=================================");
        }
    }
    /*
     @name: printAvailableAddressBooks
     @desc: print available address books
     @param: addressBooks
     @return: void
     */
    private static void printAvailableAddressBooks(Map<String, AddressBook> addressBooks) {
        System.out.println("Available Address Books:");
        for (String addressBookName : addressBooks.keySet()) {
            System.out.println(addressBookName);
        }
    }
    /*
     @name: addAddressBook
     @desc: add address book
     @param: addressBooks, scanner
     @return: void
     */
    private static void addAddressBook(Map<String, AddressBook> addressBooks, Scanner scanner) {
        System.out.print("Enter the name of the new Address Book: ");
        String addressBookName = scanner.next();

        if (!addressBooks.containsKey(addressBookName)) {
            AddressBook newAddressBook = new AddressBook();
            addressBooks.put(addressBookName, newAddressBook);
            System.out.println("Address Book '" + addressBookName + "' added successfully!");
        } else {
            System.out.println("Address Book with the same name already exists.");
        }
    }
    /*
     @name: selectAddressBook
     @desc: select address book
     @param: addressBooks, scanner
     @return: selectedAddressBook
     */
    private static AddressBook selectAddressBook(Map<String, AddressBook> addressBooks, Scanner scanner) {
        System.out.print("Enter the name of the Address Book: ");
        String addressBookName = scanner.next();

        AddressBook selectedAddressBook = addressBooks.get(addressBookName);

        if (selectedAddressBook != null) {
            System.out.println("Address Book '" + addressBookName + "' selected.");
        } else {
            System.out.println("Address Book not found.");
        }

        return selectedAddressBook;
    }
    /*
     @name: addContact
     @desc: add contact
     @param: addressBook, scanner
     @return: void
     */
    private static void addContact(AddressBook addressBook, Scanner scanner) {
        while (true) {
            System.out.println("Enter contact details:");
            System.out.print("First Name: ");
            String firstName = scanner.next();
            System.out.print("Last Name: ");
            String lastName = scanner.next();
            System.out.print("Address: ");
            String address = scanner.next();
            System.out.print("City: ");
            String city = scanner.next();
            System.out.print("State: ");
            String state = scanner.next();
            System.out.print("ZIP: ");
            int zip = scanner.nextInt();
            System.out.print("Phone Number: ");
            long phoneNumber = scanner.nextLong();
            System.out.print("Email: ");
            String email = scanner.next();

            Contact newContact = new Contact(firstName, lastName, address, city, state, zip, phoneNumber, email);
            addressBook.addContact(newContact);

            System.out.println("Contact added successfully!");

            System.out.print("Do you want to add another contact? (1 for yes, 0 for no): ");
            int addAnother = scanner.nextInt();

            if (addAnother != 1) {
                break;
            }
        }
    }
    /*
     @name: editContact
     @desc: edit contact
     @param: addressBook, scanner
     @return: void
     */
    private static void editContact(AddressBook addressBook, Scanner scanner) {
        System.out.println("Enter contact details to edit:");
        System.out.print("First Name: ");
        String editFirstName = scanner.next();
        System.out.print("Last Name: ");
        String editLastName = scanner.next();

        Contact existingContact = getContactByName(addressBook.getContacts(), editFirstName, editLastName);

        if (existingContact != null) {
            System.out.println("Enter updated contact details:");
            System.out.print("First Name: ");
            String editedFirstName = scanner.next();
            System.out.print("Last Name: ");
            String editedLastName = scanner.next();
            System.out.print("Address: ");
            String editedAddress = scanner.next();
            System.out.print("City: ");
            String editedCity = scanner.next();
            System.out.print("State: ");
            String editedState = scanner.next();
            System.out.print("ZIP: ");
            int editedZip = scanner.nextInt();
            System.out.print("Phone Number: ");
            long editedPhoneNumber = scanner.nextLong();
            System.out.print("Email: ");
            String editedEmail = scanner.next();

            Contact editedContact = new Contact(editedFirstName, editedLastName, editedAddress, editedCity, editedState, editedZip, editedPhoneNumber, editedEmail);

            existingContact.editContact(editedContact);

            System.out.println("Contact edited successfully!");
        } else {
            System.out.println("Contact not found.");
        }
    }
    /*
     @name: deleteContact
     @desc: delete contact
     @param: addressBook, scanner
     @return: void
     */
    private static void deleteContact(AddressBook addressBook, Scanner scanner) {
        System.out.println("Enter contact details to delete:");
        System.out.print("First Name: ");
        String deleteFirstName = scanner.next();
        System.out.print("Last Name: ");
        String deleteLastName = scanner.next();

        Contact contactToDelete = getContactByName(addressBook.getContacts(), deleteFirstName, deleteLastName);

        if (contactToDelete != null) {
            addressBook.deleteContact(contactToDelete);
        } else {
            System.out.println("Contact not found.");
        }
    }
    /*
     @name: displayContacts
     @desc: display contacts
     @param: contacts
     @return: void
     */
    private static void displayContacts(List<Contact> contacts) {
        int i = 0;
        for (Contact contact : contacts) {
            System.out.println("Contact " + (++i) + "\n-----------\nName: " + contact.getFirstName() + " " + contact.getLastName() +
                    "\nAddress: " + contact.getAddress() +
                    "\tCity: " + contact.getCity() +
                    "\tState: " + contact.getState() +
                    "\tZIP: " + contact.getZip() +
                    "\nPhone Number: " + contact.getPhoneNumber() +
                    "\tEmail: " + contact.getEmail());
            System.out.println("----------------------------------------------------------------------");
        }
    }
    /*
    @name: getContactByName
    @desc: get contact by name
    @param: contacts, firstName, lastName
    @return: contact
     */
    private static Contact getContactByName(List<Contact> contacts, String firstName, String lastName) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equalsIgnoreCase(firstName) && contact.getLastName().equalsIgnoreCase(lastName)) {
                return contact;
            }
        }
        return null;
    }
}
