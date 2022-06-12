import javax.swing.*;
import java.awt.*;

public class BankApp extends JFrame {
//    LoginGui loginGui = new LoginGui();
    Checking checking = new Checking();
    Savings savings = new Savings();

    JPanel bankPanel = new JPanel(new GridLayout(4, 1, 0, 20));
    JPanel panel1 = new JPanel(new GridLayout(1, 2, 10, 0));
    JPanel panel2 = new JPanel(new GridLayout(1, 2, 10, 0));

    JLabel label1 = new JLabel("Label 1");
    JLabel label2 = new JLabel("Label 2");
    JButton button1 = new JButton("Button 1");
    JButton button2 = new JButton("Button 2");
    JButton logoutButton = new JButton("Logout");
    String comboBoxItems[] = {"Checking", "Savings"};
    JComboBox jComboBox = new JComboBox(comboBoxItems);

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Bank Application--Hello, CMSC Finals--4 MORE WEEKS WHADDDUPPPPP");
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 250);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        BankApp app = new BankApp();
        app.addComponentToPane(frame.getContentPane());

        LoginGui loginGui = new LoginGui();

        frame.setVisible(true);
    }

    public void addComponentToPane(Container pane) {
        panel1.add(label1);
        panel1.add(button1);
        panel2.add(label2);
        panel2.add(button2);

        bankPanel.add(panel1);
        bankPanel.add(panel2);
        bankPanel.add(jComboBox);
        bankPanel.add(logoutButton);

        pane.add(bankPanel);
    }

    public BankApp() {

    }


}

class LoginGui {
    JFrame frame = new JFrame("Login");

    JLabel usernameLabel = new JLabel("Username");
    JTextField usernameTextField = new JTextField("Enter Username", 10);
    JButton loginButton = new JButton("Login");

    JPanel loginPanel = new JPanel(new GridLayout(4, 1));
    JPanel usernamePanel = new JPanel(new GridLayout(1, 2));

    JPanel bankPanel = new JPanel(new GridLayout(4, 1, 0, 10));
    JPanel panel1 = new JPanel(new GridLayout(1, 2, 10, 0));
    JPanel panel2 = new JPanel(new GridLayout(1, 2, 10, 0));

    public LoginGui() {
        frame.setSize(240, 150);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponentToPane(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);

    }

    public void addComponentToPane(Container pane) {
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);

        loginPanel.add(usernamePanel);
        loginPanel.add(new JLabel(""));
        loginPanel.add(new JLabel(""));
        loginPanel.add(loginButton);

        pane.add(loginPanel);
    }

}
