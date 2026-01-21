package com.javaweb.repository.custom;

import com.javaweb.entity.RentAreaEntity;

import java.util.List;

public interface RentAreaRepositoryCustom {
    List<RentAreaEntity> findByBuildingid(Long buildingid);
}
