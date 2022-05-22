import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class ShowUserCredencial extends JFrame {
    private JTable jtShowUserContent;
    private JPanel showUserCredencialPanel;
    private JScrollPane jspShowUserContent;
    private JButton jbReturnToLogin;
    private JTextField jtfUserName;
    private JLabel jlUserName;
    private JButton jbSearchUser;
    
    public ShowUserCredencial() {
        JFrame showUserCredencialFrame = new JFrame("Pesquisar um usuário");
        showUserCredencialFrame.setContentPane(showUserCredencialPanel);
        showUserCredencialFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showUserCredencialFrame.pack();
        showUserCredencialFrame.setVisible(true);
    
        returnToLogin();
        showCredentials();
    }
    
    public void showCredentials() {
        jbSearchUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    User user = new User();
                    jtShowUserContent.setModel(new AbstractTableModel() {
                        final String[] columnNames = {"Nome do usuário"};
                        final Object[][] data = {{user.getUserName()}};
                    
                        @Override
                        public int getColumnCount() {
                            return columnNames.length;
                        
                        }
                    
                        @Override
                        public int getRowCount() {
                            return data.length;
                        }
                    
                        @Override
                        public String getColumnName(int col) {
                            return columnNames[col];
                        
                        }
                    
                        @Override
                        public Object getValueAt(int row, int col) {
                            return data[row][col];
                        }
                    
                        @Override
                        public boolean isCellEditable(int row, int col) {
                            return false;
                        }
                    
                        @Override
                        public void setValueAt(Object value, int row, int col) {
                            data[row][col] = value;
                            fireTableCellUpdated(row, col);
                        }
                    
                    });
                    JOptionPane.showMessageDialog(showUserCredencialPanel, "Os usuários foram encontrados na base de dados!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(showUserCredencialPanel, "Não foi possível encontrar os usuários na base de dados!");
                }
            }
        });
    }
    
    public void returnToLogin() {
        jbReturnToLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                closeWindow();
            }
        });
    }
    
    public void closeWindow() {
        WindowEvent windowClosingEvent = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windowClosingEvent);
    }
}
