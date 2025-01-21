package org.springframework.samples.petclinic.disease;

import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
@Service
public class SymptomService {
    SymptomRepository repo;

    public SymptomService(SymptomRepository sr){
        this.repo=sr;
    }

    @Transactional
    public List<Symptom> getAll() {
        return repo.findAll();
    }
    @Transactional(readOnly=true)
    public Symptom save(Symptom s) {
        return repo.save(s);
    }
}
