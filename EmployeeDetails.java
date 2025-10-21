
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EmployeeDetails extends JFrame implements ActionListener {
private List<Employee> employees;
private JTextField nameField, idField, searchField;
private JPasswordField passwordField;
private JButton loginButton, viewButton, createButton, editButton, deleteButton, saveButton,
searchButton;
public EmployeeDetails() {
setTitle("Employee Management System");
setSize(500, 400);
setDefaultCloseOperation(EXIT_ON_CLOSE);
setLayout(new BorderLayout());

employees = new ArrayList<>();

JPanel topPanel = new JPanel(new GridLayout(4, 2));
JLabel nameLabel = new JLabel("Name:");
nameField = new JTextField();
JLabel idLabel = new JLabel("ID:");
idField = new JTextField();
JLabel passwordLabel = new JLabel("Password:");
passwordField = new JPasswordField();
JLabel searchLabel = new JLabel("Search:");
searchField = new JTextField();
loginButton = new JButton("Login");

viewButton = new JButton("View all employees");
createButton = new JButton("Create employee");
editButton = new JButton("Edit employee");
deleteButton = new JButton("Delete employee");
saveButton = new JButton("Save data");
searchButton = new JButton("Search");

topPanel.add(nameLabel);
topPanel.add(nameField);
topPanel.add(idLabel);
topPanel.add(idField);
topPanel.add(passwordLabel);
topPanel.add(passwordField);
topPanel.add(searchLabel);
topPanel.add(searchField);

JPanel buttonPanel = new JPanel(new GridLayout(1, 6));
buttonPanel.add(loginButton);
buttonPanel.add(viewButton);
buttonPanel.add(createButton);
buttonPanel.add(editButton);
buttonPanel.add(deleteButton);
buttonPanel.add(saveButton);
buttonPanel.add(searchButton);

add(topPanel, BorderLayout.NORTH);
add(buttonPanel, BorderLayout.SOUTH);

// Registering button action listeners
loginButton.addActionListener(this);
viewButton.addActionListener(this);
createButton.addActionListener(this);

editButton.addActionListener(this);
deleteButton.addActionListener(this);
saveButton.addActionListener(this);
searchButton.addActionListener(this);

setVisible(true);
}

@Override
public void actionPerformed(ActionEvent e) {
if (e.getSource() == loginButton) {
String name = nameField.getText();
String id = idField.getText();
String password = new String(passwordField.getPassword());

if (authenticate(name, id, password)) {
JOptionPane.showMessageDialog(this, "Login successful!");

// Enable other buttons after successful login
viewButton.setEnabled(true);
createButton.setEnabled(true);
editButton.setEnabled(true);
deleteButton.setEnabled(true);
saveButton.setEnabled(true);
} else {
JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
}
} else if (e.getSource() == viewButton) {
displayEmployees();
} else if (e.getSource() == createButton) {
createEmployee();
} else if (e.getSource() == editButton) {

editEmployee();
} else if (e.getSource() == deleteButton) {
deleteEmployee();
} else if (e.getSource() == saveButton) {
saveData();
} else if (e.getSource() == searchButton) {
searchEmployee();
}
}

private boolean authenticate(String name, String id, String password) {
// Dummy authentication logic (Replace with your own logic)
return name.equals("admin") && id.equals("admin") && password.equals("admin");
}

private void displayEmployees() {
StringBuilder sb = new StringBuilder();
for (Employee employee : employees) {
sb.append("Name: ").append(employee.getName()).append(", ID:
").append(employee.getId()).append("\n");
}
JOptionPane.showMessageDialog(this, sb.toString(), "All Employees",
JOptionPane.INFORMATION_MESSAGE);
}

private void createEmployee() {
String name = JOptionPane.showInputDialog(this, "Enter name:");
String id = JOptionPane.showInputDialog(this, "Enter ID:");
if (name != null && id != null) {
employees.add(new Employee(name, id));
JOptionPane.showMessageDialog(this, "Employee created successfully!");
} else {
JOptionPane.showMessageDialog(this, "Invalid input. Please try again.");

}
}

private void editEmployee() {
String id = JOptionPane.showInputDialog(this, "Enter ID of employee to edit:");
if (id != null) {
for (Employee employee : employees) {
if (employee.getId().equals(id)) {
String newName = JOptionPane.showInputDialog(this, "Enter new name:");
if (newName != null) {
employee.setName(newName);
JOptionPane.showMessageDialog(this, "Employee details updated successfully!");
} else {
JOptionPane.showMessageDialog(this, "Invalid input. Please try again.");
}
return;
}
}
JOptionPane.showMessageDialog(this, "Employee not found!");
}
}

private void deleteEmployee() {
String id = JOptionPane.showInputDialog(this, "Enter ID of employee to delete:");
if (id != null) {
for (Employee employee : employees) {
if (employee.getId().equals(id)) {
int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this
employee?", "Confirmation", JOptionPane.YES_NO_OPTION);
if (option == JOptionPane.YES_OPTION) {
employees.remove(employee);
JOptionPane.showMessageDialog(this, "Employee deleted successfully!");
}

return;
}
}
JOptionPane.showMessageDialog(this, "Employee not found!");
}
}

private void saveData() {
// Dummy method to simulate data saving
JOptionPane.showMessageDialog(this, "Data saved successfully!");
}

private void searchEmployee() {
String searchText = searchField.getText().trim();
if (!searchText.isEmpty()) {
List<Employee> searchResults = new ArrayList<>();
for (Employee employee : employees) {
if (employee.getName().contains(searchText) || employee.getId().contains(searchText)) {
searchResults.add(employee);
}
}
if (!searchResults.isEmpty()) {
StringBuilder sb = new StringBuilder();
for (Employee employee : searchResults) {
sb.append("Name: ").append(employee.getName()).append(", ID:
").append(employee.getId()).append("\n");
}
JOptionPane.showMessageDialog(this, sb.toString(), "Search Results",
JOptionPane.INFORMATION_MESSAGE);
} else {
JOptionPane.showMessageDialog(this, "No matching employees found!", "Search Results",
JOptionPane.INFORMATION_MESSAGE);
}

} else {
JOptionPane.showMessageDialog(this, "Please enter a search term!", "Search",
JOptionPane.WARNING_MESSAGE);
}
}

public static void main(String[] args) {
SwingUtilities.invokeLater(EmployeeDetails::new);
}

private static class Employee {
private String name;
private String id;

public Employee(String name, String id) {
this.name = name;
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;

}

@Override
public String toString() {
return "Employee{" +
"name='" + name + '\'' +
", id='" + id + '\'' +
'}';
}
}
}