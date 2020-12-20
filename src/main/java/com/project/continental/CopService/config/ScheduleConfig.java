package com.project.continental.CopService.config;

import com.project.continental.CopService.model.Report;
import com.project.continental.CopService.model.ViolationRecord;
import com.project.continental.CopService.service.ViolationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ScheduleConfig {

    @Autowired
    ViolationService violationService;

    /**
     * To schedule a task to send report to each owners
     */
        private static final Logger logger= LoggerFactory.getLogger(ScheduleConfig.class);

        //@Scheduled(fixedDelay = 1000)
        @Scheduled(cron = "0 0 0 * * MON")
        public void sendReport(){
        List<ViolationRecord> violationRecords=new ArrayList<>();
         violationRecords= violationService.getViolationRecord();
         for (ViolationRecord violationRecord: violationRecords){
             System.out.println(violationRecord.toString());
            }
        }
}
