package org.roster.domain;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;


import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.core.api.domain.lookup.PlanningId;

//@Entity
@PlanningEntity
public class ShiftAssignment {

    @PlanningId
    //@Id
    //@GeneratedValue
    private Long id;

    @PlanningVariable(valueRangeProviderRefs = "employeeRange")
    private Employee employee;

	private Shift shift;

	public ShiftAssignment() {
	}

	public Employee getEmployee() {
		return this.employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Shift getShift() {
		return this.shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int calculateOverlap(ShiftAssignment currentShift) {

        if (this.getId() != currentShift.getId()) {
            if(this.getEmployee().getId() == currentShift.getId() && 
               this.getShift().getTimeslot().getStartTime().toLocalDate().equals(currentShift.getShift().getTimeslot().getStartTime().toLocalDate())) {
                    return 1;
            } else {
                return -1;
            }
        }

        return 0;
    }
}
