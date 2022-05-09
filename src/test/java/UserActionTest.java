import Utils.UserActionException;
import Utils.UserException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserActionTest {
    
    String userName = "Jhon Doe";
    String userPassword = "1234";
    
    @Test
    public void shouldCreateUser() throws UserException, UserActionException {
        User user = new User(userName, userPassword);
        UserAction userAction = new UserAction();
        userAction.createUser(user);
    
        assertTrue(user.getUserName().length() > 0);
    }
    
    @Test
    public void shouldThrowUserActionExpectionWhenMaximunUsersAreCreated() throws UserException, UserActionException {
        int maxUsers = 26;
        String maxiumUsersMessage = "Maximum number of users reached!";
        
        User user = new User(userName, userPassword);
        UserAction userAction = new UserAction();
        UserActionException userActionExceptionMaxUsers = assertThrows(UserActionException.class, () -> {
            for (int i = 0; i < maxUsers; i++) {
                userAction.createUser(user);
            }
        });
        
        assertEquals(maxiumUsersMessage, userActionExceptionMaxUsers.getMessage());
        
    }
}
