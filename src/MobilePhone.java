import java.util.ArrayList;

public class MobilePhone
{
    private ArrayList<Contact> listOfContacts;

    public MobilePhone()
    {
        listOfContacts = new ArrayList<Contact>();
    }

    public void printContacts() // Printing all the existing contacts
    {
        for (int i = 0; i < listOfContacts.size(); i++)
        {
            listOfContacts.get(i).printContact();
            System.out.println();
        }
    }

    public boolean addContact(Contact newContact) // Method for adding new contacts
    {
        if (listOfContacts.contains(newContact) || (findContact(newContact.getName()) != -1))
        { // Checking if the list already has the contact user is trying to add
            return false;
        }
        listOfContacts.add(newContact);
        return true;
    }

    public boolean updateContact(Contact oldContact, Contact newContact) // Updating info of an existing contact
    {
        int foundPosition = findContact(oldContact); // Looking for contact in the list
        if (foundPosition >= 0)
        {
            if (findContact(newContact.getName()) != -1)
            { // If user is trying to update contact to a name that already exists, update can't be done
                return false;
            }
            listOfContacts.set(foundPosition, newContact); // Replacing old contact with the new one
            return true;
        }
        return false;
    }

    public void removeContact(Contact contact) // Method for deleting contacts from the list
    {
        int position = findContact(contact);
        if (position >= 0) // Removing the contact if it was found
        {
            listOfContacts.remove(position);
        }
    }

    private int findContact(String name) // Searching for contact by name, return value - contact's position
    {
        for(int i = 0; i < listOfContacts.size(); i++) // Searching for the name in the list
        {
            if (listOfContacts.get(i).getName().equals(name)) // Comparing two names
            {
                return i;
            }
        }
        return -1;
    }

    private int findContact (Contact contact) // Searching for contact, return value - contact's position
    {
        return listOfContacts.indexOf(contact);
    }

    public Contact queryContact(String name) // Public method for contact search accessible in the Main class
    {
        int position = findContact(name);
        if (position >= 0)
        {
            return listOfContacts.get(position);
        }
        return null;
    }
}
