import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ContactServiceTest {

    @Test
    void testAddContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("1", "Alice", "Brown", "1234567890", "1 Elm St");
        service.addContact(contact);
        assertEquals("Alice", service.getContactById("1").getFirstName());
    }

    @Test
    void testAddDuplicateContact() {
        ContactService service = new ContactService();
        Contact contact1 = new Contact("1", "Alice", "Brown", "1234567890", "1 Elm St");
        service.addContact(contact1);
        Contact contact2 = new Contact("1", "Bob", "Smith", "0987654321", "2 Oak St");

        assertThrows(IllegalArgumentException.class, () -> service.addContact(contact2));
    }

    @Test
    void testDeleteContact() {
        ContactService service = new ContactService();
        Contact contact = new Contact("2", "Charlie", "Davis", "1111111111", "3 Pine St");
        service.addContact(contact);
        service.deleteContact("2");

        assertNull(service.getContactById("2"));
    }

    @Test
    void testUpdateContactInfo() {
        ContactService service = new ContactService();
        Contact contact = new Contact("3", "Eve", "White", "2222222222", "4 Cedar Ave");
        service.addContact(contact);

        service.updateFirstName("3", "Eva");
        service.updateLastName("3", "Black");
        service.updatePhone("3", "3333333333");
        service.updateAddress("3", "5 Maple Blvd");

        assertEquals("Eva", service.getContactById("3").getFirstName());
        assertEquals("Black", service.getContactById("3").getLastName());
        assertEquals("3333333333", service.getContactById("3").getPhone());
        assertEquals("5 Maple Blvd", service.getContactById("3").getAddress());
    }
    
    @Test
    void testDeleteNonExistentContact() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("999"));
    }

    @Test
    void testUpdateNonExistentContact() {
        ContactService service = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("999", "NewName"));
    }

    @Test
    void testUpdateWithInvalidData() {
        ContactService service = new ContactService();
        Contact contact = new Contact("10", "Sam", "Lee", "4444444444", "10 Birch Rd");
        service.addContact(contact);

        // Try to set invalid phone
        assertThrows(IllegalArgumentException.class, () -> service.updatePhone("10", "123"));
    }
}