import Utils.UserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {
    
    @Test
    public void shouldCreateUser() throws UserException {
        String userName = "Jhon Doe";
        String userPassword = "1234";
        
        User user = new User(userName, userPassword);
        
        assertEquals(userName, user.getUserName());
        assertEquals(userPassword, user.getUserPassword());
    }
    
    @Test
    public void shouldThrowAnExceptionWhenCreateUserWithInvalidValues() {
        
        String userNameOrPasswordEmptyException = "User name or password cannot be empty";
        String userNameOrPasswordNullException = "User name or password cannot be null";
        
        UserException userNameEmptyException = assertThrows(UserException.class, () -> {
            User user = new User("", "1234");
        });
        
        assertEquals(userNameOrPasswordEmptyException, userNameEmptyException.getMessage());
    
        UserException userPasswordEmptyException = assertThrows(UserException.class, () -> {
            User user = new User("Jhon Doe", "");
        });
        
        assertEquals(userNameOrPasswordEmptyException, userPasswordEmptyException.getMessage());
    
        UserException userPasswordNullException = assertThrows(UserException.class, () -> {
            User user = new User("Jhon Doe", null);
        });
        
        assertEquals(userNameOrPasswordNullException, userPasswordNullException.getMessage());
    
        UserException userNameNullException = assertThrows(UserException.class, () -> {
            User user = new User(null, "1231");
        });
        
        assertEquals(userNameOrPasswordNullException, userNameNullException.getMessage());
        
    }
    
}
