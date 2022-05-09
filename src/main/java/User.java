import Utils.UserException;

public class User {
    private String userName;
    private String userPassword;
    
    public User(){}
    
    public User(String userName, String userPassword) throws UserException {
        if(userPassword == null || userName == null){
            throw new UserException("User name or password cannot be null");
        } else if(userPassword.length() == 0 || userName.length() == 0){
            throw new UserException("User name or password cannot be empty");
        } else {
            this.setUserName(userName);
            this.setUserPassword(userPassword);
    
            System.out.println("New user created.");
        }
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserPassword() {
        return userPassword;
    }
    
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
