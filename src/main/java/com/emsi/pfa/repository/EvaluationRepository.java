package com.emsi.pfa.repository;

import com.emsi.pfa.entity.DriverEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<DriverEvaluation,Long> {
    DriverEvaluation findByDriverPublicId(String driverPublicId);
}
