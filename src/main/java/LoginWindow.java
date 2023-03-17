
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Login window GUI
 * 
 * @author Bret Craig
 * @author Cole Park
 * @version 1.2
 */
public class LoginWindow extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JTextField passwordField;
    private JButton submitButton;
    private JButton registerButton;
    private JButton analyticsButton;

    /**
     * Login Constructor
     */
    public LoginWindow() {
        super("UML Tutor Login");

        JLabel usernameLabel = new JLabel(" Username:");
        JLabel passwordLabel = new JLabel(" Password:");

        usernameField = new JTextField(20);
        usernameField.setHorizontalAlignment(JTextField.CENTER);

        passwordField = new JTextField(20);
        passwordField.setHorizontalAlignment(JTextField.CENTER);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        analyticsButton = new JButton("Analytics");
        analyticsButton.addActionListener(this);

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.setBackground(new Color(132, 169, 140));
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.setBackground(new Color(132, 169, 140));
        buttonPanel.add(submitButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(analyticsButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(300, 210);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Controls button function.
     * 
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String user = usernameField.getText();
        String pass = passwordField.getText();
        switch (e.getActionCommand()) {

            case "Submit":
                try {

                    if (Blackboard.getInstance().getDb().checkUserLogin(user, pass)) {
                        JOptionPane.showMessageDialog(null, "Login successful.");
                        Driver.login(user);
                        Blackboard.getInstance().setUser(user);
                    } else {
                        JOptionPane.showMessageDialog(null, "Login failed, username or password incorrect.");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

            case "Register":
                try {
                    if (Blackboard.getInstance().getDb().addUser(user, pass)) {
                        JOptionPane.showMessageDialog(null, "Registration successful, please log in.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Registration failed, username already taken");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;


            case "Analytics":
                try {
                    Driver.analytics();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;

        }
    }
}