package com.themdtnoauthorization.noauthorization.dao;

import com.themdtnoauthorization.noauthorization.entity.Disease;
import com.themdtnoauthorization.noauthorization.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DiseaseRepo extends JpaRepository<Disease, Long> {

    List<Disease> findAllByPatientOrderByDiagnosisDateDesc(Patient patient);
    List<Disease> findDiseasesByPatient(Patient patient);
    List<Disease> findAllByPatient(Patient patient);
    Optional<Disease> findFirstByPatientOrderByIdDesc(Patient patient);
    Optional<Disease> findDistinctByPatient(Patient patient);
}
