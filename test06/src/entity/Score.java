package entity;

import util.Grade;

public class Score {

	// private String name;
	// private String dep;
	// private String pro;
	private Employee emp;
	// private Department dep;
	private Project pro;
	private int id;
	private Integer value;
	private Grade grade;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	// public Department getDep() {
	// return dep;
	// }
	//
	// public void setDep(Department dep) {
	// this.dep = dep;
	// }

	public Project getPro() {
		return pro;
	}

	public void setPro(Project pro) {
		this.pro = pro;
	}

	// public String getName() {
	// return name;
	// }
	//
	// public void setName(String name) {
	// this.name = name;
	// }
	//
	// public String getDep() {
	// return dep;
	// }
	//
	// public void setDep(String dep) {
	// this.dep = dep;
	// }
	//
	// public String getPro() {
	// return pro;
	// }
	//
	// public void setPro(String pro) {
	// this.pro = pro;
	// }

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

}
