package in.co.vwits.ems.dao;

import java.util.List;
import java.util.Optional;

import in.co.vwits.ems.model.Employee;

public interface EmployeeDao {
	
	public int save(Employee e);
	public void deleteByEmpId(int empId);
	public Optional<Employee> findByEmpId(int empId);
	public List<Employee> findAll();
	public void updateByEmpId(int empId, double newSalary);

}
