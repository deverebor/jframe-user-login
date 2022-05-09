import Utils.UserActionException;
import Utils.UserException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ChangeUserCredencial extends JFrame {
    private JPanel changeUserCredencialPanel;
    private JButton jbReturnToMenu;
    private JButton jbChangeUserCredencial;
    private JTextField jtfUserName;
    private JLabel jlUserName;
    private JPasswordField jpfUserPassword;
    private JLabel jlUserPassword;
    private JLabel jlNewUserName;
    private JLabel jlNewPassword;
    private JPasswordField jpfNewPassword;
    private JTextField jtfNewUserName;
    private String oldUserName, oldUserPassword, newUserName, newUserPassword;
    
    public ChangeUserCredencial() {
        JFrame changeUserCredencialFrame = new JFrame();
        changeUserCredencialFrame.setTitle("Change User Credencial");
        changeUserCredencialFrame.setContentPane(changeUserCredencialPanel);
        changeUserCredencialFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        changeUserCredencialFrame.pack();
        changeUserCredencialFrame.setVisible(true);
    
        changeExistentUser();
        returnToMenu();
    }
    
    public void changeExistentUser() {
        jbChangeUserCredencial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                User oldUser = new User();
                
                oldUserName = jtfUserName.getText();
                oldUserPassword = String.valueOf(jpfUserPassword.getPassword());
                
                newUserName = jtfNewUserName.getText();
                newUserPassword = String.valueOf(jpfNewPassword.getPassword());
                
                validateFormFields(oldUser);
    
                try {
                    User newUser = new User(newUserName, newUserPassword);
                    UserAction createNewUser = new UserAction();
    
                    validateFormFields(newUser);
                    
                    createNewUser.createUser(newUser);
                } catch (UserException | UserActionException error) {
                    JOptionPane.showMessageDialog(changeUserCredencialPanel, "Erro ao alterar usuário!");
                    System.out.println("Error: " + error.getMessage());
                } finally {
                    clearFormFields();
                }
            }
        });
    }
    
    public void validateFormFields(User user) {
        if (oldUserName.isEmpty() || newUserName.isEmpty() && oldUserPassword.isEmpty() || newUserPassword.isEmpty()) {
            JOptionPane.showMessageDialog(changeUserCredencialPanel, "Por favor, preencha todos os campos!");
        } else if (oldUserName.equals(user.getUserName()) && newUserName.equals(user.getUserName())
                || oldUserPassword.equals(user.getUserPassword()) && newUserPassword.equals(user.getUserPassword())
        ) {
            JOptionPane.showMessageDialog(changeUserCredencialPanel, "O nome do usuário não pode ser igual ao antigo!");
        }
    }
    
    public void returnToMenu() {
        jbReturnToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                closeWindow();
            }
        });
    }
    
    public void clearFormFields() {
        jtfUserName.setText("");
        jpfUserPassword.setText("");
        jtfNewUserName.setText("");
        jpfNewPassword.setText("");
    }
    
    public void closeWindow() {
        WindowEvent windowClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSED);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windowClosingEvent);
        dispose();
        CreateAccount mainFrame = new CreateAccount();
    }
}
