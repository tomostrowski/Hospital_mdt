package com.themdtnoauthorization.noauthorization.manager;

import com.themdtnoauthorization.noauthorization.dao.DiseaseRepo;
import com.themdtnoauthorization.noauthorization.dao.InstitutionRepo;
import com.themdtnoauthorization.noauthorization.dao.PatientRepo;
import com.themdtnoauthorization.noauthorization.entity.*;
import com.themdtnoauthorization.noauthorization.model.*;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;


@Service
public class PatientManager {

    private final PatientRepo patientRepo;
    private final DiseaseRepo diseaseRepo;
    private final InstitutionRepo institutionRepo;


    public PatientManager(PatientRepo patientRepo, DiseaseRepo diseaseRepo, InstitutionRepo institutionRepo) {
        this.patientRepo = patientRepo;
        this.diseaseRepo = diseaseRepo;
        this.institutionRepo = institutionRepo;
    }

    public Patient save(Patient patient) {
        return patientRepo.save(patient);
    }

    public Patient update(Long id, Patient updatedPatient) {
        updatedPatient.setId(id);
        return patientRepo.save(updatedPatient);
    }

    public Optional<Patient> findById(Long id) {
        return patientRepo.findById(id);
    }

    public List<Patient> findAll() {
        return patientRepo.findAll();
    }

    public void deleteById(Long id) {
        patientRepo.deleteById(id);
    }


    public List<Disease> findAllDiseasesByPatientId(Long id) {
        Patient patient = patientRepo.findById(id).get();
        return diseaseRepo.findAllByPatientOrderByDiagnosisDateDesc(patient);
    }

    public Set<PatientListModel> findBySurname(String surname) {
        Set<Patient> patientSet = patientRepo.findPatientsBySurnameStartingWith(surname);
        if (patientSet.size() > 0) {
            Set<PatientListModel> patientListModelSet = new LinkedHashSet<>();
            for (Patient patient : patientSet) {
                PatientListModel model = new PatientListModel();
                model.setId(patient.getId());
                model.setGivenName(patient.getGivenName());
                model.setSurname(patient.getSurname());
                model.setDateOfBirth(patient.getDateOfBirth());
                model.setPatientNumber(patient.getPatientNumber());
                model.setGender(patient.getGender());
                patientListModelSet.add(model);
            }
            return patientListModelSet;
        } else return new LinkedHashSet<>();
    }

    public Set<PatientListModel> findByPatientNumber(String patientNumber) {
        Set<Patient> patientSet = patientRepo.findByPatientNumberStartingWith(patientNumber);
        if (patientSet.size() > 0) {
            Set<PatientListModel> patientListModelSet = new LinkedHashSet<>();
            for (Patient patient : patientSet) {
                PatientListModel model = new PatientListModel();
                model.setId(patient.getId());
                model.setGivenName(patient.getGivenName());
                model.setSurname(patient.getSurname());
                model.setDateOfBirth(patient.getDateOfBirth());
                model.setPatientNumber(patient.getPatientNumber());
                model.setGender(patient.getGender());
                patientListModelSet.add(model);
            }
            return patientListModelSet;
        } else return new LinkedHashSet<>();
    }

    public Set<PatientListModel> findByIdNumber(String idNumber) {
        Set<Patient> patientSet = patientRepo.findByPatientNumberStartingWith(idNumber);
        if (patientSet.size() > 0) {
            Set<PatientListModel> patientListModelSet = new LinkedHashSet<>();
            for (Patient patient : patientSet) {
                PatientListModel model = new PatientListModel();
                model.setId(patient.getId());
                model.setGivenName(patient.getGivenName());
                model.setSurname(patient.getSurname());
                model.setDateOfBirth(patient.getDateOfBirth());
                model.setPatientNumber(patient.getPatientNumber());
                model.setGender(patient.getGender());
                patientListModelSet.add(model);
            }
            return patientListModelSet;
        } else return new LinkedHashSet<>();
    }

