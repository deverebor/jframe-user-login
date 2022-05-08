import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
    
    public CreateAccount() {
        this.setTitle("Bem-vindo! Crie sua conta");
        this.setContentPane(createAccountPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        createAccount();
        validateUserNameField();
        quitApplication();
    }
    
    public void createAccount() {
        jbCreateUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String userName = tfUserName.getText();
                String userPassword = String.valueOf(jpfUserPassword.getPassword());
                String userConfirmPassword = String.valueOf(jpfUserConfirmPassword.getPassword());
                
                if (userName.isEmpty() || userPassword.isEmpty() || userConfirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(createAccountPanel, "Preencha todos os campos!");
                }
                else if (!userPassword.equals(userConfirmPassword)) {
                    JOptionPane.showMessageDialog(createAccountPanel, "As senhas não conferem!");
                } else {
                    JOptionPane.showMessageDialog(createAccountPanel,
                            "Parabéns " + userName +"! Sua conta foi criada" + "com sucesso!"
                    );
                }
            }
        });
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
    
    public void quitApplication() {
        jbExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }
}
