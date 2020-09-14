package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.MedicalProfessionalRepo;
import com.themdtnoauthorization.noauthorization.entity.MedicalProfessional;
import com.themdtnoauthorization.noauthorization.model.MedicalProfessionalListModel;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MedicalProfessionalManager {

    private MedicalProfessionalRepo medicalProfessionalRepo;

    public MedicalProfessionalManager(MedicalProfessionalRepo medicalProfessionalRepo) {
        this.medicalProfessionalRepo = medicalProfessionalRepo;
    }

    public MedicalProfessional save(MedicalProfessional medicalProfessional){
        return medicalProfessionalRepo.save(medicalProfessional);
    }

    public Optional<MedicalProfessional> findById(Long id){
        return medicalProfessionalRepo.findById(id);
    }

    public Iterable<MedicalProfessional> findAll(){
        return medicalProfessionalRepo.findAll();
    }

    public Iterable<MedicalProfessionalListModel> list(){
        List<MedicalProfessional> medicalProfessionalList = medicalProfessionalRepo.findAll(Sort.by(Sort.Direction.ASC, "lastName"));
        if (medicalProfessionalList.size()>0){
            Set<MedicalProfessionalListModel> medicalProfessionalListModelSet = new LinkedHashSet<>();
            for (MedicalProfessional medicalProfessional : medicalProfessionalList){
                MedicalProfessionalListModel model = new MedicalProfessionalListModel();
                model.setId(medicalProfessional.getId());
                model.setName((medicalProfessional.getName()));
//                model.setFirstName(medicalProfessional.getFirstName());
//                model.setLastName(medicalProfessional.getLastName());
                medicalProfessionalListModelSet.add(model);
            } return medicalProfessionalListModelSet;
        } else return new LinkedHashSet<>();
    }

/////!!!!
//    public Long getIdByName(String name){
//   MedicalProfessional medicalProfessional = medicalProfessionalRepo.findMedicalProfessionalByName(name).orElse(new MedicalProfessional(name));
//    }



    public void deleteById(Long id){
        medicalProfessionalRepo.deleteById(id);
    }

    public Iterable<MedicalProfessionalListModel> findByName(String name) {
        Set<MedicalProfessional> medicalProfessionalSet = medicalProfessionalRepo.findMedicalProfessionalsByNameStartingWith(name, Sort.by("lastName").ascending());
        if(medicalProfessionalSet.size()>0){
//            Stream<MedicalProfessional> stream = medicalProfessionalSet.stream();
//
//            stream.sorted(Comparator.comparing(medicalProfessionalSet::getLastName)
//                    .collect(Collectors.toCollection(LinkedHashSet:: new));
//

            Set<MedicalProfessionalListModel> medicalProfessionalListModelSet = new LinkedHashSet<>();
            for (MedicalProfessional medicalProfessional : medicalProfessionalSet){
                MedicalProfessionalListModel model = new MedicalProfessionalListModel();
                model.setId(medicalProfessional.getId());
                model.setName(medicalProfessional.getName());
//                model.setFirstName(medicalProfessional.getFirstName());
//                model.setLastName(medicalProfessional.getLastName());
//                model.setSpecialisation((medicalProfessional.getSpecialisation()));
                medicalProfessionalListModelSet.add(model);
            } return medicalProfessionalListModelSet;
        } else return new LinkedHashSet<>();
    }

    public Iterable<MedicalProfessionalListModel> findByLastName(String lastName) {
        Set<MedicalProfessional> medicalProfessionalSet = medicalProfessionalRepo.findMedicalProfessionalsByLastNameIsStartingWith(lastName);
        if(medicalProfessionalSet.size()>0){
            Set<MedicalProfessionalListModel> medicalProfessionalListModelSet = new LinkedHashSet<>();
            for (MedicalProfessional medicalProfessional : medicalProfessionalSet){
                MedicalProfessionalListModel model = new MedicalProfessionalListModel();
                model.setId(medicalProfessional.getId());
//                model.setFirstName(medicalProfessional.getFirstName());
//                model.setLastName(medicalProfessional.getLastName());
//                model.setSpecialisation((medicalProfessional.getSpecialisation()));
                medicalProfessionalListModelSet.add(model);
            } return medicalProfessionalListModelSet;
        } else return new LinkedHashSet<>();
    }

    public Iterable<MedicalProfessionalListModel> findByFirstNameAndLastName(String firstName, String lastName) {
        Set<MedicalProfessional> medicalProfessionalSet = medicalProfessionalRepo.findMedicalProfessionalsByFirstNameIsStartingWithAndLastNameIsStartingWith(firstName, lastName);
        if(medicalProfessionalSet.size()>0){
            Set<MedicalProfessionalListModel> medicalProfessionalListModelSet = new LinkedHashSet<>();
            for (MedicalProfessional medicalProfessional : medicalProfessionalSet){
                MedicalProfessionalListModel model = new MedicalProfessionalListModel();
                model.setId(medicalProfessional.getId());
//                model.setFirstName(medicalProfessional.getFirstName());
//                model.setLastName(medicalProfessional.getLastName());
                medicalProfessionalListModelSet.add(model);
            } return medicalProfessionalListModelSet;
        } else return new LinkedHashSet<>();
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void fillDB(){
//        save(new MedicalProfessional("Marek Mariusz","Ostrowski"));
//        save(new MedicalProfessional("Adam","Barczewski" ));
//        save(new MedicalProfessional("Michał","Szary" ));
//    }
}
