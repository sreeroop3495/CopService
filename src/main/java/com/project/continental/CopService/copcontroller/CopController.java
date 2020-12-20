package com.project.continental.CopService.copcontroller;

import com.project.continental.CopService.model.*;
import com.project.continental.CopService.repository.ViolationRecordRepository;
import com.project.continental.CopService.repository.ViolationRepository;
import com.project.continental.CopService.service.ViolationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@EnableScheduling
public class CopController {

    private static final Logger logger= LoggerFactory.getLogger(CopController.class);

    /**
     * This controller class is used to expose endpoints for  accessing violation related data and payment against violations
     */
    @Autowired
    ViolationService violationService;

    @Autowired
    ViolationRepository violationRepository;

    @Autowired
    ViolationRecordRepository violationRecordRepository;

   //http://localhost:9000/police/allViolations
    @GetMapping("/allViolations")
    /**
     * Endpoint to get the violations list
     */
    public List<Violations> getAllViolations() {
        return violationRepository.findAll();
    }

/*
http://localhost:9000/police/addViolation
 {
 "section":"100A",
 "description":"Pollution certificate",
 "penalty":1000
 }

 */
    @PostMapping("/addViolation")
    /**
     * Endpoint to add  violations to database
     */
    public void addViolation(@RequestBody Violations violations) {

        violationRepository.save(violations);
    }

    //http://localhost:9000/police/PenaltyByViolation/120A
    @GetMapping("/PenaltyByViolation/{section}")
    public int getPenaltyByViolation(@PathVariable("section") String section) {
        /**
         * Endpoint to payment to be done against each penalty
         */
        return violationRepository.getPenaltyBySection(section);
    }


    //http://localhost:9000/police/getViolationByRegNo/KL58N500
    @GetMapping("getViolationByRegNo/{regno}")
    public List<ViolationRecord> getViolationRecord(@PathVariable("regno") String regno) {
        /**
         * Endpoint to get the violations record of a particular vehicle
         */
        return violationService.getViolationRecordByRegNo(regno);

    }

//http://localhost:9000/police/getViolationByRegNoAndSection/KL57M7570/120A
    @GetMapping("getViolationByRegNoAndSection/{regno}/{section}")
    public ViolationRecord getViolationRecord(@PathVariable("regno") String regno, @PathVariable("section") String section) {
        /**
         * Endpoint to get the violations list of a particular vehicle in a particular violation
         */
        return violationRecordRepository.getViolationRecordByRegAndSection(regno, section);

    }

    //http://localhost:9000/police/getPenaltyBySection/120A
    @GetMapping("/getPenaltyBySection/{section}")
    public Violations getPenalty(@PathVariable("section") String section){
        /**
         * Endpoint to get the section and payment to be done for a particular section
         */
        return violationService.getViolationBySection(section);
    }

    /*
        http://localhost:9000/police/addViolationRecord
        {
        "regno": "KL11B2331",
        "section": "120A"
    }
     */
    @PostMapping("/addViolationRecord")
    public void addviolationRecord(@RequestBody ViolationRecord violationRecord) {
        /**
         * Used to add violation record to a particular vehicle
         */
     violationService.addViolationRecord(violationRecord);

    }

    //http://localhost:9000/police/getAllViolations
    @GetMapping("/getAllViolations")
    public List<ViolationRecord> getViolationsByReg() {
        /**
         * Endpoint to get all the violation records
         */
      return violationService.getViolationRecord();
    }

/*

    http://localhost:9000/police/pay

  {
        "regno": "KL57M7570",
        "section": "120A",
        "amount": 500
    }
 */


    @PostMapping("/pay")
    public void PayPenalty(@RequestBody Pay pay) {
        /**
         * Endpoint to pay the penalty,accordingly database is updated
         */
     violationService.PayPenalty(pay);

    }

//http://localhost:9000/police/getReport/KL57M7570
    @GetMapping("/getReport/{regno}")
    public Report getReport(@PathVariable("regno") String regno) {
        /**
         * Endpoint to gnerate a report of a particular vehicle with section penalty details
         */
    return  violationService.getReport(regno);
}

}
