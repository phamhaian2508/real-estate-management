package com.javaweb.service.impl;

import com.javaweb.entity.AssignBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {
    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public AssignBuildingEntity toAssigmentBuildingEntity(AssignmentBuildingDTO assignmentBuildingDTO){
        BuildingEntity building = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        assignmentBuildingRepository.deleteByBuildingEntity(building);
        List<Long> staffIds =  assignmentBuildingDTO.getStaffs();
        for (Long id : staffIds) {
            AssignBuildingEntity assignBuildingEntity = new AssignBuildingEntity();
            UserEntity userEntity = userRepository.findById(id).get();
            assignBuildingEntity.setBuildingEntity(building);
            assignBuildingEntity.setUserEntity(userEntity);
            assignmentBuildingRepository.save(assignBuildingEntity);
        }
        return null;
    }
}
