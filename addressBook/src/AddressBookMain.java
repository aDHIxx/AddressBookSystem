import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * @name: Contact
 * @desc: Used to store contact details of a person.
 */
class Contact {
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private int zip;
    private long phoneNumber;
    private String email;

    /*
     * Constructor for Contact class
     * @param firstName
     * @param lastName
     * @param address
     * @param city
     * @param state
     * @param zip
     * @param phoneNumber
     * @param email
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
     * @name: editContact
     * @desc: Used to edit an existing contact's details
     * @param newContact - Contact with updated details
     * @return: none
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

    String getEmail() {
        return email;
    }

    long getPhoneNumber() {
        return phoneNumber;

    }

    int getZip() {
        return zip;
    }

    String getState() {
        return state;
    }

    String getCity() {
        return city;
    }

    String getAddress() {
        return address;
    }

    String getLastName() {
        return lastName;
    }

    String getFirstName() {
        return firstName;
    }
}
/*
 * @name: AddressBook
 * @desc: Used to store contacts in an address book.
 */
class AddressBook {
    private List<Contact> contacts;

    /*
     * Constructor to initialize the address book.
     */
    public AddressBook() {
        contacts = new ArrayList<>();
    }
    /*
     * @name: addContact
     * @desc: Used to add a new contact to the address book
     * @param newContact - Contact to be added
     * @return: none
     */
    public void addContact(Contact newContact) {
        contacts.add(newContact);
    }
    /*
     * @name: getContacts
     * @desc: Used to get all the contacts in the address book
     * @return: List of contacts
     */
    public List<Contact> getContacts() {
        return contacts;
    }

    /*
     * @name: editContact
     * @desc: Used to edit an existing contact in the address book
     * @param existingContact - Contact to be edited
     * @param updatedContact - Contact with updated details
     * @return: none
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
     * @name: deleteContact
     * @desc: Used to delete an existing contact in the address book
     * @param existingContact - Contact to be deleted
     * @return: none
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
/*
 * @name: AddressBookMain
 * @desc: Main class to run the program.
 */
public class AddressBookMain {

    private static final int ADD_CONTACT = 1;
    private static final int EDIT_CONTACT = 2;
    private static final int DELETE_CONTACT = 3;
    private static final int DISPLAY_CONTACTS = 4;
    private static final int EXIT = 5;

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program!");

        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Select an option: \t");
            System.out.print("1. Add Contact \t");
            System.out.print("2. Edit Contact \t");
            System.out.print("3. Delete Contact \t");
            System.out.print("4. Display Contacts \t");
            System.out.println("5. Exit");


            int choice = scanner.nextInt();

            switch (choice) {
                case ADD_CONTACT:
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
                    break;
                case EDIT_CONTACT:
                    System.out.println("Enter contact details to edit:");
                    System.out.print("First Name: ");
                    String editFirstName = scanner.next();
                    System.out.print("Last Name: ");
                    String editLastName = scanner.next();

                    Contact existingContact = getContactByName(addressBook.getContacts(), editFirstName, editLastName);

                    if (existingContact != null) {
                        System.out.println("Enter updated contact details:");
                        System.out.print("First Name: ");
                        String editedfirstName = scanner.next();
                        System.out.print("Last Name: ");
                        String editedlastName = scanner.next();
                        System.out.print("Address: ");
                        String editedaddress = scanner.next();
                        System.out.print("City: ");
                        String editedcity = scanner.next();
                        System.out.print("State: ");
                        String editedstate = scanner.next();
                        System.out.print("ZIP: ");
                        int editedzip = scanner.nextInt();
                        System.out.print("Phone Number: ");
                        long editedphoneNumber = scanner.nextLong();
                        System.out.print("Email: ");
                        String editedemail = scanner.next();

                        Contact editedContact = new Contact(editedfirstName, editedlastName, editedaddress, editedcity, editedstate, editedzip, editedphoneNumber, editedemail);

                        existingContact.editContact(editedContact);

                        System.out.println("Contact edited successfully!");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case DELETE_CONTACT:
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
                    break;
                case DISPLAY_CONTACTS:
                    System.out.println("Address Book");
                    System.out.println("============");
                    displayContacts(addressBook.getContacts());
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

    private static void displayContacts(List<Contact> contacts) {
        int i=0;
        for (Contact contact : contacts) { //had to make it package private
            System.out.println("Contact "+(i++)+"\n-----------\nName: " + contact.getFirstName() + " " + contact.getLastName()+
                    "\nAddress: " + contact.getAddress() +
                    "\tCity: " + contact.getCity() +
                    "\tState: " + contact.getState() +
                    "\tZIP: " + contact.getZip() +
                    "\nPhone Number: " + contact.getPhoneNumber() +
                    "\tEmail: " + contact.getEmail());
            System.out.println("----------------------------------------------------------------------");

        }
    }

    private static Contact getContactByName(List<Contact> contacts, String firstName, String lastName) {
        for (Contact contact : contacts) {
            if (contact.getFirstName().equalsIgnoreCase(firstName) && contact.getLastName().equalsIgnoreCase(lastName)) {
                return contact;
            }
        }
        return null;
    }
}