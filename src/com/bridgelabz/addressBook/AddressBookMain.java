package com.bridgelabz.addressBook;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/*
 * @name: AddressBookMain
 * @desc: class to store address books
 */
public class AddressBookMain {
    static Scanner scanner = new Scanner(System.in);
    private static final int ADD_ADDRESS_BOOK = 1;
    private static final int SELECT_ADDRESS_BOOK = 2;
    private static final int ADD_CONTACT = 3;
    private static final int EDIT_CONTACT = 4;
    private static final int DELETE_CONTACT = 5;
    private static final int DISPLAY_CONTACTS = 6;
    private static final int PRINT_ADDRESS_BOOKS = 7;
    private static final int SEARCH_PERSON_CITY_STATE = 8;
    private static final int VIEW_PERSONS_CITY_STATE = 9;
    private static final int GET_COUNT_BY_CITY_STATE = 10;
    private static final int SORT_BY_NAME = 11;
    private static final int SORT_MENU = 12;
    private static final int READ_FROM_FILE = 13;
    private static final int WRITE_TO_FILE = 14;
    private static final int EXIT = 15;


    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program!");
        //creating a hashmap to store address books
        //key: address book name, value: address book
        Map<String, AddressBook> addressBooks = new HashMap<>();
        AddressBook currentAddressBook = null;

