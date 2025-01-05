# Car Rental System Application

## General Description
This project is designed as a car rental system. Users registered in the database can log in to the system. If they are not registered, they can log in after registration (with personal information) and perform car rental transactions, including specifying car brand, model, rental days, fuel type, and total cost.

## Application Components and Logic

### 1. **MainPage**
- **Purpose**: The initial screen of the application.
- **Functionality**:
  - Allows users to log in if they are already registered.
  - Provides an option for new users to navigate to the registration page.
  - **Login button**: Logs the user into the application.
  - **Register button**: Directs the user to the registration page.
  - **Exit button**: Exits the application.

---

### 2. **NewUserPage**
- **Purpose**: Screen for new user registration.
- **Functionality**:
  - Allows new users to create an account by providing necessary details.
  - Stores user information in the database for future logins.
  - **Save button**: Saves the user's information in the database and allows them to enter the application.
  - **Back button**: Directs the user to the previous screen.
  - **Exit button**: Exits the application.

---

### 3. **MenuPage**
- **Purpose**: The main menu for performing car rental transactions.
- **Functionality**:
  - Users can choose a car brand from the available options (e.g., Mercedes, Ford, Toyota, Honda, Audi).
  - Depending on the selected brand, users can select a car model (e.g., if Ford is selected, models like Fiesta, Focus, Mustang can be chosen).
  - Users can select the type of fuel (petrol or diesel).
  - Users specify the number of rental days.
  - The system calculates and displays the total rental cost based on a rate of **100 euros per day**.
  - **Rent button**: Rents the car and saves the information in the database.
  - **Exit button**: Exits the application.

---

### 4. **DatabaseConnect**
- **Purpose**: Helper class for managing database connections.
- **Functionality**:
  - Manages all database operations such as user registration, login verification, and storing rental transactions.
  - Ensures communication between the application and the database.

---

## Features
- User-friendly interface for registration, login, and rental operations.
- Secure and efficient database connection for managing user and transaction data.
- Dynamic car selection based on brand and model.
- Total cost calculation based on rental duration.

---

## Setup and Usage
1. Clone the repository to your local machine.
2. Set up the database using the provided schema.
3. Run the application on a local server or your preferred environment.
4. Access the application to register, log in, and rent a car.

---

## License
This project is licensed under the [MIT License](LICENSE).

---

By organizing the system into these components, the application ensures a user-friendly experience while maintaining a robust backend for managing user data and rental transactions.
