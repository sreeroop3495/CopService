package com.project.continental.CopService.model;

import java.util.List;

public class Report {
    /**
     * This class is for creating report
     */
    VehicleDetails vehicleDetails;
    List<ViolationRecord> violationRecord;
    List<Violations> violations;

    public Report(VehicleDetails vehicleDetails, List<ViolationRecord> violationRecord, List<Violations> violations) {
        this.vehicleDetails = vehicleDetails;
        this.violationRecord = violationRecord;
        this.violations = violations;
    }

    public  Report(){}

    public VehicleDetails getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(VehicleDetails vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }

    public List<ViolationRecord> getViolationRecord() {
        return violationRecord;
    }

    public void setViolationRecord(List<ViolationRecord> violationRecord) {
        this.violationRecord = violationRecord;
    }

    public List<Violations> getViolations() {
        return violations;
    }

    public void setViolations(List<Violations> violations) {
        this.violations = violations;
    }
}
