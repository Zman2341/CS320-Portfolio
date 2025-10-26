import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    void testValidContactCreation() {
        Contact contact = new Contact("12345", "John", "Doe", "1234567890", "123 Main Street");
        assertEquals("12345", contact.getContactId());
        assertEquals("John", contact.getFirstName());
        assertEquals("Doe", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("123 Main Street", contact.getAddress());
    }

    @Test
    void testInvalidContactId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "John", "Doe", "1234567890", "123 Main Street");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678901", "John", "Doe", "1234567890", "123 Main Street");
        });
    }

    @Test
    void testInvalidPhoneNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "Doe", "12345", "123 Main Street");
        });
    }

    @Test
    void testUpdateFields() {
        Contact contact = new Contact("123", "John", "Doe", "1234567890", "123 Main Street");
        contact.setFirstName("Jane");
        contact.setLastName("Smith");
        contact.setPhone("0987654321");
        contact.setAddress("456 Oak Ave");

        assertEquals("Jane", contact.getFirstName());
        assertEquals("Smith", contact.getLastName());
        assertEquals("0987654321", contact.getPhone());
        assertEquals("456 Oak Ave", contact.getAddress());
    }
    
    @Test
    void testInvalidFirstName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", null, "Doe", "1234567890", "123 Main Street");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "ThisNameIsTooLong", "Doe", "1234567890", "123 Main Street");
        });
    }

    @Test
    void testInvalidLastName() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", null, "1234567890", "123 Main Street");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "ThisLastNameIsTooLong", "1234567890", "123 Main Street");
        });
    }

    @Test
    void testInvalidAddress() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "Doe", "1234567890", null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "John", "Doe", "1234567890",
                "This address is too long to be valid because it exceeds thirty characters");
        });
    }

    @Test
    void testEdgeCaseValidLengths() {
        String tenChars = "ABCDEFGHIJ"; // 10 chars
        String thirtyChars = "123456789012345678901234567890"; // 30 chars
        Contact contact = new Contact("1234567890", tenChars, tenChars, "0123456789", thirtyChars);
        assertEquals(tenChars, contact.getFirstName());
        assertEquals(tenChars, contact.getLastName());
        assertEquals("0123456789", contact.getPhone());
        assertEquals(thirtyChars, contact.getAddress());
    }
}