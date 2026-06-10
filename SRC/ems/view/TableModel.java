package ems.view;

import ems.model.Employee;
import ems.controller.Manager;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    private final Manager manager;
    private List<Employee> data;
    private final String[] columnNames = { "ID", "Name", "Department", "Salary" };

    public TableModel(Manager manager) {
        this.manager = manager;
        this.data = manager.getAllEmployees();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Employee emp = data.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> emp.getId();
            case 1 -> emp.getName();
            case 2 -> emp.getDepartment();
            case 3 -> String.format("₹%,.2f", emp.getSalary());
            default -> null;
        };
    }

    @Override
    public void fireTableDataChanged() {
        this.data = manager.getAllEmployees();
        super.fireTableDataChanged();
    }
}
