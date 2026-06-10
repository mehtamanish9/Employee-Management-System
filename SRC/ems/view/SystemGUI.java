package ems.view;

import ems.model.Employee;
import ems.controller.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SystemGUI extends JFrame {
    private final Manager manager = new Manager();
    private final TableModel tableModel = new TableModel(manager);
    private final JTable employeeTable = new JTable(tableModel);

    private final JTextField idField = new JTextField(5);
    private final JTextField nameField = new JTextField(15);
    private final JTextField deptField = new JTextField(15);
    private final JTextField salaryField = new JTextField(10);

    public SystemGUI() {
        setTitle("Employee Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        buildUI();
        tableModel.fireTableDataChanged();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                manager.saveData();
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void buildUI() {
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));

        inputPanel.add(new JLabel("ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Department:"));
        inputPanel.add(deptField);
        inputPanel.add(new JLabel("Salary:"));
        inputPanel.add(salaryField);

        JScrollPane scrollPane = new JScrollPane(employeeTable);
        employeeTable.setFillsViewportHeight(true);
        employeeTable.setRowHeight(25);
        employeeTable.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        JButton addButton = new JButton("Add Employee");
        JButton updateButton = new JButton("Update Employee");
        JButton deleteButton = new JButton("Delete Employee");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addEmployee());
        updateButton.addActionListener(e -> updateEmployee());
        deleteButton.addActionListener(e -> deleteEmployee());

        employeeTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && employeeTable.getSelectedRow() != -1) {
                int selectedRow = employeeTable.getSelectedRow();
                int id = (int) tableModel.getValueAt(selectedRow, 0);
                Employee emp = manager.getEmployee(id);
                if (emp != null) {
                    idField.setText(String.valueOf(emp.getId()));
                    nameField.setText(emp.getName());
                    deptField.setText(emp.getDepartment());
                    salaryField.setText(String.valueOf(emp.getSalary()));
                }
            }
        });
    }

    private double parseSalary(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private int parseId(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void addEmployee() {
        String name = nameField.getText().trim();
        String dept = deptField.getText().trim();
        double salary = parseSalary(salaryField.getText());

        if (name.isEmpty() || dept.isEmpty() || salary <= 0) {
            JOptionPane.showMessageDialog(this, "Enter valid Name, Department, and Salary.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        manager.addEmployee(name, dept, salary);
        tableModel.fireTableDataChanged();
        clearFields();
    }

    private void updateEmployee() {
        int id = parseId(idField.getText());
        String name = nameField.getText().trim();
        String dept = deptField.getText().trim();
        double salary = parseSalary(salaryField.getText());

        if (id <= 0 || name.isEmpty() || dept.isEmpty() || salary <= 0) {
            JOptionPane.showMessageDialog(this, "Provide valid ID and all fields for update.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (manager.updateEmployee(id, name, dept, salary)) {
            tableModel.fireTableDataChanged();
            JOptionPane.showMessageDialog(this, "Employee ID " + id + " updated successfully.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Employee ID " + id + " not found.",
                    "Update Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteEmployee() {
        int id = parseId(idField.getText());
        if (id <= 0) {
            JOptionPane.showMessageDialog(this, "Enter a valid Employee ID to delete.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete Employee ID " + id + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (manager.deleteEmployee(id)) {
                tableModel.fireTableDataChanged();
                JOptionPane.showMessageDialog(this, "Employee deleted successfully.",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Employee ID not found.",
                        "Delete Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        deptField.setText("");
        salaryField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SystemGUI::new);
    }
}
  
