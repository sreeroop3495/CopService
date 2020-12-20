package com.project.continental.CopService.repository;

import com.project.continental.CopService.model.ViolationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ViolationRecordRepository extends JpaRepository<ViolationRecord,String> {


    /**
     * Repository for violationRecord database related queries and updates
     */

    @Query(value = "select * from violation_record where regno=?1",nativeQuery = true)
    List<ViolationRecord> getViolationRecordByReg(String regno);

    @Query(value = "select * from violation_record where regno=?1 and section=?2",nativeQuery = true)
    ViolationRecord getViolationRecordByRegAndSection(String regno,String section);

    @Query(value = "select count from violation_record where regno=?1 and section=?2",nativeQuery = true)
    int getViolationCount(String regno,String section);

    @Modifying(clearAutomatically = true)
    @Query(value = "update violation_record set count=?1 where regno=?2 and section=?3",nativeQuery = true)
    int updateViolationCount(int count,String regno,String section);

    @Modifying(clearAutomatically = true)
    @Query(value = "delete from violation_record where count=1 and regno=?1 and section=?2",nativeQuery = true)
    int deleteViolationCount(String regno,String section);


}
