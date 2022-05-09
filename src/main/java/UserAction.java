import Utils.UserActionException;

public class UserAction {
    private final User[] users;
    private final int maxUsers = 25;
    private int pointer = 0;
    
    public UserAction() {
        users = new User[maxUsers];
    }
    
    public void createUser(User user) throws UserActionException {
        if(pointer < maxUsers) {
            try {
                users[pointer] = user;
                pointer++;
                
                System.out.println("User account created successfully!");
            } catch (Exception error) {
                throw new UserActionException("Error creating user account!");
            }
        }
    }
}
