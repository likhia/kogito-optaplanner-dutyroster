package org.roster.solver;

import org.roster.domain.*;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.api.score.stream.Constraint;
import org.optaplanner.core.api.score.stream.ConstraintCollectors;
import org.optaplanner.core.api.score.stream.ConstraintFactory;
import org.optaplanner.core.api.score.stream.ConstraintProvider;

public class DutyRosterConstraintProvider implements ConstraintProvider {

    
    public static final int HARD_LEVELS_SIZE = 1;
    public static final int SOFT_LEVELS_SIZE = 5;


    @Override
    public Constraint[] defineConstraints(ConstraintFactory constraintFactory) {
        return new Constraint[] { 
            oneShiftPerDay(constraintFactory),
            shiftReqiredDutyMet(constraintFactory),
            balanceEmployeesShiftNumber(constraintFactory),
            dayOffRequestRule(constraintFactory)
        };
    }

    public Constraint balanceEmployeesShiftNumber(ConstraintFactory constraintFactory) {
        return constraintFactory.fromUnfiltered(ShiftAssignment.class)
                                .groupBy(ShiftAssignment::getEmployee, ConstraintCollectors.count())
                                .penalize("balanceEmpShiftNo", HardSoftScore.ONE_SOFT, (employee, count) -> count * count);
    }

    public Constraint dayOffRequestRule(ConstraintFactory constraintFactory) {
        return constraintFactory.fromUnfiltered(ShiftAssignment.class)
                .filter(shiftAssignment -> shiftAssignment.getShift() != null && shiftAssignment.getEmployee() != null)
                .join(DayOffRequest.class)
                .filter((shiftAssignment, dayOffRequest) -> shiftAssignment.getShift().getTimeslot().equals(dayOffRequest.getOffShift()) && shiftAssignment.getEmployee().equals(dayOffRequest.getEmployee()))
                .penalize("Shift on an off-day", HardSoftScore.ONE_HARD);
    }
    
    public Constraint oneShiftPerDay(ConstraintFactory constraintFactory) {
        return constraintFactory.fromUnfiltered(ShiftAssignment.class)
                .filter(shiftAssignment -> shiftAssignment.getShift() != null && shiftAssignment.getEmployee() != null)
                .join(ShiftAssignment.class)  
                .filter((shiftAssignment, otherShiftAssignment) -> shiftAssignment.calculateOverlap(otherShiftAssignment) > 0)
                .penalize("oneShiftPerDay", HardSoftScore.ONE_HARD);
    }

    public Constraint shiftReqiredDutyMet(ConstraintFactory constraintFactory) {
        return constraintFactory.fromUnfiltered(ShiftAssignment.class)
                .filter(shiftAssignment -> shiftAssignment.getEmployee() != null)
                .filter(shiftAssignment -> !shiftAssignment.getEmployee().getDuty().getName().equals(shiftAssignment.getShift().getRequiredDuty().getName()))
                .penalize("shiftReqiredDutyMet", HardSoftScore.ONE_HARD);
    }


}
