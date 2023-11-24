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

}
/*
 * @name: AddressBookMain
 * @desc: Main class to run the program.
 */
public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program!");
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);
        Contact newContact = new Contact("fName", "lName", "address", "city", "state", 100000, 1234567890, "mail@mail.com");
    }
}
