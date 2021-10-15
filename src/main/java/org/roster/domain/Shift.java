package org.roster.domain;

public class Shift {
    
    private Duty requiredDuty;
	private TimeSlot timeslot;

	public Shift() {
	}

	public Duty getRequiredDuty() {
		return this.requiredDuty;
	}

	public void setRequiredDuty(Duty requiredDuty) {
		this.requiredDuty = requiredDuty;
	}

	public TimeSlot getTimeslot() {
		return this.timeslot;
	}

	public void setTimeslot(TimeSlot timeslot) {
		this.timeslot = timeslot;
	}

	public Shift(Duty requiredDuty, TimeSlot timeslot) {
		this.requiredDuty = requiredDuty;
		this.timeslot = timeslot;
	}

}
