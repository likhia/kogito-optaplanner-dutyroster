package org.roster.domain;

public class Employee {

	private Long id;
	private String name;
	private Duty duty;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Duty getDuty() {
		return this.duty;
	}

	public void setDuty(Duty duty) {
		this.duty = duty;
	}

	public Employee(String name, Duty duty) {
		this.name = name;
		this.duty = duty;
	}

}
