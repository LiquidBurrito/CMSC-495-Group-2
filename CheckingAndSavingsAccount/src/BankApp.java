import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class BankApp extends JFrame {

    private final JPanel cards;
    // ImageIcon added 25Jun22, JH
    ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Money.png"));
    // random number generation for account balance
    // added 25Jun22, JH
    Double min = 100.00;
    Double max = 10000.00;
    double randoDouble = (Math.random() * ((max - min) + 1)) + min;
    double roundDouble = Math.round(randoDouble * 100.0) / 100.0;
    // random number generation for savings account balance
    // added 25Jun22, JH
    Double min2 = 2500.00;
    Double max2 = 100000.00;
    double randoDouble2 = (Math.random() * ((max2 - min2) + 1)) + min2;
    double roundDouble2 = Math.round(randoDouble2 * 100.0) / 100.0;
    // random number generation for days since last login
    // added 25Jun22, JH
    private static final Random rando = new Random();
    private static final int MAX = 730;
    private static final int MIN = 31;
    Integer randoInt = rando.nextInt((MAX-MIN)+1)+MIN;


    // Admin creds input for Login Creds -- Added 26June
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Password";

    HashMap<String, String> loginCreds = new HashMap<>();
    public BankApp(){
        JFrame frame = new JFrame();

        // Add USERNAME and PASSWORD to login creds hashmap -- Added 26June
        loginCreds.put(USERNAME, PASSWORD);

        JPanel p1 = loginScreen();
        p1.setBackground(Color.orange);
        JPanel p2 = checkingScreen();
        p2.setBackground(Color.orange);
        JPanel p3 = savingsScreen();
        p3.setBackground(Color.orange);

        // create the panel that contains the "cards"
        cards = new JPanel(new CardLayout());
        cards.add(p1, "Panel 1");
        cards.add(p2, "Panel 2");
        cards.add(p3, "Panel 3");

        // add your card container to the frame
        Container pane = frame.getContentPane();
        pane.add(cards, BorderLayout.CENTER);

        frame.setTitle("SJC Credit Union");
        frame.setIconImage(icon.getImage());
        frame.setLocation(new Point(500,300));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setVisible(true);
    }

    // loginScreen GUI
    public JPanel loginScreen(){

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // JLabels for loginScreen
        // added 25Jun22, JH
        JLabel welcomeLabel = new JLabel("Welcome To SJC Credit Union");
        welcomeLabel.setBounds(20, 5, 800, 50);
        welcomeLabel.setFont(new Font("Monaco", Font.BOLD, 50));
        panel.add(welcomeLabel);

        // Message Label to depict login failure -- Added 26June
        JLabel messageLabel = new JLabel("Welcome. Input your Login Credentials Please.");
        messageLabel.setBounds(260, 75, 360, 20);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(messageLabel);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(290, 100, 100, 20);
        userLabel.setFont(new Font("Monaco",Font.BOLD, 15));
        panel.add(userLabel);

        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(290, 160, 100, 20);
        passLabel.setFont(new Font("Monaco",Font.BOLD, 15));
        panel.add(passLabel);

        // JFields for loginScreen
        JTextField userText = new JTextField(20);
        userText.setBounds(290,125,200, 25);
        panel.add(userText);

        JPasswordField passText = new JPasswordField();
        passText.setBounds(290,185,200,25);
        panel.add(passText);

        // JButtons for loginScreen
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(340,225,90,25);
        loginButton.setFont(new Font("Monaco", Font.BOLD, 15));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground((Color.BLACK));
        panel.add(loginButton);


        // ActionListeners for loginScreen
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Various Algorithm tests to check for incorrect or correctly entered login credentials -- Added 26June
                // Commented code left in temporarily; also see checkLoginCreds() below
                String usern = userText.getText();
                String passw = String.valueOf(passText.getPassword());

                if((!loginCreds.containsKey(usern)) || (!loginCreds.get(usern).equals(passw))) {
                    try {
                        throw new UnauthorizedLoginException("Invalid login credentials entered. Please try again.");
                    } catch (Exception ex) {
                        System.out.println("An error occurred: " + ex);
                    }
                    messageLabel.setText("Invalid login credentials entered. Please try again.");
                }
                if(loginCreds.containsKey(usern)) {
                    if(loginCreds.get(usern).equals(passw)) {
                        CardLayout cl = (CardLayout)(cards.getLayout());
                        cl.next(cards);
                    }
//                    else {
//                        messageLabel.setText("Invalid login credentials entered. Please try again.");
//                    }
//                } else {
//                    messageLabel.setText("Invalid login credentials entered. Please try again.");
                }

//                if((usern != "Username") || (passw != "Password")) {

////                    try {
////                        throw new UnauthorizedLoginException("Invalid login credentials entered. Please try again.");
////                    } catch (Exception ex) {
////                        System.out.println("An error occurred: " + ex);
////                    }

//                    System.out.println("Invalid login credentials entered. Please try again.");
//                } else {
//                    CardLayout cl = (CardLayout)(cards.getLayout());
//                    cl.next(cards);
//                }

//                try {
//                    checkLoginCreds(usern, passw);
//                    CardLayout cl = (CardLayout)(cards.getLayout());
//                    cl.next(cards);
//                } catch (Exception ex) {
//                    System.out.println("An error occurred" + ex);
//                }

            }
        });
        return panel;
    }

    // Test function for throwing new UnauthorizedLoginException -- 26June
