package com.themdtnoauthorization.noauthorization.dao;

import com.themdtnoauthorization.noauthorization.entity.MedicalProfessional;
import com.themdtnoauthorization.noauthorization.model.MedicalProfessionalListModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface MedicalProfessionalRepo extends JpaRepository<MedicalProfessional, Long> {

    Set<MedicalProfessional> findMedicalProfessionalsByFirstNameIsStartingWithAndLastNameIsStartingWith(String firstName, String lastName);
    Set<MedicalProfessional> findMedicalProfessionalsByLastNameIsStartingWith(String lastName);
    Set<MedicalProfessional> findMedicalProfessionalsByNameStartingWith(String name, Sort lastName);
//    Optional<MedicalProfessional> findFirstByName(String name);
}
