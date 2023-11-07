package in.co.vwits.ems.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import in.co.vwits.ems.model.Employee;

public class EmployeeDaoImpl {
	
	private List<Employee> employees;

	public EmployeeDaoImpl() {
		employees = new ArrayList<Employee>();
		Employee e1 = new Employee(); 
		e1.setEmpId(1);
		e1.setName("Amit");
		e1.setSalary(95);
		

		Employee e2 = new Employee(); 
		e2.setEmpId(2);
		e2.setName("Sumit");
		e2.setSalary(75);
		

		Employee e3 = new Employee(); 
		e3.setEmpId(3);
		e3.setName("Rahul");
		e3.setSalary(85);


		employees.add(e1);
		employees.add(e2);
		employees.add(e3);
	}

	//Read all
	public List<Employee> findAll(){
		return employees;
	}

	//Create 
	public void save(Employee e)
	{
		employees.add(e);
	}

	//Search by empId
	public Optional <Employee> findByEmpId(int empId) 
	{
		for (Employee e : employees) 
		{
			if (e.getEmpId() == empId) 
			{
				return Optional.of(e);
			}
		}
		return Optional.empty(); 
	}

	//Delete by empId
	public void deleteByEmpId(int empId) 
	{
		Iterator<Employee> i = employees.iterator();
		while(i.hasNext())
		{
			Employee e = i.next();
			if(e.getEmpId() == empId)
			{
				i.remove();
			}
		}
	}
	//Update salary by empId
	public void updateByEmpId(int empId, double newSalary) 
	{
		for (Employee e : employees)
		{
			if (e.getEmpId() == empId) 
			{
				e.setSalary(newSalary);
				return;
			}
		}

	}

}