//    static void checkLoginCreds(String usern, String passw) throws UnauthorizedLoginException {
//        if((usern != USERNAME) || (passw != PASSWORD)) {
//            throw new UnauthorizedLoginException("Invalid login credentials entered. Please try again.");
//        }
//    }

    // checkingScreen GUI
    public JPanel checkingScreen() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        /*Enter your GUI code here for the checking account.
          Make sure to add components to panel using panel.add(component)
          All components must have the component.setBounds(x position, y position, width, height);
        * */

        // JLabels for checkingScreen
        // added 26Jun22, JH
        Date date = new Date();
        new SimpleDateFormat();
        JLabel dTG = new JLabel("Today is " + date);
        dTG.setBounds(260, 315, 300, 20);
        dTG.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(dTG);

        // added 26Jun22, JH
        JLabel lastLog = new JLabel("Your last login was " + randoInt + " days ago");
        lastLog.setBounds(260, 340, 300, 20);
        lastLog.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(lastLog);

        // added 26Jun22, JH
        JLabel accountLabel = new JLabel("Checking Account");
        accountLabel.setBounds(20, 5, 800, 60);
        accountLabel.setFont(new Font("Monaco", Font.BOLD, 50));
        panel.add(accountLabel);

        // added 26Jun22, JH
        JLabel currentBalance = new JLabel("Balance: $"+roundDouble);
        currentBalance.setBounds(435, 85, 350, 40);
        currentBalance.setFont(new Font("Monaco", Font.BOLD, 25));
        panel.add(currentBalance);

        // added 26Jun22, JH
        JLabel transactionType = new JLabel("Please choose a transaction type");
        transactionType.setBounds(20, 90, 250, 20);
        transactionType.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(transactionType);

        // added 26Jun22, JH
        JLabel transactionAmount = new JLabel("Enter your transaction amount");
        transactionAmount.setBounds(20, 200, 250, 20);
        transactionAmount.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(transactionAmount);

        // JTextFields for checkingScreen
        // added 26Jun22, JH
        JTextField transactionInput = new JTextField("$");
        transactionInput.setBounds(20, 230, 230, 40);
        transactionInput.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(transactionInput);

        // JComboBox for checkingScreen
        // added 26Jun22, JH
        String[] transactions1 = {"Deposit","Withdraw"};
        JComboBox jCB = new JComboBox(transactions1);
        jCB.setBounds(20,120,100,20);
        jCB.setRenderer(new DefaultListCellRenderer() {
            public void paint(Graphics g) {
                setBackground(Color.green);
                setForeground(Color.black);
                super.paint(g);
            }
        });
        panel.add(jCB);

        //JButtons for checkingScreen
        // added 27Jun22, JH
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(270, 230, 100, 40);
        submitButton.setFont(new Font("Monaco", Font.BOLD, 15));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground((Color.BLACK));
        panel.add(submitButton);

        // added 27Jun22, JH
        JButton goToButton = new JButton("Go To Savings");
        goToButton.setBounds(20,320,130,25);
        goToButton.setFont(new Font("Monaco", Font.BOLD, 12));
        goToButton.setForeground(Color.WHITE);
        goToButton.setBackground((Color.BLACK));
        panel.add(goToButton);

        // added 27Jun22, JH
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(650,320,120,25);
        logoutButton.setFont(new Font("Monaco", Font.BOLD, 15));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground((Color.BLACK));
        panel.add(logoutButton);

        // actionListeners for checkingScreen
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        goToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "Panel 3");
            }
        });

        //return panel;

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "Panel 1");
            }
        });
        return panel;
    }

    // savingsScreen GUI
    public JPanel savingsScreen(){
        JPanel panel = new JPanel();
        panel.setLayout(null);

        /*Enter your GUI code here for the checking account.
          Make sure to add components to panel using panel.add(component)
          All components must have the component.setBounds(x position, y position, width, height);
        * */

        // JLabels for savingsScreen
        // added 27Jun22, JH
        Date date = new Date();
        new SimpleDateFormat();
        JLabel dTG = new JLabel("Today is " + date);
        dTG.setBounds(260, 315, 300, 20);
        dTG.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(dTG);

        // added 27Jun22, JH
        JLabel lastLog = new JLabel("Your last login was " + randoInt + " days ago");
        lastLog.setBounds(260, 340, 300, 20);
        lastLog.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(lastLog);

        // added 27Jun22, JH
        JLabel accountLabel = new JLabel("Savings Account");
        accountLabel.setBounds(20, 5, 800, 60);
        accountLabel.setFont(new Font("Monaco", Font.BOLD, 50));
        panel.add(accountLabel);

        // added 27Jun22, JH
        JLabel currentBalance = new JLabel("Balance: $"+roundDouble2);
        currentBalance.setBounds(435, 85, 350, 40);
        currentBalance.setFont(new Font("Monaco", Font.BOLD, 25));
        panel.add(currentBalance);

        // added 27Jun22, JH
        JLabel transactionType = new JLabel("Please choose a transaction type");
        transactionType.setBounds(20, 90, 250, 20);
        transactionType.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(transactionType);

        // added 27Jun22, JH
        JLabel transactionAmount = new JLabel("Enter your transaction amount");
        transactionAmount.setBounds(20, 200, 250, 20);
        transactionAmount.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(transactionAmount);

        // added 27Jun22, JH
        JLabel interestAccrued = new JLabel("Interest accrued since last login $TBD");
        interestAccrued.setBounds(435, 120, 350, 20);
        interestAccrued.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(interestAccrued);

        // JTextFields for savingsScreen
        // added 27Jun22, JH
        JTextField transactionInput = new JTextField("$");
        transactionInput.setBounds(20, 230, 230, 40);
        transactionInput.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(transactionInput);

        // JComboBox for savingsScreen
        // added 27Jun22, JH
        String[] transactions1 = {"Deposit","Withdraw"};
        JComboBox jCB = new JComboBox(transactions1);
        jCB.setBounds(20,120,100,20);
        jCB.setRenderer(new DefaultListCellRenderer() {
            public void paint(Graphics g) {
                setBackground(Color.green);
                setForeground(Color.black);
                super.paint(g);
            }
        });
        panel.add(jCB);

        //JButtons for savingsScreen
        // added 27Jun22, JH
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(270, 230, 100, 40);
        submitButton.setFont(new Font("Monaco", Font.BOLD, 15));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground((Color.BLACK));
        panel.add(submitButton);

        // added 27Jun22, JH
        JButton goToButton = new JButton("Go To Checking");
        goToButton.setBounds(20,320,130,25);
        goToButton.setFont(new Font("Monaco", Font.BOLD, 12));
        goToButton.setForeground(Color.WHITE);
        goToButton.setBackground((Color.BLACK));
        panel.add(goToButton);

        // added 27Jun22, JH
        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(650,320,120,25);
        logoutButton.setFont(new Font("Monaco", Font.BOLD, 15));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground((Color.BLACK));
        panel.add(logoutButton);

        // actionListeners for savingsScreen
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        goToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "Panel 2");
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, "Panel 1");
            }
        });
        return panel;
    }
}