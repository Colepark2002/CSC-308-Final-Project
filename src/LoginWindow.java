import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Login window GUI
 * @author Bret Craig
 * @version 1.1
 */
public class LoginWindow extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JTextField passwordField;
    private JButton submitButton;
    private JButton registerButton;

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

        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.setBackground(new Color(132, 169, 140));
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.setBackground(new Color(132, 169, 140));
        buttonPanel.add(submitButton);
        buttonPanel.add(registerButton);

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(300, 175);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Controls button submit and register button function.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {

            case "Submit":
                // To get username: usernameField.getText();
                // To get password: passwordField.getText();
                if (true) {
                    JOptionPane.showMessageDialog(null, "Login successful.");
                    Driver.login();
                } else {
                    JOptionPane.showMessageDialog(null, "Login failed, username or password incorrect.");
                }

                break;

            case "Register":
                if (true) {
                    JOptionPane.showMessageDialog(null, "Registration successful, please log in.");
                }
                else {
                    JOptionPane.showMessageDialog(null, "Registration failed, password already taken");
                }
                break;

        }
    }
}