    //do listy pacjentó jest mniej danych!
    public Set<PatientListModel> getPatientList() {
        Set<Patient> patientSet = new LinkedHashSet<>(patientRepo.findAll());
        if (patientSet.size() > 0) {
            Set<PatientListModel> patientListModels = new LinkedHashSet<>();
            for (Patient patient : patientSet) {
                PatientListModel model = new PatientListModel();
                model.setId(patient.getId());
                model.setGivenName(patient.getGivenName());
                model.setSurname(patient.getSurname());
                model.setGender(patient.getGender());
                model.setDateOfBirth(patient.getDateOfBirth());
                model.setAge(patient.getAge());
                model.setPatientNumber(patient.getPatientNumber());
                model.setDiseases(getDiseaseListSet(patient));
                patientListModels.add(model);
            }
            return patientListModels;
        } else return new LinkedHashSet<PatientListModel>();
    }

    public Set<DiseaseModelList> getDiseaseListSet(Patient patient) {
        Set<Disease> diseaseSet = new LinkedHashSet<>(patient.getDiseases());
        if (diseaseSet.size() > 0) {
            Set<DiseaseModelList> diseaseModelSet = new LinkedHashSet<>();
            for (Disease disease : diseaseSet) {
                DiseaseModelList model = new DiseaseModelList();
                model.setId(disease.getId());
                model.setName(disease.getName());
                model.setDiagnosisDate(disease.getDiagnosisDate());

                diseaseModelSet.add(model);
            }
            return diseaseModelSet;
        } else return new LinkedHashSet<DiseaseModelList>();
    }


    public PatientModel getPatient(Long id) {
        Patient patient = patientRepo.findById(id).orElseThrow(()-> new RuntimeException("Patient does not exist."));
        PatientModel model = new PatientModel();
        model.setId(patient.getId());
        model.setGivenName(patient.getGivenName());
        model.setSurname(patient.getSurname());
        model.setGender(patient.getGender());
        model.setDateOfBirth(patient.getDateOfBirth());
        model.setAge(patient.getAge());
        model.setPatientNumber(patient.getPatientNumber());
        model.setEmail(patient.getEmail());
        model.setMobileNumber(patient.getMobileNumber());
        model.setIdNumber(patient.getIdNumber());
        model.setDiseases(getDiseaseSet(patient));
        model.setMedicalHistory(getMedicalHistoryModel(patient));
        return model;
    }

    public Set<DiseaseModel> getDiseaseSet(Patient patient) {
        Set<Disease> diseaseSet = new LinkedHashSet<>(patient.getDiseases());
        if (diseaseSet.size() > 0) {
            Set<DiseaseModel> diseaseModelSet = new LinkedHashSet<>();
            for (Disease disease : diseaseSet) {
                DiseaseModel model = new DiseaseModel();
                model.setId(disease.getId());
                model.setName(disease.getName());
                model.setDiagnosisDate(disease.getDiagnosisDate());
                //MUSISZ DODAC IFy jesli nie ma tych pół!
                if (disease.getDiagnosingPhysician() != null)
                    model.setDiagnosingPhysician(disease.getDiagnosingPhysician().getFirstName() + " " + disease.getDiagnosingPhysician().getLastName());
                if (disease.getReferringPhysician() != null)
                    model.setReferringPhysician(disease.getReferringPhysician().getFirstName() + " " + disease.getReferringPhysician().getLastName());
                model.setPlaceOfDiagnosis(disease.getPlaceOfDiagnosis());
                model.setCancerInfo(getCancerInfoModel(disease));
                model.setTreatmentHistory(getTreatmentHistoryModel(disease));
                model.setMdts(getMdts(disease));
                diseaseModelSet.add(model);
            }
            return diseaseModelSet;
        } else return new LinkedHashSet<DiseaseModel>();
    }


    public MedicalHistoryModel getMedicalHistoryModel(Patient patient) {
        MedicalHistory medicalHistory = patient.getMedicalHistory();
        if (medicalHistory != null) {
            MedicalHistoryModel model = new MedicalHistoryModel();
            model.setId(medicalHistory.getId());
            model.setFamilyHistory(medicalHistory.getFamilyHistory());
            model.setMedicalHistory(medicalHistory.getMedicalHistory());
            model.setMedication(medicalHistory.getMedication());
            model.setAllergies(medicalHistory.getAllergies());
            model.setPerformanceStatus(medicalHistory.getPerformanceStatus());
            return model;
        } else return null;
    }

