import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class RemoveUserCredential extends JFrame {
    
    private JPanel removeUserAccountPanel;
    private JLabel jlUserName;
    private JTextField jtfUserName;
    private JLabel jlUserPassword;
    private JLabel jlConfirmUserPassword;
    private JPasswordField jpfUserPassword;
    private JPasswordField jpfConfirmUserPassword;
    private JButton jbRemoveUser;
    private JButton jbReturnToMenu;
    
    private String userName, userPassword, userConfirmPassword;
    
    public RemoveUserCredential() {
        JFrame removeUserAccount = new JFrame("Remover Conta de Usuário");
        removeUserAccount.setContentPane(removeUserAccountPanel);
        removeUserAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        removeUserAccount.pack();
        removeUserAccount.setVisible(true);
        
        removeUser();
        returnToMenu();
    }
    
    public void removeUser() {
        jbRemoveUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    userName = jtfUserName.getText();
                    userPassword = String.valueOf(jpfUserPassword.getPassword());
                    userConfirmPassword = String.valueOf(jpfConfirmUserPassword.getPassword());
                    
                    User oldUser = new User();
                    UserAction removeUser = new UserAction();
        
                    validateFormFields(oldUser);
                    if(oldUser.getUserName().equals(userName) && oldUser.getUserPassword().equals(userPassword)) {
                        removeUser.removeUser(oldUser);
                        JOptionPane.showMessageDialog(removeUserAccountPanel, "Usuário removido com sucesso!");
                    }
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(removeUserAccountPanel, "Por favor, preencha os campos corretamente !");
                } finally {
                    clearFormFields();
                }
            }
        });
    }
    
    public void returnToMenu() {
        jbReturnToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                closeWindow();
            }
        });
    }
    
    public void validateFormFields(User user) {
        if (userName.isEmpty() || userPassword.isEmpty() || userConfirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(removeUserAccountPanel, "Por favor, preencha os campos corretamente !");
        } else if(user.getUserName() == null || user.getUserPassword() == null) {
            JOptionPane.showMessageDialog(removeUserAccountPanel, "O CPF indicado não existe!");
        }
        else if (!userPassword.equals(userConfirmPassword) || !user.getUserPassword().equals(userPassword)) {
            JOptionPane.showMessageDialog(removeUserAccountPanel, "As senhas não coincidem!");
        }
    }
    
    public void closeWindow() {
        WindowEvent windowClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windowClosingEvent);
        dispose();
        CreateAccount mainFrame = new CreateAccount();
    }
    
    public void clearFormFields() {
        jtfUserName.setText("");
        jpfUserPassword.setText("");
        jpfConfirmUserPassword.setText("");
    }
}
