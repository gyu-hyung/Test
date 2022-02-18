package com.example.project04_lastproject.employees;

public class EmployeeVO {

	private int employee_id ;
	private String name ,department_name ,  city ,  country_name ;
	public int getEmployee_id() {
		return  employee_id ;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment_name() {
		return "( dept : " + department_name + ")";
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	
	
	
	
	
}
