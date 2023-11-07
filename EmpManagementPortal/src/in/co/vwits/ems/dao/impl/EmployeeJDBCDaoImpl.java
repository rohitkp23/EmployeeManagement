package in.co.vwits.ems.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import in.co.vwits.ems.dao.EmployeeDao;
import in.co.vwits.ems.model.Employee;

public class EmployeeJDBCDaoImpl implements EmployeeDao {
	public int save(Employee e) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db","root","rkp23");
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO tbl_employee VALUES(?,?,?)");


			pstmt.setInt(1, e.getEmpId());
			pstmt.setString(2, e.getName());
			pstmt.setDouble(3, e.getSalary());


			int noOfRowsUpdated = pstmt.executeUpdate();//firing query
			return noOfRowsUpdated;

		} catch (SQLException s) {
			s.printStackTrace();
		} 
		return 0;
	}

	public void deleteByEmpId(int empId) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db", "root", "rkp23");
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM tbl_employee WHERE empId = ?");

			pstmt.setInt(1, empId);

			int noOfRowsDeleted = pstmt.executeUpdate();
			System.out.println("Number of records deleted are " + noOfRowsDeleted);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Optional<Employee> findByEmpId(int empId) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db", "root", "rkp23");
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM tbl_employee WHERE empId = ?");

			pstmt.setInt(1, empId);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				Employee foundEmployee = new Employee();
				foundEmployee.setEmpId(rs.getInt("empId"));
				foundEmployee.setName(rs.getString("name"));
				foundEmployee.setSalary(rs.getDouble("salary"));

				return Optional.of(foundEmployee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

	public List<Employee> findAll() {

		List<Employee> employees = new ArrayList<>();

		try (	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db", "root", "rkp23");
				PreparedStatement pstmt = con.prepareStatement("SELECT * FROM tbl_employee");
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmpId(rs.getInt("empId"));
				employee.setName(rs.getString("name"));
				employee.setSalary(rs.getDouble("salary"));
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employees;
	}

	public void updateByEmpId(int empId, double newSalary) {
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_db", "root", "rkp23");
			PreparedStatement pstmt = con.prepareStatement("UPDATE tbl_employee SET salary = ? WHERE empId = ?");

			pstmt.setDouble(1, newSalary);
			pstmt.setInt(2, empId);

			int noOfRowsUpdated = pstmt.executeUpdate();
			System.out.println("Number of records updated are " + noOfRowsUpdated);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}