    public CancerInfoModel getCancerInfoModel(Disease disease) {
        CancerInfo cancerInfo = disease.getCancerInfo();
        if (cancerInfo != null) {
            CancerInfoModel model = new CancerInfoModel();
            model.setId(cancerInfo.getId());
            model.setBiomarkers(cancerInfo.getBiomarkers());
            model.setGrade(cancerInfo.getGrade());
            model.setDcis(cancerInfo.getDcis());
            model.setKi67(cancerInfo.getKi67());
            model.setSuperior(cancerInfo.getSuperior());
            model.setInferior(cancerInfo.getInferior());
            model.setMedial(cancerInfo.getMedial());
            model.setLateral(cancerInfo.getLateral());
            model.setMc(cancerInfo.getMc());
            model.setMp(cancerInfo.getMp());
            model.setNc(cancerInfo.getNc());
            model.setNp(cancerInfo.getNp());
            model.setPositiveLymphNodes(cancerInfo.getPositiveLymphNodes());
            model.setSize(cancerInfo.getSize());
            model.setSummary(cancerInfo.getSummary());
            model.setTc(cancerInfo.getTc());
            model.setTp(cancerInfo.getTp());
            model.setType(cancerInfo.getType());
            model.setTypeOther(cancerInfo.getTypeOther());
            return model;
        } else return null;
    }

    public TreatmentHistoryModel getTreatmentHistoryModel(Disease disease) {
        TreatmentHistory treatmentHistory = disease.getTreatmentHistory();
        if (treatmentHistory != null) {
            TreatmentHistoryModel model = new TreatmentHistoryModel();
            model.setId(treatmentHistory.getId());
            model.setBiopsy(treatmentHistory.getBiopsy());
            model.setSurgery(treatmentHistory.getSurgery());
            model.setRadiotherapy(treatmentHistory.getRadiotherapy());
            model.setEndocrineTreatment(treatmentHistory.getEndocrineTreatment());
            model.setChemotherapy(treatmentHistory.getChemotherapy());
            model.setImmunotherapy(treatmentHistory.getImmunotherapy());
            model.setPhysiotherapy(treatmentHistory.getPhysiotherapy());
            model.setOther(treatmentHistory.getOther());
            return model;
        } else return null;
    }

    public Set<MdtModel> getMdts(Disease disease) {
        Set<Mdt> mdtSet = new LinkedHashSet<>(disease.getMdts());
        if (mdtSet.size() > 0) {
            Set<MdtModel> mdtModelSet = new LinkedHashSet<>();
            for (Mdt mdt : mdtSet) {
                MdtModel model = new MdtModel();
                model.setId(mdt.getId());
                model.setAdditionalComments(mdt.getAdditionalComments());
//                if(mdt.getAffiliation() != null)
//                model.setAffiliation(getAffiliation(mdt));
                model.setAffiliation(mdt.getAffiliation());
//                if (mdt.getAttendees() != null)
//                    model.setAttendees(mdt.getAttendees());
                model.setStartDate(mdt.getStartDate());
                model.setEndDate(mdt.getEndDate());
                model.setSummary(mdt.getSummary());
//                if(mdt.getLocationOfTreatment() != null)
//                    model.setLocationOfTreatment(getLocationOfTreatment(mdt));
                model.setLocationOfTreatment(mdt.getLocationOfTreatment());
                model.setDateOfReferralForMDT(mdt.getDateOfReferralForMDT());
                model.setComments(getComments(mdt));
                model.setIsOpen(mdt.getIsOpen());
                mdtModelSet.add(model);
            }
            return mdtModelSet;
        } else return new LinkedHashSet<>();
    }

