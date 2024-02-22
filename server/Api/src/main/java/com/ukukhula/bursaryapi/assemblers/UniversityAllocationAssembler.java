package com.ukukhula.bursaryapi.assemblers;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.ukukhula.bursaryapi.controllers.UniversityAllocationController;
import com.ukukhula.bursaryapi.entities.UniversityAllocation;

@Component
public class UniversityAllocationAssembler implements RepresentationModelAssembler<UniversityAllocation, EntityModel<UniversityAllocation>> {

  @Override
  public EntityModel<UniversityAllocation> toModel(UniversityAllocation universityAllocation) {
    return EntityModel.of(universityAllocation,
        linkTo(methodOn(UniversityAllocationController.class).getUniversityAllocationById(universityAllocation.getId())).withSelfRel()
        // linkTo(methodOn(UniversityAllocationController.class).getAllUniversityAllocations()).withRel("allocations")
        );
  }

}
