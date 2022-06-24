import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankApp extends JFrame {
    private JButton btnFrame1;
    private JButton btnFrame2;
    private JButton button1;
    private JTextField thisIsPanel3TextField;
    private JButton btn2Frame1;
    private JPanel cards;
    public BankApp(){
        JFrame frame = new JFrame();

        JPanel p1 = loginScreen();
        p1.setBackground(Color.RED);
        JPanel p2 = checkingScreen();
        p2.setBackground(Color.BLUE);
        JPanel p3 = savingsScreen();
        p3.setBackground(Color.GREEN);


        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(p1, "Panel 1");
        cards.add(p2, "Panel 2");
        cards.add(p3, "Panel 3");

        // Add your card container to the frame
        Container pane = frame.getContentPane();
        pane.add(cards, BorderLayout.CENTER);

        frame.setTitle("Bank of Alaska");
        frame.setLocation(new Point(500,300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setVisible(true);
    }



    public JPanel loginScreen(){
        JLabel password1, label;
        JTextField username;
        JButton button;
        JPasswordField Password;

        JPanel panel = new JPanel();
        panel.setLayout(null);

        label = new JLabel ("Username");
        label.setBounds(100,168,70,20);
        panel.add(label);

        username = new JTextField();
        username.setBounds(100,187,193,28);
        panel.add(username);

        password1 = new JLabel("Password");
        password1.setBounds(100,215,70,20);
        panel.add(password1);

        Password = new JPasswordField();
        Password.setBounds(100,235,193,28);
        panel.add(Password);

        button = new JButton("Login");
        button.setBounds(100,270,90,25);
        button.setForeground(Color.WHITE);
        button.setBackground((Color.BLACK));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.next(cards);
            }
        });
        panel.add(button);

        return panel;
    }

    public JPanel checkingScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        /*Enter your GUI code here for the checking account.
          Make sure to add components to panel using panel.add(component)
          All components must have the component.setBounds(x position, y position, width, height);
        * */


        JButton button = new JButton("Logout");
        button.setBounds(60,360,120,25);
        JButton button2 = new JButton("View Savings");
        button2.setBounds(200,360,120,25);
        button.setForeground(Color.WHITE);
        button.setBackground((Color.BLACK));
        button2.setForeground(Color.WHITE);
        button2.setBackground((Color.BLACK));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "Panel 1");
            }
        });
        panel.add(button);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "Panel 3");
            }
        });
        panel.add(button2);
        return panel;
    }

    public JPanel savingsScreen(){
        JPanel panel = new JPanel();
        panel.setLayout(null);

        /*Enter your GUI code here for the Savings account.
          Make sure to add components to panel using panel.add(component)
          All components must have the component.setBounds(x position, y position, width, height);
        * */

        JButton button = new JButton("Logout");
        button.setBounds(60,360,120,25);
        JButton button2 = new JButton("View Checking");
        button2.setBounds(200,360,120,25);
        button.setForeground(Color.WHITE);
        button.setBackground((Color.BLACK));
        button2.setForeground(Color.WHITE);
        button2.setBackground((Color.BLACK));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "Panel 1");
            }
        });
        panel.add(button);

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "Panel 2");
            }
        });
        panel.add(button2);
        return panel;
    }
}









    //Below this is commented out code from previous build
    /*
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
        JFrame frame = new JFrame("Bank Application");
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

    public void test(){
        //this is a test from Sergio
    }

    public void caseyTest() {
        // this is a test from Casey O....
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
*/

