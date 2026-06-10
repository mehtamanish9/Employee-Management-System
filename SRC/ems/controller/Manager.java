package ems.controller;

import ems.model.Employee;
import java.io.*;
import java.util.*;

public class Manager {
    private final Map<Integer, Employee> employees = new HashMap<>();
    private int nextId = 1;
    private static final String DATA_FILE = "employees.dat";

    public Manager() {
        loadData();
    }

    public List<Employee> getAllEmployees() {
        List<Employee> list = new ArrayList<>(employees.values());
        list.sort(Comparator.comparingInt(Employee::getId));
        return list;
    }

    public Employee getEmployee(int id) {
        return employees.get(id);
    }

    public void addEmployee(String name, String department, double salary) {
        Employee emp = new Employee(nextId++, name, department, salary);
        employees.put(emp.getId(), emp);
    }

    public boolean updateEmployee(int id, String newName, String newDept, double newSalary) {
        Employee emp = employees.get(id);
        if (emp != null) {
            emp.setName(newName);
            emp.setDepartment(newDept);
            emp.setSalary(newSalary);
            return true;
        }
        return false;
    }

    public boolean deleteEmployee(int id) {
        return employees.remove(id) != null;
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(employees);
            oos.writeInt(nextId);
        } catch (IOException e) {
            System.err.println("Error saving employee data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) return;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Map<Integer, Employee> loaded = (Map<Integer, Employee>) ois.readObject();
            employees.clear();
            employees.putAll(loaded);
            nextId = ois.readInt();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading employee data: " + e.getMessage());
        }
    }
}
