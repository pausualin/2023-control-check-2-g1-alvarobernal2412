package org.springframework.samples.petclinic.disease;

import java.util.Set;

import org.hibernate.annotations.ManyToAny;
import org.springframework.samples.petclinic.model.NamedEntity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Symptom extends NamedEntity{

    String description;

    @Valid
    @ManyToMany
    Set<Disease> includes;
    
    @Valid
    @ManyToMany
    Set<Disease> excludes;
}
