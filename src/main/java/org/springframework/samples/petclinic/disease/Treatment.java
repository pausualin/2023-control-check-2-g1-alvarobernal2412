package org.springframework.samples.petclinic.disease;

import java.util.Set;

import org.hibernate.annotations.ManyToAny;
import org.springframework.samples.petclinic.model.NamedEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Treatment extends NamedEntity{
    String description;

    @NotNull
    @Min(1)
    Integer dose; 

    @Valid
    @ManyToMany
    Set<Disease> recommendedFor;
}
