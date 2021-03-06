import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Login extends JFrame {
    private JPasswordField txtPassword;
    private JTextField txtUsername;
    private JButton btnLogin;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JPanel pnlLogin;
    private JButton forgotYourPasswordButton;

    private Login() {
        initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Login.visible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @SuppressWarnings("SameParameterValue")
    static void visible(boolean b) {
        Login login = new Login();
        login.setVisible(true);
    }

    private void initialize() {
        setTitle("Login");
        setContentPane(pnlLogin);
        setResizable(false);
        setBounds(100, 100, 320, 175);
        Helper.centreWindow(this);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        pnlLogin.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        pnlLogin.registerKeyboardAction(e -> {
            try {
                UserLogin();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        btnLogin.addActionListener(e -> {
            try {
                UserLogin();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        forgotYourPasswordButton.addActionListener(actionEvent -> {
            ForgetPassword.visible(true);
        });
    }

    private void UserLogin() throws Exception {
        var username = txtUsername.getText();
        @SuppressWarnings("deprecation")
        var password = txtPassword.getText();
        boolean status = Database.login(username, password);
        if (status) {
            setVisible(false);
            Main.visible(true);
        } else {
            lblUsername.setForeground(Color.RED);
            lblPassword.setForeground(Color.RED);
            Helper.showDialog("Wrong username or password. Please check.");
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
