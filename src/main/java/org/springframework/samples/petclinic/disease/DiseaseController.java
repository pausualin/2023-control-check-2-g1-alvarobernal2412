package org.springframework.samples.petclinic.disease;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.exceptions.ResourceNotOwnedException;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerService;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.disease.Disease;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.samples.petclinic.util.RestPreconditions;
import org.springframework.samples.petclinic.disease.Disease;
import org.springframework.samples.petclinic.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@SecurityRequirement(name = "bearerAuth")

@RequestMapping("/api/v1/diseases")

public class DiseaseController {

    private final DiseaseService diseaseService;
    private final OwnerService ownerService;
    private final PetService petService;
    private final UserService userService;
    private static final String VET_AUTH = "VET";
	private static final String ADMIN_AUTH = "ADMIN";
	private static final String OWNER_AUTH = "OWNER";


    public DiseaseController(DiseaseService diseaseService, OwnerService ownerService, PetService petService, UserService userService) {
        this.diseaseService = diseaseService;
        this.ownerService = ownerService;
        this.petService = petService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Disease>> findAll() {
        User currentUser = userService.findCurrentUser();
        if (!currentUser.hasAnyAuthority(VET_AUTH)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        List<Disease> diseases = diseaseService.findDiseases();
        return new ResponseEntity<>(diseases, HttpStatus.OK);
    }

    @GetMapping("/{diseaseId}")
    public ResponseEntity<Disease> findById(@PathVariable("diseaseId") int diseaseId) {
        User currentUser = userService.findCurrentUser();
        if (!currentUser.hasAnyAuthority(VET_AUTH)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Disease disease = diseaseService.findDiseaseById(diseaseId);
        if (disease == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(disease, HttpStatus.OK);
    }
    
}
