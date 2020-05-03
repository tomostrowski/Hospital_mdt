package com.themdtnoauthorization.noauthorization.dao;


import com.themdtnoauthorization.noauthorization.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long> {

}
