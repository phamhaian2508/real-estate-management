package com.javaweb.service;

import com.javaweb.entity.AssignBuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;

public interface AssignmentBuildingService {
    AssignBuildingEntity toAssigmentBuildingEntity(AssignmentBuildingDTO assignmentBuildingDTO);
}
