package org.roster.domain;

import java.time.LocalDateTime;

public class TimeSlot {
    
    private LocalDateTime startTime;
	private LocalDateTime endTime;

	public TimeSlot() {
	}

	public LocalDateTime getStartTime() {
		return this.startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return this.endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public TimeSlot(LocalDateTime startTime, LocalDateTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
}
