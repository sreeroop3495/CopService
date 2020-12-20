package com.project.continental.CopService.repository;

import com.project.continental.CopService.model.Violations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ViolationRepository extends JpaRepository<Violations,String> {


    /**
     * Repository for violation database related queries and updates
     */

    @Query(value = "select penalty from Violations where section=?1",nativeQuery = true)
    int getPenaltyBySection(String section);

    @Query(value="select * from Violations where section=?1",nativeQuery = true)
    Violations findViolationBySection(String section);
}
