import Utils.UserActionException;
import Utils.UserException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CreateAccount extends JFrame {
    private JPanel createAccountPanel;
    private JLabel jlUserName;
    private JTextField tfUserName;
    private JLabel lbUserPassword;
    private JPasswordField jpfUserPassword;
    private JLabel lbUserConfirmPassword;
    private JPasswordField jpfUserConfirmPassword;
    private JButton jbCreateUser;
    private JButton jbExitButton;
    private JButton jbShowUserCredencial;
    private JButton jbChangeUserCredential;
    private JButton jbRemoveUser;
    private String userName, userPassword, userConfirmPassword;
    
    public CreateAccount() {
        JFrame createAccountFrame = new JFrame("Bem-vindo! Crie sua conta");
        createAccountFrame.setContentPane(createAccountPanel);
        createAccountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createAccountFrame.pack();
        createAccountFrame.setVisible(true);
        
        createAccount();
        showUserCredentials();
        changeUserCredentials();
        validateUserNameField();
        quitApplication();
    }
    
    public void createAccount() {
        jbCreateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                userName = tfUserName.getText();
                userPassword = String.valueOf(jpfUserPassword.getPassword());
                userConfirmPassword = String.valueOf(jpfUserConfirmPassword.getPassword());
                
                try {
                    validateCreateAccount();
                } catch(Exception e){
                    JOptionPane.showMessageDialog(createAccountPanel,"Não foi possível criar a conta!");
                } finally {
                    clearFormFields();
                }
            }
        });
    }
    
    public void showUserCredentials() {
        jbShowUserCredencial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ShowUserCredencial showUserCredencial = new ShowUserCredencial();
                closeWindow();
            }
        });
    }
    
    public void changeUserCredentials() {
        jbChangeUserCredential.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChangeUserCredencial changeUserCredential = new ChangeUserCredencial();
                closeWindow();
            }
        });
    }
    
    public void removeUserCredentials() {
        jbRemoveUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                RemoveUserCredential removeUserCredential = new RemoveUserCredential();
                closeWindow();
            }
        });
    }
    
    public void validateCreateAccount() throws UserActionException, UserException {
        if(userName.isEmpty() || userPassword.isEmpty() || userConfirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(createAccountPanel, "Preencha todos os campos!");
        } else if(!userPassword.equals(userConfirmPassword)) {
            JOptionPane.showMessageDialog(createAccountPanel, "As senhas não conferem!");
        } else {
            User newUser = new User(userName, userPassword);
            UserAction createUser = new UserAction();
            
            createUser.createUser(newUser);
            
            JOptionPane.showMessageDialog(createAccountPanel, "Parabéns " + userName +"! Sua conta foi criada" + "com sucesso!");
        }
    }
    
    public void validateUserNameField() {
        tfUserName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                String userName = tfUserName.getText();
                if (userName.isEmpty()) {
                    JOptionPane.showMessageDialog(createAccountPanel, "Preencha o campo de nome de usuário!");
                } else if(userName.length() < 3 || !Character.isUpperCase(userName.charAt(0))) {
                    JOptionPane.showMessageDialog(createAccountPanel,
                            "O nome de usuário deve ter no mínimo 3 caracteres! E começar com uma letra maiúscula!"
                    );
                }
            }
        });
    }
    
    public void clearFormFields() {
        tfUserName.setText(null);
        jpfUserPassword.setText(null);
        jpfUserConfirmPassword.setText(null);
    }
    
    public void closeWindow() {
        WindowEvent windowClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windowClosingEvent);
        dispose();
    }
    
    public void quitApplication() {
        jbExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }
}
