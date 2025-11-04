package com.fdmgroup.pojos;

import java.util.List;

public class Manager {
	 	private int id;
	    private String name;
	    private int age;
	    private int salary;
	    private List<Staff> staffs;
	    
	    public Manager() {}

		public Manager(int id, String name, int age, int salary, List<Staff> staffs) {
			this.id = id;
			this.name = name;
			this.age = age;
			this.salary = salary;
			this.staffs = staffs;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public double getSalary() {
			return salary;
		}

		public void setSalary(int salary) {
			this.salary = salary;
		}

		public List<Staff> getStaffs() {
			return staffs;
		}

		public void setStaffs(List<Staff> staffs) {
			this.staffs = staffs;
		}
	    
}