        while (true) {
            System.out.print("Select an option: \n");
            System.out.print("1. Add Address Book \t");
            System.out.print("2. Select Address Book \t");
            System.out.print("3. Add contact \t");
            System.out.print("4. Edit contact \t");
            System.out.print("5. Delete contact \t");
            System.out.print("6. Display contacts \n");
            System.out.print("7. Display Address Books \t");
            System.out.print("8. Search Person by City or State \t");
            System.out.print("9. View Persons by City or State \t");
            System.out.print("10. Get Count by City or State \n");
            System.out.print("11. Sort by Name and Display\t");
            System.out.print("12. Sort Menu \t");
            System.out.print("13. Read from File \t");
            System.out.print("14. Write to File \t");
            System.out.println("15. Exit");

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
                    assert currentAddressBook != null;
                    displayContacts(currentAddressBook.getContacts());
                    break;
                case PRINT_ADDRESS_BOOKS:
                    printAddressBooks(addressBooks);
                    break;
                case SEARCH_PERSON_CITY_STATE:
                    searchAndDisplayPersonByCityOrState(addressBooks, scanner);
                    break;
                case VIEW_PERSONS_CITY_STATE:
                    handleViewPersonsByCityOrState(addressBooks, scanner);
                    break;
                case GET_COUNT_BY_CITY_STATE:
                    handleGetCountByCityOrState(addressBooks, scanner);
                    break;
                case SORT_BY_NAME:
                    assert currentAddressBook != null;
                    sortContactsByName(currentAddressBook.getContacts());
                    break;
                case SORT_MENU:
                    handleSortMenu(currentAddressBook);
                    break;
                case READ_FROM_FILE:
                    assert currentAddressBook != null;
                    readFromFile(currentAddressBook);
                    break;
                case WRITE_TO_FILE:
                    assert currentAddressBook != null;
                    writeToFile(currentAddressBook);
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
            AddressBook newAddressBook = new AddressBook(addressBookName); // Pass the name
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

    /*
     * @name: searchAndDisplayPersonByCityOrState
     * @desc: search for a person in a city or state across multiple address books
     *        and display the search result
     * @param: addressBooks, scanner
     * @return: void
     */
    private static void searchAndDisplayPersonByCityOrState(Map<String, AddressBook> addressBooks, Scanner scanner) {
        System.out.print("Enter City or State to search: ");
        String cityOrState = scanner.next();

        List<Contact> searchResult = addressBooks.values().stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .filter(contact -> contact.getCity().equalsIgnoreCase(cityOrState) || contact.getState().equalsIgnoreCase(cityOrState))
                .collect(Collectors.toList());

        displayContacts(searchResult);
    }

    /*
     * @name: viewPersonsByCityOrState
     * @desc: view persons by city or state using dictionaries
     * @param: addressBooks, scanner, byState (true if viewing by state, false if viewing by city)
     * @return: void
     */
    private static void viewPersonsByCityOrState(Map<String, AddressBook> addressBooks, String cityOrState, boolean byState) {
        System.out.print("Enter " + (byState ? "State" : "City") + " to view persons: ");

        List<Contact> persons = new ArrayList<>();

        for (AddressBook addressBook : addressBooks.values()) {
            if (byState && addressBook.getStateDictionary().containsKey(cityOrState)) {
                persons.addAll(addressBook.getStateDictionary().get(cityOrState));
            } else if (!byState && addressBook.getCityDictionary().containsKey(cityOrState)) {
                persons.addAll(addressBook.getCityDictionary().get(cityOrState));
            }
        }

        if (!persons.isEmpty()) {
            displayContacts(persons);
        } else {
            System.out.println("No persons found in the specified " + (byState ? "state" : "city") + ".");
        }
    }


    private static void handleViewPersonsByCityOrState(Map<String, AddressBook> addressBooks, Scanner scanner) {
        System.out.print("Choose by State (1) or City (2): ");
        int choice = scanner.nextInt();
        System.out.print("Enter City or State to get count: ");
        String cityOrState = scanner.next();
        boolean byState = (choice == 1);
        viewPersonsByCityOrState(addressBooks, cityOrState, byState);
    }

    /*
     * @name: getCountByCityOrState
     * @desc: get the count of contact persons by city or state across multiple address books
     * @param: addressBooks, cityOrState, byState (true if counting by state, false if counting by city)
     * @return: int - count of contact persons
     */
    private static int getCountByCityOrState(Map<String, AddressBook> addressBooks, String cityOrState, boolean byState) {
        return (int) addressBooks.values().stream()
                .flatMap(addressBook -> addressBook.getContacts().stream())
                .filter(contact -> (byState ? contact.getState() : contact.getCity()).equalsIgnoreCase(cityOrState))
                .count();
    }

    /*
     * @name: handleGetCountByCityOrState
     * @desc: handle the get count operation by city or state
     * @param: addressBooks, scanner
     * @return: void
     */
    private static void handleGetCountByCityOrState(Map<String, AddressBook> addressBooks, Scanner scanner) {
        System.out.print("Choose by State (1) or City (2): ");
        int choice = scanner.nextInt();
        System.out.print("Enter City or State to get count: ");
        String cityOrState = scanner.next();
        boolean byState = (choice == 1);
        int count = getCountByCityOrState(addressBooks, cityOrState, byState);

        System.out.println("Count of contact persons by " + (byState ? "State" : "City") + " '" + cityOrState + "': " + count);
    }

    /*
     * @name: sortContactsByName
     * @desc: sort contacts alphabetically by person's name
     * @param: contacts
     * @return: void
     */
    private static void sortContactsByName(List<Contact> contacts) {
        contacts.sort(Contact::compareTo);
        System.out.println("Contacts sorted alphabetically by name:");
        displayContacts(contacts);
    }

    /*
     * @name: handleSortMenu
     * @desc: handle the sorting sub-menu
     * @param: currentAddressBook
     * @return: void
     */
    private static void handleSortMenu(AddressBook currentAddressBook) {
        final int SORT_BY_CITY = 1;
        final int SORT_BY_STATE = 2;
        final int SORT_BY_ZIP = 3;
        System.out.println("Sorting Options:");
        System.out.println("1. Sort by City");
        System.out.println("2. Sort by State");
        System.out.println("3. Sort by ZIP");
        System.out.print("Enter your choice: ");
        int sortChoice = scanner.nextInt();

        switch (sortChoice) {
            case SORT_BY_CITY:
                assert currentAddressBook != null;
                sortContactsByCity(currentAddressBook.getContacts());
                break;

            case SORT_BY_STATE:
                assert currentAddressBook != null;
                sortContactsByState(currentAddressBook.getContacts());
                break;

            case SORT_BY_ZIP:
                assert currentAddressBook != null;
                sortContactsByZip(currentAddressBook.getContacts());
                break;

            default:
                System.out.println("Invalid sorting option.");
        }
    }

    /*
     * @name: sortContactsByCity
     * @desc: sort contacts alphabetically by city
     * @param: contacts
     * @return: void
     */
    private static void sortContactsByCity(List<Contact> contacts) {
        contacts.sort(Comparator.comparing(Contact::getCity));
        System.out.println("Contacts sorted alphabetically by city:");
        displayContacts(contacts);
    }

    /*
     * @name: sortContactsByState
     * @desc: sort contacts alphabetically by state
     * @param: contacts
     * @return: void
     */
    private static void sortContactsByState(List<Contact> contacts) {
        contacts.sort(Comparator.comparing(Contact::getState));
        System.out.println("Contacts sorted alphabetically by state:");
        displayContacts(contacts);
    }

    /*
     * @name: sortContactsByZip
     * @desc: sort contacts numerically by ZIP
     * @param: contacts
     * @return: void
     */
    private static void sortContactsByZip(List<Contact> contacts) {
        contacts.sort(Comparator.comparingInt(Contact::getZip));
        System.out.println("Contacts sorted numerically by ZIP:");
        displayContacts(contacts);
    }

    /*
     * @name: readFromFile
     * @desc: read persons' contacts from a text file
     * @param: currentAddressBook
     * @return: void
     */
    private static void readFromFile(AddressBook currentAddressBook) {
        final String FILE_PATH_BASE = "C:\\Users\\Lenovo\\Desktop\\Bridgelabz-GE\\AddressBook\\files\\";
        System.out.print("Enter the file to read from: ");
        String filePath = scanner.next();
        filePath = FILE_PATH_BASE + filePath + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] contactDetails = line.split(",");
                Contact contact = new Contact(contactDetails[0], contactDetails[1], contactDetails[2],
                        contactDetails[3], contactDetails[4], Integer.parseInt(contactDetails[5]),
                        Long.parseLong(contactDetails[6]), contactDetails[7]);

                currentAddressBook.addContact(contact);
            }
            System.out.println("Contacts read from file successfully!");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    /*
     * @name: writeToFile
     * @desc: write persons' contacts to a text file
     * @param: currentAddressBook
     * @return: void
     */
    private static void writeToFile(AddressBook currentAddressBook) {
        final String FILE_PATH_BASE = "C:\\Users\\Lenovo\\Desktop\\Bridgelabz-GE\\AddressBook\\files\\";
        if (currentAddressBook != null) {
            String addressBookName = currentAddressBook.getName();
            String filePath = FILE_PATH_BASE + addressBookName + ".txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                List<Contact> contacts = currentAddressBook.getContacts();
                for (Contact contact : contacts) {
                    String line = contact.getFirstName() + "," + contact.getLastName() + "," +
                            contact.getAddress() + "," + contact.getCity() + "," + contact.getState() + "," +
                            contact.getZip() + "," + contact.getPhoneNumber() + "," + contact.getEmail();
                    writer.write(line);
                    writer.newLine();
                }
                System.out.println("Contacts written to file successfully!");
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }

    }
}
