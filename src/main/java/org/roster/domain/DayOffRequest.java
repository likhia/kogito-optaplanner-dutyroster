package org.roster.domain;

public class DayOffRequest {
    private TimeSlot offShift;
	private Employee employee;

	public DayOffRequest() {
	}

	public TimeSlot getOffShift() {
		return this.offShift;
	}

	public void setOffShift(TimeSlot offShift) {
		this.offShift = offShift;
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public DayOffRequest(TimeSlot offShift, Employee employee) {
		this.offShift = offShift;
		this.employee = employee;
	}

	public Boolean isOffDay(Employee e) {
		if(this.employee == null) {
			return false;
		}

		if(this.employee.getId().equals(e.getId())) {
			return  true;
		}

		return false;
	}
}