    public Set<CommentModel> getComments(Mdt mdt) {
        Set<Comment> commentSet = new LinkedHashSet<>(mdt.getComments());
        if (commentSet.size()> 0) {
            Set<CommentModel> commentModelSet = new LinkedHashSet<>();
            for (Comment comment : commentSet) {
                CommentModel model = new CommentModel();
                model.setId(comment.getId());
                model.setText(comment.getText());
                if (comment.getAuthor()!=null)
                model.setAuthor(comment.getAuthor().getFirstName()+" "+comment.getAuthor().getLastName());
                model.setDate(comment.getDate());
                model.setDateOfEditing(comment.getDateOfEditing());
                commentModelSet.add(model);
            } return commentModelSet;
        }
        else return new LinkedHashSet<>();
    }

//    public MedicalProfessionalModel getAuthor(Comment comment){
//        MedicalProfessional author = comment.getAuthor();
//        if(author !=null){
//            MedicalProfessionalModel model = new MedicalProfessionalModel();
//            model.setId(model.getId());
//            model.setName(model.getName());
//            return model;
//        } else return null;
//    }

    public DiseaseModel findLastDisease(Long patientId) {
        Patient patient = patientRepo.findById(patientId).orElseThrow(()-> new RuntimeException("Patient doesnt exist."));
//       Disease disease=  diseaseRepo.findDistinctByPatient(patient).orElseThrow(()-> new RuntimeException("Disease doesnt exist."));
//        List<Disease> diseaseSet = diseaseRepo.findAllByPatientOrderByDiagnosisDateDesc(patient);
//        List<Disease> diseaseList = diseaseRepo.findAll().stream()
//                                            .filter(d->d.getId()==1)
////                                            .sorted()
//                                            .collect(Collectors.toList());
        Disease disease = diseaseRepo.findFirstByPatientOrderByIdDesc(patient).orElseThrow(()-> new RuntimeException("Disease does not exist."));
        if (disease!=null) {
            DiseaseModel model = new DiseaseModel();
            model.setId(disease.getId());
            model.setName(disease.getName());
            model.setMdts(getMdts(disease));

//            model.setCancerInfo(getCancerInfoModel(disease));
//            model.setDiagnosingPhysician(model.getDiagnosingPhysician());
//            model.setDiagnosisDate(disease.getDiagnosisDate());
//            model.setPlaceOfDiagnosis(disease.getPlaceOfDiagnosis());
//            model.setTreatmentHistory(getTreatmentHistoryModel(disease));
//            model.setReferringPhysician(disease.getReferringPhysician().getName());
            return model;
        }
        else return null;
    }


//    }
//public InstitutionModel getLocationOfTreatment(Mdt mdt){
//        Institution institution = mdt.getLocationOfTreatment();
//        if(institution !=null){
//            InstitutionModel model = new InstitutionModel();
//            model.setId(institution.getId());
//            model.setName(institution.getName());
//            return model;
//        } else return null;
//}

//    public InstitutionModel getAffiliation(Mdt mdt){
//        Institution institution = mdt.getAffiliation();
//        if(institution !=null){
//            InstitutionModel model = new InstitutionModel();
//            model.setId(institution.getId());
//            model.setName(institution.getName());
//            return model;
//        } else return null;
//    }


//   @EventListener(ApplicationReadyEvent.class)
//   public void fillDB(){
//        save(new Patient("Weronika", "Rosatti", "AWQ210321", LocalDate.of(1980, 9, 14 ), "F", "+55 564 000 300", "rosati@gmail.com", "6as123345"));
//        save(new Patient( "Magdalena", "Gorys", "TWM21000021", LocalDate.of(1981, 9, 15 ), "F", "+49 253 923 436", "gorys@gmail.com", "969856"));
//        save(new Patient("Bożena", "Maliszewska", "OTWTH6427", LocalDate.of(1958, 10, 15 ), "F", "+22 125 923 111", "maria234@gmail.com", "162346"));
//        save(new Patient("Agnieszka", "Bargos", "OWTSD210321", LocalDate.of(1963, 6, 14 ), "F", "+78 3214 923 111", "goska235@gmail.com", "864824511"));
//        save(new Patient("Natalia", "Olensko", "PTSR210321", LocalDate.of(1972, 1, 14 ), "F", "+88 111 685 972", "barbara@gmail.com", "e503952"));
//        save(new Patient("Tatiana", "Partyka", "KKS210321", LocalDate.of(1962, 9, 14 ), "F", "+411 777 963 436", "mickiewicz@gmail.com", "5234k6427"));
//   }
}
