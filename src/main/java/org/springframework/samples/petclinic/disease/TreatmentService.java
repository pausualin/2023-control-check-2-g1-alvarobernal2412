package org.springframework.samples.petclinic.disease;

import java.util.List;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
@Service
public class TreatmentService {
    private TreatmentRepository repo;

    public TreatmentService(TreatmentRepository tr){
        this.repo=tr;
    }
    @Transactional
    public List<Treatment> getAll() {
        return repo.findAll();
    }
    @Transactional(readOnly = true)
    public Treatment save(Treatment t) {
        return repo.save(t);
    }
}
