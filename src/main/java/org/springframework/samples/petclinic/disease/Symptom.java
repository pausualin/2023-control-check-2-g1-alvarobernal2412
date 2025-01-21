package org.springframework.samples.petclinic.disease;

import java.util.Set;

import org.springframework.samples.petclinic.model.NamedEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Symptom extends NamedEntity{

    String description;

    @NotNull
    @Min(1)
    Integer dose; 
    
    @Transient
    Set<Disease> includes;
    
    @Transient
    Set<Disease> excludes;
}
