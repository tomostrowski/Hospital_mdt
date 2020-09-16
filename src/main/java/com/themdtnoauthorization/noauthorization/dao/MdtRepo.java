package com.themdtnoauthorization.noauthorization.dao;

import com.themdtnoauthorization.noauthorization.entity.Disease;
import com.themdtnoauthorization.noauthorization.entity.Mdt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MdtRepo extends JpaRepository<Mdt, Long> {
    Set<Mdt> findMdtByDisease(Disease disease);
}
