package com.themdtnoauthorization.noauthorization.dao;


import com.themdtnoauthorization.noauthorization.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {

    Set<Patient> findPatientsByGivenNameStartingWithAndSurnameStartingWith(String givenName, String surname);
    Set<Patient> findPatientsBySurnameStartingWith(String surname);
    Set<Patient> findByPatientNumberStartingWith(String patientNumber);
    Set<Patient> findByIdNumberStartingWith(String idNumber);
}
