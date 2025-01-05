package pcg1;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MenuPage extends JFrame {
    private JComboBox<String> carBrandBox;
    private JComboBox<String> carModelBox;
    private JSpinner rentalDaysSpinner;
    private JComboBox<String> fuelTypeBox;
    private JTextField totalCostField;
    private JButton rentButton;
    private JButton exitButton;
    private int userId;

    public MenuPage(int userId) {
        this.userId = userId;
        setTitle("Rent a Car - Menu");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel carBrandLabel = new JLabel("Car Brand:");
        carBrandLabel.setBounds(50, 50, 100, 30);
        add(carBrandLabel);

        carBrandBox = new JComboBox<>(new String[]{"Mercedes", "Ford", "Toyota", "Honda", "Audi"});
        carBrandBox.setBounds(150, 50, 150, 30);
        carBrandBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCarModels();
            }
        });
        add(carBrandBox);

        JLabel carModelLabel = new JLabel("Car Model:");
        carModelLabel.setBounds(50, 100, 100, 30);
        add(carModelLabel);

        carModelBox = new JComboBox<>();
        carModelBox.setBounds(150, 100, 150, 30);
        add(carModelBox);

        JLabel rentalDaysLabel = new JLabel("Rental Days:");
        rentalDaysLabel.setBounds(50, 150, 100, 30);
        add(rentalDaysLabel);

        rentalDaysSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));
        rentalDaysSpinner.setBounds(150, 150, 150, 30);
        rentalDaysSpinner.addChangeListener(e -> updateTotalCost());
        add(rentalDaysSpinner);

        JLabel fuelTypeLabel = new JLabel("Fuel Type:");
        fuelTypeLabel.setBounds(50, 200, 100, 30);
        add(fuelTypeLabel);

        fuelTypeBox = new JComboBox<>(new String[]{"Petrol", "Diesel"});
        fuelTypeBox.setBounds(150, 200, 150, 30);
        add(fuelTypeBox);

        JLabel totalCostLabel = new JLabel("Total Cost:");
        totalCostLabel.setBounds(50, 250, 100, 30);
        add(totalCostLabel);

        totalCostField = new JTextField();
        totalCostField.setBounds(150, 250, 150, 30);
        totalCostField.setEditable(false);
        add(totalCostField);

        rentButton = new JButton("Rent");
        rentButton.setBounds(50, 300, 100, 30);
        add(rentButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(200, 300, 100, 30);
        add(exitButton);

        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rentCar();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        updateCarModels();
        updateTotalCost();
    }

    private void updateCarModels() {
        carModelBox.removeAllItems();
        String selectedBrand = (String) carBrandBox.getSelectedItem();
        if (selectedBrand != null) {
            switch (selectedBrand) {
                case "Mercedes":
                    carModelBox.addItem("A-Class");
                    carModelBox.addItem("C-Class");
                    carModelBox.addItem("E-Class");
                    break;
                case "Ford":
                    carModelBox.addItem("Fiesta");
                    carModelBox.addItem("Focus");
                    carModelBox.addItem("Mustang");
                    break;
                case "Toyota":
                    carModelBox.addItem("Corolla");
                    carModelBox.addItem("CH-R");
                    carModelBox.addItem("Hilux");
                    break;
                case "Honda":
                    carModelBox.addItem("Civic");
                    carModelBox.addItem("Accord");
                    carModelBox.addItem("CR-V");
                    break;
                case "Audi":
                    carModelBox.addItem("A3");
                    carModelBox.addItem("A4");
                    carModelBox.addItem("Q5");
                    break;
            }
        }
    }

    private void updateTotalCost() {
        int days = (int) rentalDaysSpinner.getValue();
        double totalCost = days * 100;
        totalCostField.setText(String.valueOf(totalCost));
    }

    private void rentCar() {
        String carBrand = (String) carBrandBox.getSelectedItem();
        String carModel = (String) carModelBox.getSelectedItem();
        int rentalDays = (int) rentalDaysSpinner.getValue();
        String fuelType = (String) fuelTypeBox.getSelectedItem();
        double totalCost = Double.parseDouble(totalCostField.getText());

        try (Connection connection = DatabaseConnect.getConnection()) {
            String query = "INSERT INTO rentals (user_id, car_brand, car_model, rental_days, fuel_type, total_cost) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            statement.setString(2, carBrand);
            statement.setString(3, carModel);
            statement.setInt(4, rentalDays);
            statement.setString(5, fuelType);
            statement.setDouble(6, totalCost);

            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Car rented successfully!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MenuPage(1).setVisible(true); // Here we are passing 1 as a dummy userId
    }
}


