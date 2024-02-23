package com.ukukhula.bursaryapi.assemblers;

import com.ukukhula.bursaryapi.controllers.UniversityApplicationController;
import com.ukukhula.bursaryapi.controllers.UniversityController;
import com.ukukhula.bursaryapi.entities.UniversityApplication;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UniversityApplicationModelAssembler implements RepresentationModelAssembler<UniversityApplication, EntityModel<UniversityApplication>> {

    @Override
    public EntityModel<UniversityApplication> toModel(UniversityApplication universityApplication) {
        return EntityModel.of(universityApplication,
                linkTo(methodOn(UniversityApplicationController.class).oneApplication(universityApplication.getId())).withSelfRel(),
                linkTo(methodOn(UniversityController.class).one(universityApplication.getUniversityId())).withRel(
                        "university"));
    }
}
