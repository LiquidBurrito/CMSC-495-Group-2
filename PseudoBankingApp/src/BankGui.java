import javax.swing.*;
import java.awt.*;

public class BankGui extends JFrame {
    LoginGui login = new LoginGui();
    Checking checking = new Checking();
    Savings savings = new Savings();

    JFrame frame = new JFrame("Bank Application");
    JLabel label1 = new JLabel("Label 1");
    JLabel label2 = new JLabel("Label 2");
    JButton button1 = new JButton("Button 1");
    JButton button2 = new JButton("Button 2");
    JComboBox jComboBox = new JComboBox();

    public BankGui() {
        frame.setSize(420, 300);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(label1);
        frame.add(label2);
        frame.add(button1);
        frame.add(button2);
        frame.add(jComboBox);
        frame.setVisible(true);
    }
}

class LoginGui extends JFrame {
    JFrame frame = new JFrame();
    JLabel passwordLabel, usernameLabel;
    JTextField username;
    JButton login;
    JPasswordField password;

    public LoginGui() {
        frame.add(passwordLabel);
        frame.add(usernameLabel);
        frame.add(login);
        frame.add(username);
        frame.add(password);
    }

}
