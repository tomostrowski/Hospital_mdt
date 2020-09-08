package com.themdtnoauthorization.noauthorization.dao;

import com.themdtnoauthorization.noauthorization.entity.Disease;
import com.themdtnoauthorization.noauthorization.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface DiseaseRepo extends JpaRepository<Disease, Long> {

    Set<Disease> findAllByPatientOrderByDiagnosisDateDesc(Patient patient);
}
