package pcg1;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewUserPage extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JRadioButton maleButton;
    private JRadioButton femaleButton;
    private JComboBox<Integer> ageBox;
    private JRadioButton licenseYesButton;
    private JRadioButton licenseNoButton;
    private JButton saveButton;
    private JButton backButton;
    private JButton exitButton;

    public NewUserPage() {
        setTitle("Register New User");
        setSize(400, 553);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(50, 50, 100, 30);
        getContentPane().add(firstNameLabel);

        firstNameField = new JTextField();
        firstNameField.setBounds(150, 50, 150, 30);
        getContentPane().add(firstNameField);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(50, 100, 100, 30);
        getContentPane().add(lastNameLabel);

        lastNameField = new JTextField();
        lastNameField.setBounds(150, 100, 150, 30);
        getContentPane().add(lastNameField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 150, 100, 30);
        getContentPane().add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 150, 150, 30);
        getContentPane().add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 200, 100, 30);
        getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 200, 150, 30);
        getContentPane().add(passwordField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(50, 250, 100, 30);
        getContentPane().add(genderLabel);

        maleButton = new JRadioButton("Male");
        maleButton.setBounds(150, 250, 70, 30);
        getContentPane().add(maleButton);

        femaleButton = new JRadioButton("Female");
        femaleButton.setBounds(230, 250, 70, 30);
        getContentPane().add(femaleButton);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 300, 100, 30);
        getContentPane().add(ageLabel);

        Integer[] ages = new Integer[43];
        for (int i = 0; i < 43; i++) {
            ages[i] = 18 + i;
        }
        ageBox = new JComboBox<>(ages);
        ageBox.setBounds(150, 300, 150, 30);
        getContentPane().add(ageBox);

        JLabel licenseLabel = new JLabel("License:");
        licenseLabel.setBounds(50, 350, 100, 30);
        getContentPane().add(licenseLabel);

        licenseYesButton = new JRadioButton("Yes");
        licenseYesButton.setBounds(150, 350, 70, 30);
        getContentPane().add(licenseYesButton);

        licenseNoButton = new JRadioButton("No");
        licenseNoButton.setBounds(230, 350, 70, 30);
        getContentPane().add(licenseNoButton);

        ButtonGroup licenseGroup = new ButtonGroup();
        licenseGroup.add(licenseYesButton);
        licenseGroup.add(licenseNoButton);

        saveButton = new JButton("Save");
        saveButton.setBounds(50, 400, 100, 30);
        getContentPane().add(saveButton);

        backButton = new JButton("Back");
        backButton.setBounds(200, 400, 100, 30);
        getContentPane().add(backButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(125, 450, 100, 30);
        getContentPane().add(exitButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainPage().setVisible(true);
                dispose();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void saveUser() {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String gender = maleButton.isSelected() ? "Male" : "Female";
        int age = (int) ageBox.getSelectedItem();
        String license = licenseYesButton.isSelected() ? "Yes" : "No";

        try (Connection connection = DatabaseConnect.getConnection()) {
            String query = "INSERT INTO users (first_name, last_name, username, password, gender, age, license) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, username);
            statement.setString(4, password);
            statement.setString(5, gender);
            statement.setInt(6, age);
            statement.setString(7, license);

            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "User registered successfully!");
            new MainPage().setVisible(true);
            dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NewUserPage().setVisible(true);
    }
}
