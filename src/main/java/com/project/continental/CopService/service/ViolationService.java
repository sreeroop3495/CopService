package com.project.continental.CopService.service;

import com.project.continental.CopService.copcontroller.CopController;
import com.project.continental.CopService.model.*;
import com.project.continental.CopService.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
public class ViolationService {
    private static final Logger logger= LoggerFactory.getLogger(ViolationService.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ViolationService violationService;

    @Autowired
    ViolationRecordRepository violationRecordRepository;

    @Autowired
    ViolationRepository violationRepository;

    @Value("${owner.url}")
    String ownerurl;

    public int getPenaltyByViolation(String section){
        logger.info("Fetching penalty amount from db");
     return violationRepository.getPenaltyBySection(section);
    }

    public List<ViolationRecord> getViolationRecordByRegNo(String regno){
        logger.info("Fetching violation record  from db");
        return violationRecordRepository.getViolationRecordByReg(regno);
    }

    public Violations getViolationBySection(String section){
        logger.info("Fetching violation from db");
        return violationRepository.findViolationBySection(section);
    }

    public Report getReport(String regno){
        logger.info("Fetching Report from db and by calling owner service");
        Report report=new Report();

        VehicleDetails vehicleDetails = new VehicleDetails();
        List<ViolationRecord> violationRecords = new ArrayList<>();
        List<Violations> violations=new ArrayList<>();

        vehicleDetails = restTemplate.getForObject("http://localhost:8083/api/v1/getVehicleDetails/" + regno, VehicleDetails.class);
        violationRecords = violationService.getViolationRecordByRegNo(regno);

        for(ViolationRecord violationRecord:violationRecords){
            String section=violationRecord.getSection();
            violations.add(violationService.getViolationBySection(section));
        }

        report.setVehicleDetails(vehicleDetails);
        report.setViolationRecord(violationRecords);
        report.setViolations(violations);

        return  report;
    }


    @Transactional
    public void PayPenalty(Pay pay){
        logger.info("Making payment, checking amount and section in respective db");
        ViolationRecord violationRecord = new ViolationRecord();
        try {
            violationRecord = violationRecordRepository.getViolationRecordByRegAndSection(pay.getRegno(), pay.getSection());

        } catch (Exception e) {
            System.out.println("No records found with this input");
        }

        if (violationRecord != null && pay.getAmount() == violationService.getPenaltyByViolation(pay.getSection())) {
            int count1;
            count1 = violationRecordRepository.getViolationCount(violationRecord.getRegno(), violationRecord.getSection());
            if (count1 == 1) {
                System.out.println("SUCCESS PAYMENT1");
                violationRecordRepository.deleteViolationCount(pay.getRegno(), pay.getSection());

            } else if (count1 > 1) {
                count1 = count1 - 1;
                System.out.println("SUCCESS PAYMENT2");
                violationRecordRepository.updateViolationCount(count1, pay.getRegno(), pay.getSection());
            }


        } else {
            System.out.println("Payment failed please recheck and pay again");
        }

    }


    @Transactional
    public void addViolationRecord(ViolationRecord violationRecord){
        logger.info("Addinng violation record  to db");
        int count;
        try {
            count = violationRecordRepository.getViolationCount(violationRecord.getRegno(), violationRecord.getSection());
        } catch (Exception n) {
            count = 0;
        }

        count = count + 1;
        violationRecord.setCount(count);

        System.out.println(violationRecord.toString());
        if (count == 1) {
            violationRecordRepository.save(violationRecord);
        } else if (count > 1) {
            violationRecordRepository.updateViolationCount(count, violationRecord.getRegno(), violationRecord.getSection());
        }
    }

    public List<ViolationRecord> getViolationRecord(){
        return violationRecordRepository.findAll();
    }
}
