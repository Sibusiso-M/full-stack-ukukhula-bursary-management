package com.ukukhula.bursaryapi.assemblers;

import com.ukukhula.bursaryapi.controllers.StudentAllocationController;
import com.ukukhula.bursaryapi.entities.StudentAllocation;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class StudentAllocationAssembler implements RepresentationModelAssembler<StudentAllocation, EntityModel<StudentAllocation>> {

    private Map<String, Integer> parameters;

    public void setParameters(Map<String, Integer> parameters) {
        this.parameters = parameters;
    }

    @Override
    public EntityModel<StudentAllocation> toModel(StudentAllocation studentAllocation) {
        if (parameters == null) {
            throw new IllegalArgumentException("Parameters must be set before calling toModel");
        }

        return EntityModel.of(studentAllocation,
                linkTo(methodOn(StudentAllocationController.class).getStudentAllocationById(studentAllocation.getId())).withSelfRel(),
                linkTo(methodOn(StudentAllocationController.class).getAllStudentAllocations()).withRel("allStudentAllocations"),
                linkTo(methodOn(StudentAllocationController.class).getTotalSpentByUniversity(parameters)).withRel("totalSpentByUniversity"));
    }
}
