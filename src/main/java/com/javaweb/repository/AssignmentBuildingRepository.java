package com.javaweb.repository;

import com.javaweb.entity.AssignBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentBuildingRepository extends JpaRepository<AssignBuildingEntity,Long> {
    void deleteByBuildingEntity_IdIn(Long[] ids);
    List<AssignBuildingEntity> deleteByBuildingEntity(BuildingEntity building);
}
