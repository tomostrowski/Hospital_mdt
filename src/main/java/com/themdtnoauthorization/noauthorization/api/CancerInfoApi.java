package com.themdtnoauthorization.noauthorization.api;

import com.themdtnoauthorization.noauthorization.entity.CancerInfo;
import com.themdtnoauthorization.noauthorization.manager.CancerInfoManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/cancer")
@CrossOrigin
public class CancerInfoApi {

    private final CancerInfoManager cancerInfoManager;

    public CancerInfoApi(CancerInfoManager cancerInfoManager) {
        this.cancerInfoManager = cancerInfoManager;
    }

    @GetMapping("/{id}")
    public Optional<CancerInfo> findById(@PathVariable Long id) {
        return cancerInfoManager.findById(id);
    }

    @GetMapping("/all")
    public Iterable<CancerInfo> findAll(){
        return cancerInfoManager.findAll();
    }

    @PostMapping("/new")
    public CancerInfo addNew(@RequestBody CancerInfo cancerInfo){
        return cancerInfoManager.save(cancerInfo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody CancerInfo updatedCancerInfo) {
        cancerInfoManager.update(id, updatedCancerInfo);
        return ResponseEntity.ok("Cancer info has been updated.");
    }

    @PatchMapping("/{id}/summary={summary}")
    public ResponseEntity<String> changeName(@PathVariable Long id, @PathVariable String summary){
        CancerInfo cancerInfo = findById(id).get();
        cancerInfo.setSummary(summary);
        cancerInfoManager.save(cancerInfo);
        return ResponseEntity.ok().body("Summary has been changed to "+summary);
    }

    @PatchMapping("/{id}/type={type}")
    public ResponseEntity<String> changeType(@PathVariable Long id, @PathVariable String type){
        CancerInfo cancerInfo = findById(id).get();
        cancerInfo.setType(type);
        cancerInfoManager.save(cancerInfo);
        return ResponseEntity.ok().body("Type has been changed to "+type);
    }

    @PatchMapping("/{id}/grade={grade}")
    public ResponseEntity<String> changeGrade(@PathVariable Long id, @PathVariable String grade){
        CancerInfo cancerInfo = findById(id).get();
        cancerInfo.setGrade(grade);
        cancerInfoManager.save(cancerInfo);
        return ResponseEntity.ok().body("Grade has been changed to "+grade);
    }

    @PatchMapping("/{id}/biomarkers={biomarkers}")
    public ResponseEntity<String> changeBiomarkers(@PathVariable Long id, @PathVariable String biomarkers){
        CancerInfo cancerInfo = findById(id).get();
        cancerInfo.setBiomarkers(biomarkers);
        cancerInfoManager.save(cancerInfo);
        return ResponseEntity.ok().body("Biomarkers has been changed to "+biomarkers);
    }
    @PatchMapping("/{id}/ki67={ki67}")
    public ResponseEntity<String> changeKi67(@PathVariable Long id, @PathVariable String ki67){
        CancerInfo cancerInfo = findById(id).get();
        cancerInfo.setKi67(ki67);
        cancerInfoManager.save(cancerInfo);
        return ResponseEntity.ok().body("Ki67 has been changed to "+ki67);
    }

    @PatchMapping("/{id}/typeOther={typeOther}")
    public ResponseEntity<String> changeTypeOther(@PathVariable Long id, @PathVariable String typeOther){
        CancerInfo cancerInfo = findById(id).get();
        cancerInfo.setKi67(typeOther);
        cancerInfoManager.save(cancerInfo);
        return ResponseEntity.ok().body("Other type has been changed to "+ typeOther);
    }

    @PatchMapping("/{id}/size={size}")
    public ResponseEntity<String> changeSize(@PathVariable Long id, @PathVariable int size){
        CancerInfo cancerInfo = findById(id).get();
        cancerInfo.setSize(size);
        cancerInfoManager.save(cancerInfo);
        return ResponseEntity.ok().body("size has been changed to "+size+" mm.");
    }
//
//    @PatchMapping("/{id}/margins={margins}")
//    public ResponseEntity<String> changeMargins(@PathVariable Long id, @PathVariable String margins){
//        CancerInfo cancerInfo = findById(id).get();
//        cancerInfo.setMargins(margins);
//        cancerInfoManager.save(cancerInfo);
//        return ResponseEntity.ok().body("Margins has been changed to "+margins);
//    }

    @PatchMapping("/{id}/positiveLymphNodes={positiveLymphNodes}")
    public ResponseEntity<String> changePositiveLymphNodes(@PathVariable Long id, @PathVariable String positiveLymphNodes){
        CancerInfo cancerInfo = findById(id).get();
        cancerInfo.setPositiveLymphNodes(positiveLymphNodes);
        cancerInfoManager.save(cancerInfo);
        return ResponseEntity.ok().body("Positive Lymph Nodes has been changed to "+positiveLymphNodes);
    }

    @PatchMapping("/{id}/tc={tc}")
    public ResponseEntity<String> changeTc(@PathVariable Long id, @PathVariable String tc){
        CancerInfo cancerInfo = findById(id).get();
        cancerInfo.setTc(tc);
        cancerInfoManager.save(cancerInfo);
        return ResponseEntity.ok().body("T-clinical has been changed to "+tc);
    }

    @PatchMapping("/{id}/nc={nc}")
    public ResponseEntity<String> changeNc(@PathVariable Long id, @PathVariable String nc){
        CancerInfo cancerInfo = findById(id).get();
        cancerInfo.setNc(nc);
        cancerInfoManager.save(cancerInfo);
        return ResponseEntity.ok().body("N-clinical has been changed to "+nc);
    }

    @PatchMapping("/{id}/mc={mc}")
    public ResponseEntity<String> changeMc(@PathVariable Long id, @PathVariable String mc){
        CancerInfo cancerInfo = findById(id).get();
        cancerInfo.setMc(mc);
        cancerInfoManager.save(cancerInfo);
        return ResponseEntity.ok().body("M-clinical has been changed to "+mc);
    }

    @PatchMapping("/{id}/tp={tp}")
    public ResponseEntity<String> changeTp(@PathVariable Long id, @PathVariable String tp){
        CancerInfo cancerInfo = findById(id).get();
        cancerInfo.setTp(tp);
        cancerInfoManager.save(cancerInfo);
        return ResponseEntity.ok().body("T-pathology has been changed to "+tp);
    }

    @PatchMapping("/{id}/np={np}")
    public ResponseEntity<String> changeNp(@PathVariable Long id, @PathVariable String np){
        CancerInfo cancerInfo = findById(id).get();
        cancerInfo.setNp(np);
        cancerInfoManager.save(cancerInfo);
        return ResponseEntity.ok().body("N-pathology has been changed to "+np);
    }

    @PatchMapping("/{id}/mp={mp}")
    public ResponseEntity<String> changeMp(@PathVariable Long id, @PathVariable String mp){
        CancerInfo cancerInfo = findById(id).get();
        cancerInfo.setMp(mp);
        cancerInfoManager.save(cancerInfo);
        return ResponseEntity.ok().body("M-pathology has been changed to "+mp);
    }

//    @PatchMapping("/{id}/marginNumber={marginNumber}")
//    public ResponseEntity<String> changeMarginNumber(@PathVariable Long id, @PathVariable double marginNumber){
//        CancerInfo cancerInfo = findById(id).get();
//        cancerInfo.setMarginNumber(marginNumber);
//        cancerInfoManager.save(cancerInfo);
//        return ResponseEntity.ok().body("Margin number has been changed to "+marginNumber+" mm.");
//    }
}
