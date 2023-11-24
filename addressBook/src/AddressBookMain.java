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
}
/*
 * @name: AddressBookMain
 * @desc: Main class to run the program.
 */
public class AddressBookMain {

    private static final int ADD_CONTACT = 1;
    private static final int DISPLAY_CONTACT = 2;
    private static final int EXIT = 2;

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program!");

        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Select an option: \t");
            System.out.print("1. Add Contact \t");
            System.out.println("2. Exit");

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
                case EXIT:
                    System.out.println("Exiting !");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }


}