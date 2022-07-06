import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class BankApp extends JFrame {

    private final JPanel cards;

    private JPanel p1, p2, p3;

    private static final DecimalFormat df = new DecimalFormat("0.00");
    // added decimal formatter 02JUL22, JH
    DecimalFormat formatter = new DecimalFormat("0.00");
    // ImageIcon added 25Jun22, JH
    // updated pathing for ImageIcon 03JUL22, JH
    ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Money.png"));

    int daysCounter;

    // random number generation for checking account balance
    // added 25Jun22, JH
    // updated formatting 03JUL22, JH
    double min = 100.00;
    Double max = 1000.00;
    double randoDouble1 = (Math.random() * ((max - min) + 1)) + min;
    double roundDouble1 = Double.parseDouble(formatter.format(Math.round(randoDouble1 * 100.0) / 100.0));

    // random number generation for savings account balance
    // added 25Jun22, JH
    // updated formatting 03JUL22, JH
    Double min2 = 2500.00;
    Double max2 = 10000.00;
    double randoDouble2 = (Math.random() * ((max2 - min2) + 1)) + min2;
    double roundDouble2 = Double.parseDouble(formatter.format(Math.round(randoDouble2 * 100.0) / 100.0));

    // random number generation for days since last login
    // added 25Jun22, JH
    private static final Random rando = new Random();
    private static final int MAX = 90;
    private static final int MIN = 30;
    Integer randoInt = rando.nextInt((MAX-MIN)+1)+MIN;

    // Admin creds input for Login Creds -- added 26June, CO
    // Updated username to allow for any case entered -- updated 05Jul22, CO
    private static final String USERNAME = "username";
    private static final String PASSWORD = "Password";

    HashMap<String, String> loginCreds = new HashMap<>();

    private double intAccrued;
    private int loginTimes;
    private int compoundTimes(){
        return daysCounter/30;
    }
    private int remainingDays(){
        return daysCounter % 30;
    }
    private void calculateInterest (){
        //add random days to current days count
        daysCounter += randoInt;
        //calculates interest accrued based on daysCounter
        intAccrued =  calculateHelper(roundDouble2,compoundTimes(),0.015, 12);
        //adds interest accrued to savings balance
        roundDouble2 += intAccrued;
        //updates daysCounter to remaining days after last compound
        daysCounter = remainingDays();
    }

    private double calculateHelper(double p, int t, double r, int n) {
            double amount = p * Math.pow(1 + (r / n), n * t);
            double cinterest = amount - p;
            return cinterest;
    }

    public BankApp() {

        daysCounter = 0;
        intAccrued = 0;
        loginTimes = 0;
        JFrame frame = new JFrame();

        // Add USERNAME and PASSWORD to login creds hashmap -- added 26June, CO
        loginCreds.put(USERNAME, PASSWORD);

        p1 = loginScreen();
        p1.setBackground(Color.orange);
        p2 = checkingScreen();
        p2.setBackground(Color.orange);
        p3 = savingsScreen();
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
    public JPanel loginScreen() {

        JPanel panel = new JPanel();
        panel.setLayout(null);

        // JLabels for loginScreen
        // added 25Jun22, JH
        JLabel welcomeLabelL = new JLabel("Welcome To SJC Credit Union");
        welcomeLabelL.setBounds(20, 5, 800, 50);
        welcomeLabelL.setFont(new Font("Monaco", Font.BOLD, 50));
        panel.add(welcomeLabelL);

        // Message Label to depict login failure -- added 26June, CO
        JLabel messageLabelL = new JLabel("Welcome. Input your Login Credentials Please.");
        messageLabelL.setBounds(260, 75, 360, 20);
        messageLabelL.setFont(new Font("Arial", Font.PLAIN, 15));
        panel.add(messageLabelL);

        JLabel userLabelL = new JLabel("Username");
        userLabelL.setBounds(290, 100, 100, 20);
        userLabelL.setFont(new Font("Monaco",Font.BOLD, 15));
        panel.add(userLabelL);

        JLabel passLabelL = new JLabel("Password");
        passLabelL.setBounds(290, 160, 100, 20);
        passLabelL.setFont(new Font("Monaco",Font.BOLD, 15));
        panel.add(passLabelL);

        // JFields for loginScreen
        JTextField userTextL = new JTextField(20);
        userTextL.setBounds(290,125,200, 25);
        panel.add(userTextL);

        JPasswordField passTextL = new JPasswordField();
        passTextL.setBounds(290,185,200,25);
        panel.add(passTextL);

        // JButtons for loginScreen
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(340,225,90,25);
        loginButton.setFont(new Font("Monaco", Font.BOLD, 15));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground((Color.BLACK));
        panel.add(loginButton);


        // ActionListeners for loginScreen
        loginButton.addActionListener(e -> {

            // Check for incorrect or correctly entered login credentials -- added 26June, CO
            // Updated username to allow for any case entered -- updated 05Jul22, CO
            String usern = userTextL.getText().toLowerCase();
            String passw = String.valueOf(passTextL.getPassword());

            if((!loginCreds.containsKey(usern)) || (!loginCreds.get(usern).equals(passw))) {
                try {
                    throw new UnauthorizedLoginException("Invalid login credentials entered. Please try again.");
                } catch (Exception ex) {
                    System.out.println("An error occurred: " + ex);
                }
                messageLabelL.setText("Invalid login credentials entered. Please try again.");
            }
            if(loginCreds.containsKey(usern)) {
                if(loginCreds.get(usern).equals(passw)) {
                    CardLayout cl = (CardLayout)(cards.getLayout());
                    cl.next(cards);
                    // added these 2 lines to clear text fields after login 3JUL, JH
                    userTextL.setText("");
                    passTextL.setText("");
                }

            }
        });

        return panel;
    }

    // JLabels that apply to both checking and savings cards
    //

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
        JLabel accountLabel1 = new JLabel("Checking Account");
        accountLabel1.setBounds(20, 5, 800, 60);
        accountLabel1.setFont(new Font("Monaco", Font.BOLD, 50));
        panel.add(accountLabel1);

        // added 26Jun22, JH
        JLabel currentBalance1 = new JLabel("Balance: $" + roundDouble1);
        currentBalance1.setBounds(435, 85, 350, 40);
        currentBalance1.setFont(new Font("Monaco", Font.BOLD, 25));
        panel.add(currentBalance1);

        // added 26Jun22, JH
        JLabel transactionType1 = new JLabel("Please choose a transaction type");
        transactionType1.setBounds(20, 90, 250, 20);
        transactionType1.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(transactionType1);

        // added 26Jun22, JH
        JLabel transactionAmount1 = new JLabel("Enter your transaction amount");
        transactionAmount1.setBounds(20, 200, 250, 20);
        transactionAmount1.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(transactionAmount1);

        // added 3JUL22, JH
        JLabel depositAmount1 = new JLabel("");
        depositAmount1.setBounds(20, 180, 800, 40);
        depositAmount1.setFont(new Font("Monaco", Font.BOLD, 20));
        panel.add(depositAmount1);

        // JTextFields for checkingScreen
        // added 26Jun22, JH
        JTextField transactionInput1 = new JTextField();
        transactionInput1.setBounds(20, 230, 230, 40);
        transactionInput1.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(transactionInput1);

        // added KeyListener to ensure only numbers and decimals are input into JTextField
        // any that is not 0-9 or "." gives error
        // added 03JUL22, JH
        transactionInput1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == '.') {
                    transactionInput1.setEditable(true);
                    transactionAmount1.setText("");
                } else {
                    transactionInput1.setEditable(false);
                    transactionInput1.setText("Numbers Only! Click to Clear!");
                }
            }
        });

        // added MouseListeners 03JUL22, JH
        transactionInput1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                transactionInput1.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        // JComboBox for checkingScreen
        // added 26Jun22, JH
        String[] transactions1 = {"Deposit","Withdraw"};
        JComboBox jCB1 = new JComboBox(transactions1);
        jCB1.setBounds(20,120,100,20);
        jCB1.setRenderer(new DefaultListCellRenderer() {
            public void paint(Graphics g) {
                setBackground(Color.green);
                setForeground(Color.black);
                super.paint(g);
            }
        });
        panel.add(jCB1);

        //JButtons for checkingScreen
        // added 27Jun22, JH
        JButton submitButton1 = new JButton("Submit");
        submitButton1.setBounds(270, 230, 100, 40);
        submitButton1.setFont(new Font("Monaco", Font.BOLD, 15));
        submitButton1.setForeground(Color.WHITE);
        submitButton1.setBackground((Color.BLACK));
        panel.add(submitButton1);

        // added 27Jun22, JH
        JButton goToButton1 = new JButton("Go To Savings");
        goToButton1.setBounds(20,320,130,25);
        goToButton1.setFont(new Font("Monaco", Font.BOLD, 12));
        goToButton1.setForeground(Color.WHITE);
        goToButton1.setBackground((Color.BLACK));
        panel.add(goToButton1);

        // added 27Jun22, JH
        JButton logoutButton1 = new JButton("Logout");
        logoutButton1.setBounds(650,320,120,25);
        logoutButton1.setFont(new Font("Monaco", Font.BOLD, 15));
        logoutButton1.setForeground(Color.WHITE);
        logoutButton1.setBackground((Color.BLACK));
        panel.add(logoutButton1);

        // actionListeners for checkingScreen
        // updated 02JUL22, JH
        submitButton1.addActionListener(e -> {
            int jCBIndex = jCB1.getSelectedIndex();
            if (jCBIndex == 0) {
                double getDeposit = Double.parseDouble(transactionInput1.getText());
                double newBalance = roundDouble1 + getDeposit;
                BigDecimal bD = new BigDecimal(newBalance).setScale(2, RoundingMode.HALF_DOWN);
                currentBalance1.setText("Balance: $" + formatter.format(bD.doubleValue()));
                roundDouble1 = newBalance;
                depositAmount1.setText("$" + formatter.format(getDeposit) + " was added to Checking!");
            }
            if (jCBIndex == 1) {
                double getWithdrawal = Double.parseDouble(transactionInput1.getText());
                double newBalance = roundDouble1 - getWithdrawal;

                //if-else statement and code added to withdraw amount entered,
                // and throw error if funds are not available in account -- added/updated 05Jul22, CO
                if(newBalance < (0 - .1)) {
                    try {
                        throw new NotValidAmountException("Not enough funds in account to withdraw. Please enter a valid amount.");
                    } catch (Exception ex) {
                        System.out.println("An error occurred: " + ex);
                    }
                    depositAmount1.setText("You do not have enough funds in account to withdraw the amount entered.");
                } else {
                    BigDecimal bD = new BigDecimal(newBalance).setScale(2, RoundingMode.HALF_DOWN);
                    currentBalance1.setText("Balance: $" + formatter.format(bD.doubleValue()));
                    roundDouble1 = newBalance;
                    depositAmount1.setText("$" + formatter.format(getWithdrawal) + " was withdrawn from Checking!");
                }

            }
        });

        goToButton1.addActionListener(e -> {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, "Panel 3");
        });

        //return panel;

        logoutButton1.addActionListener(e -> {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, "Panel 1");
            loginTimes++;
            randoInt = rando.nextInt((MAX-MIN)+1)+MIN;
            lastLog.setText("Your last login was " + randoInt + " days ago");
            calculateInterest();

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
        JLabel accountLabel2 = new JLabel("Savings Account");
        accountLabel2.setBounds(20, 5, 800, 60);
        accountLabel2.setFont(new Font("Monaco", Font.BOLD, 50));
        panel.add(accountLabel2);

        // added 27Jun22, JH
        JLabel currentBalance2 = new JLabel("Balance: $"+roundDouble2);
        currentBalance2.setBounds(435, 85, 350, 40);
        currentBalance2.setFont(new Font("Monaco", Font.BOLD, 25));
        panel.add(currentBalance2);

        // added 27Jun22, JH
        JLabel transactionType2 = new JLabel("Please choose a transaction type");
        transactionType2.setBounds(20, 90, 250, 20);
        transactionType2.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(transactionType2);

        // added 27Jun22, JH
        JLabel transactionAmount2 = new JLabel("Enter your transaction amount");
        transactionAmount2.setBounds(20, 200, 250, 20);
        transactionAmount2.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(transactionAmount2);

        if(loginTimes==0){
            calculateInterest();
            currentBalance2.setText("Balance: $"+df.format(roundDouble2));
        }

        // added 27Jun22, JH
        JLabel interestAccrued2 = new JLabel("Interest accrued since last login: $"+ df.format(intAccrued));
        interestAccrued2.setBounds(435, 120, 350, 20);
        interestAccrued2.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(interestAccrued2);

        // added 3JUL22, JH
        JLabel depositAmount2 = new JLabel("");
        depositAmount2.setBounds(20, 180, 800, 40);
        depositAmount2.setFont(new Font("Monaco", Font.BOLD, 20));
        panel.add(depositAmount2);

        // JTextFields for savingsScreen
        // added 27Jun22, JH
        JTextField transactionInput2 = new JTextField("$");
        transactionInput2.setBounds(20, 230, 230, 40);
        transactionInput2.setFont(new Font("Monaco", Font.BOLD, 15));
        panel.add(transactionInput2);

        // added KeyListener to ensure only numbers and decimals are input into JTextField
        // any that is not 0-9 or "." gives error
        // added 03JUL22, JH
        transactionInput2.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9' || ke.getKeyChar() == '.') {
                    transactionInput2.setEditable(true);
                    transactionAmount2.setText("");
                } else {
                    transactionInput2.setEditable(false);
                    transactionInput2.setText("Numbers Only! Click to Clear!");
                }
            }
        });

        // added MouseListeners 03JUL22, JH
        transactionInput2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                transactionInput2.setText("");
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        // JComboBox for savingsScreen
        // added 27Jun22, JH
        String[] transactions2 = {"Deposit","Withdraw"};
        JComboBox jCB2 = new JComboBox(transactions2);
        jCB2.setBounds(20,120,100,20);
        jCB2.setRenderer(new DefaultListCellRenderer() {
            public void paint(Graphics g) {
                setBackground(Color.green);
                setForeground(Color.black);
                super.paint(g);
            }
        });
        panel.add(jCB2);

        //JButtons for savingsScreen
        // added 27Jun22, JH
        JButton submitButton2 = new JButton("Submit");
        submitButton2.setBounds(270, 230, 100, 40);
        submitButton2.setFont(new Font("Monaco", Font.BOLD, 15));
        submitButton2.setForeground(Color.WHITE);
        submitButton2.setBackground((Color.BLACK));
        panel.add(submitButton2);

        // added 27Jun22, JH
        JButton goToButton2 = new JButton("Go To Checking");
        goToButton2.setBounds(20,320,130,25);
        goToButton2.setFont(new Font("Monaco", Font.BOLD, 12));
        goToButton2.setForeground(Color.WHITE);
        goToButton2.setBackground((Color.BLACK));
        panel.add(goToButton2);

        // added 27Jun22, JH
        JButton logoutButton2 = new JButton("Logout");
        logoutButton2.setBounds(650,320,120,25);
        logoutButton2.setFont(new Font("Monaco", Font.BOLD, 15));
        logoutButton2.setForeground(Color.WHITE);
        logoutButton2.setBackground((Color.BLACK));
        panel.add(logoutButton2);

        // actionListeners for savingsScreen
        // updated 02JUL22, JH
        submitButton2.addActionListener(e -> {
            int jCBIndex = jCB2.getSelectedIndex();
            if (jCBIndex == 0) {
                double getDeposit = Double.parseDouble(transactionInput2.getText());
                double newBalance = roundDouble2 + getDeposit;
                BigDecimal bD = new BigDecimal(newBalance).setScale(2, RoundingMode.HALF_DOWN);
                currentBalance2.setText("Balance: $" + formatter.format(bD.doubleValue()));
                roundDouble2 = newBalance;
                depositAmount2.setText("$" + formatter.format(getDeposit) + " was added to Savings!");
            }
            if (jCBIndex == 1) {
                double getWithdrawal = Double.parseDouble(transactionInput2.getText());
                double newBalance = roundDouble2 - getWithdrawal;

                //if-else statement and code added to withdraw amount entered,
                // and throw error if funds are not available in account -- added/updated 05Jul22, CO
                if(newBalance < (0 - .1)) {
                    try {
                        throw new NotValidAmountException("Not enough funds in account to withdraw. Please enter a valid amount.");
                    } catch (Exception ex) {
                        System.out.println("An error occurred: " + ex);
                    }
                    depositAmount2.setText("You do not have enough funds in account to withdraw the amount entered.");
                } else {
                    BigDecimal bD = new BigDecimal(newBalance).setScale(2, RoundingMode.HALF_DOWN);
                    currentBalance2.setText("Balance: $" + formatter.format(bD.doubleValue()));
                    roundDouble2 = newBalance;
                    depositAmount2.setText("$" + formatter.format(getWithdrawal) + " was withdrawn from Savings!");
                }
            }

        });

        goToButton2.addActionListener(e -> {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, "Panel 2");
        });

        logoutButton2.addActionListener(e -> {
            CardLayout cl = (CardLayout)(cards.getLayout());
            cl.show(cards, "Panel 1");
            loginTimes++;
            randoInt = rando.nextInt((MAX-MIN)+1)+MIN;
            calculateInterest();
            lastLog.setText("Your last login was " + randoInt + " days ago");
            currentBalance2.setText("Balance: $"+df.format(roundDouble2));
        });
        return panel;
    }
}
