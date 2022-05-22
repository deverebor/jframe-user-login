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
        } else {
            throw new UserActionException("Maximum number of users reached!");
        }
    }
    
    public void removeUser(User user) throws UserActionException {
        if(pointer < 0){
            throw new UserActionException("You can not remove users, because there are no users");
        } else if(user == null){
            throw new UserActionException("The user you tries to remove does not exists");
        } else {
            try {
                for(int i = 0; i < pointer; i++){
                    if(user.equals(users[i])){
                        users[i] = null;
                    }
                }
            } catch (Exception error) {
                throw new UserActionException("Can not remove user");
            }
        }
    }
    
    public void updateUser(String userName, String userPassword) throws UserActionException {
        if(pointer < 0){
            throw new UserActionException("There no users to update");
        }
        try {
            for(int i = 0; i < pointer; i++){
                if(
                        userName.equals(users[i].getUserName())
                         && userPassword.equals(users[i].getUserPassword())
                ){
                    throw new UserActionException("The new user data is the same as the old one");
                } else if(userName.length() == 0 || userPassword.length() == 0){
                    throw new UserActionException("The new user data is empty");
                }
                else {
                    users[i].setUserName(userName);
                    users[i].setUserPassword(userPassword);
                }
            }
        } catch (Exception error) {
            throw new UserActionException("Can not update user");
        }
        
    }
    
    public String[] searchUser(String userPassword) throws UserActionException {
        if (pointer <= 0) {
            throw new UserActionException("There are no users to search");
        }
        String[] searchedUsers = new String[0];
        for (int i = 0; i < pointer; i++) {
            if (userPassword.equals(users[i].getUserName())) {
                searchedUsers = new String[]{
                        users[i].getUserName(),
                        users[i].getUserPassword(),
                };
            }
        }
        
        return searchedUsers;
    }
}
