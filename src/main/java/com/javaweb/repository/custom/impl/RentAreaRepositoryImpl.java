package com.javaweb.repository.custom.impl;

import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.custom.RentAreaRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepositoryCustom {
    @Autowired
    private BuildingRepository buildingRepository;
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<RentAreaEntity> findByBuildingid(Long buildingid){
        String sql = "select * from rentarea where buildingid = "+buildingid;
        Query query = entityManager.createNativeQuery(sql, RentAreaEntity.class);
        List<RentAreaEntity> rentAreas = query.getResultList();
        return rentAreas;
    }

}
