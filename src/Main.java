import java.util.Scanner;

public class Main
{
    private static MobilePhone phone = new MobilePhone();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        boolean quit = false;
        printOptions(); // Printing the menu of options
        while (!quit)
        {
            System.out.println("\nEnter option (0 to see the list of options)");
            int option = -1;
            if (scanner.hasNextInt()) // Checking if user input is an int value
                option = scanner.nextInt(); // Reading user input
            scanner.nextLine();
            switch (option) // Implementing one of the menu options depending on user input
            {
                case 0:
                    printOptions();
                    break;
                case 1:
                    phone.printContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    updateContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    findContact();
                    break;
                case 6:
                    quit = true;
                    break;
            }
        }
    }

    public static void printOptions() // Printing the menu
    {
        System.out.println("\nAvailable options:\n" +
                "0) See list of options\n" +
                "1) Print list of contacts\n" +
                "2) Add new contact\n" +
                "3) Update existing contact\n" +
                "4) Remove contact\n" +
                "5) Find contact\n" +
                "6) Quit\n");
    }

    public static void addContact() // Adding a new contact with the name and the phone number that user enters
    {
        System.out.println("Enter new contact's name");
        String name = scanner.nextLine();
        System.out.println("Enter new contact's number");
        String number = scanner.nextLine();
        Contact newContact = new Contact(name, number);
        if (phone.addContact(newContact)) // If the new contact doesn't yet exist, adding it to the list
        {
            System.out.print("New contact ");
            newContact.printContact();
            System.out.println(" was successfully added");
        }
        else // Otherwise throw an error
        {
            System.out.println("This contact already exists in the list");
        }
    }

    public static void updateContact() // Updating an existing contact
    {
        System.out.println("Enter contact's name that you want to update");
        String name = scanner.nextLine();
        Contact existingContact = phone.queryContact(name); // Searching for the contact in the list
        if (existingContact == null)
        {
            System.out.println("Contact was not found");
            return;
        }
        // If the contact was found, updating the info based on user input
        System.out.println("Enter new name");
        String newName = scanner.nextLine();
        System.out.println("Enter new phone number");
        String newNumber = scanner.nextLine();
        Contact updatedContact = new Contact(newName, newNumber); // Creating a new contact  with updated info
        if (phone.updateContact(existingContact, updatedContact))
        { // If the update was successful, printing a completion message
            existingContact.printContact();
            System.out.print(" was replaced with ");
            updatedContact.printContact();
            System.out.println();
        }
        else // If the update was not successful, throwing an error
        {
            System.out.println("Contact with this name already exists");
        }
    }

    public static void removeContact() // Removing contact from the list
    {
        System.out.println("Please enter the name of the contact you want to remove");
        String name = scanner.nextLine();
        Contact existingContact = phone.queryContact(name); // Searching for the contact in the list
        if (existingContact == null)
        {
            System.out.println("Contact was not found");
            return;
        }
        // If the contact was found, removing it from the list
        phone.removeContact(existingContact);
        System.out.println("Contact " + existingContact.getName() + " was successfully deleted");
    }

    public static void findContact() // Searching for contact by name
    {
        System.out.println("Please enter the name of the contact you want to find");
        String name = scanner.nextLine();
        Contact existingContact = phone.queryContact(name); // Searching for the contact in the list
        if (existingContact == null)
        {
            System.out.println("Contact was not found");
            return;
        }
        // If the contact was found, printing the contact's info
        System.out.println("Found contact:");
        existingContact.printContact();
        System.out.println();
    }
}
