package ua.hw.jdbc.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO {
    private final DatabaseConnector connector;
    public EmployeeDAO(DatabaseConnector connector) { this.connector = connector; }

    public Employee add(Employee e) throws SQLException {
        String sql = "INSERT INTO employees(name, age, position, salary) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection c = connector.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setInt(2, e.getAge());
            ps.setString(3, e.getPosition());
            ps.setFloat(4, e.getSalary());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) e.setId(rs.getInt("id"));
            }
        }
        return e;
    }

    public boolean update(Employee e) throws SQLException {
        if (e.getId() == null) throw new IllegalArgumentException("id is null");
        String sql = "UPDATE employees SET name=?, age=?, position=?, salary=? WHERE id=?";
        try (Connection c = connector.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, e.getName());
            ps.setInt(2, e.getAge());
            ps.setString(3, e.getPosition());
            ps.setFloat(4, e.getSalary());
            ps.setInt(5, e.getId());
            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM employees WHERE id=?";
        try (Connection c = connector.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    public Optional<Employee> findById(int id) throws SQLException {
        String sql = "SELECT id, name, age, position, salary FROM employees WHERE id=?";
        try (Connection c = connector.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(map(rs));
            }
        }
        return Optional.empty();
    }

    public List<Employee> findAll() throws SQLException {
        String sql = "SELECT id, name, age, position, salary FROM employees ORDER BY id";
        List<Employee> list = new ArrayList<>();
        try (Connection c = connector.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    private Employee map(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("age"),
                rs.getString("position"),
                rs.getFloat("salary")
        );
    }
}