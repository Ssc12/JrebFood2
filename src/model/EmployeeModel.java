package model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import connect.Connect;

public class EmployeeModel {
	
	private String tableName="employees";
	private Connect con = Connect.getConnection();

	private Integer id;
	private Integer roleId;
	private String name;
	private Date DOB;
	private String email;
	private String password;
	private String status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Vector<EmployeeModel> viewAll(Integer roleId){
		Vector<EmployeeModel> data = new Vector<>();
		String query = String.format("SELECT employeeId,employeeName,employeeDOB,employeeEmail,employeeStatus FROM %s WHERE roleId=?", tableName);
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet result = null;
		try {
			ps.setInt(1, roleId);
			result = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			while(result.next()) {
				Integer employeeId = result.getInt("employeeId");
				String employeeName = result.getString("employeeName");
				Date employeeDOB = result.getDate("employeeDOB");
				String employeeEmail = result.getString("employeeEmail");
				String employeeStatus = result.getString("employeeStatus");
				
				EmployeeModel employee = new EmployeeModel();
				employee.setId(employeeId);
				employee.setName(employeeName);
				employee.setDOB(employeeDOB);
				employee.setEmail(employeeEmail);
				employee.setStatus(employeeStatus);
				data.add(employee);
			}
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean createEmployee() {
		String query = String.format("INSERT INTO %s VALUES(null,?,?,?,?,?,?,null,null)", tableName);
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setInt(1, roleId);
			ps.setString(2, name);
			ps.setDate(3, DOB);
			ps.setString(4, email);
			ps.setString(5, password);
			ps.setString(6, status);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
