import Utils.UserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {
    
    @Test
    public void shouldCreateUser() throws UserException {
        User user = new User("Jhon Doe", "1234");
        assertEquals("Jhon Doe", user.getUserName());
        assertEquals("1234", user.getUserPassword());
    }
    
    @Test
    public void shouldThrowAnExceptionWhenCreateUserWithInvalidValues() {
        
        UserException userNameEmptyException = assertThrows(UserException.class, () -> {
            User user = new User("", "1234");
        });
        assertEquals("User name or password cannot be empty", userNameEmptyException.getMessage());
    
        UserException userPasswordEmptyException = assertThrows(UserException.class, () -> {
            User user = new User("Jhon Doe", "");
        });
        assertEquals("User name or password cannot be empty", userPasswordEmptyException.getMessage());
    
        UserException userPasswordNullException = assertThrows(UserException.class, () -> {
            User user = new User("Jhon Doe", null);
        });
        assertEquals("User name or password cannot be null", userPasswordNullException.getMessage());
    
        UserException userNameNullException = assertThrows(UserException.class, () -> {
            User user = new User(null, "1231");
        });
        assertEquals("User name or password cannot be null", userNameNullException.getMessage());
        
    }
    
}
