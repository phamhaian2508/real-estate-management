package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    public static void joinTable(BuildingSearchRequest buildingSearchRequest,StringBuilder sql) {
        Long staffId = buildingSearchRequest.getStaffId();
        if(staffId != null) {
            sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
        }
        Long areaTo = buildingSearchRequest.getAreaTo();
        Long areaFrom = buildingSearchRequest.getAreaFrom();
        if (areaTo!=null || areaFrom!=null) {
            sql.append(" INNER JOIN rentarea ON b.id = rentarea.buildingid ");
        }
    }
    public static void queryNomal(BuildingSearchRequest buildingSearchRequest,StringBuilder where) {
        try {
            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
            for(Field item:fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.equals("areaTo") && !fieldName.equals("areaFrom")
                    && !fieldName.equals("rentPriceFrom") && !fieldName.equals("rentPriceTo")) {
                    Object value = item.get(buildingSearchRequest);
                    if(value!=null&&value!="") {
                        if(item.getType().getName().equals("java.lang.Long")||item.getType().getName().equals("java.lang.Integer")) {
                            where.append(" AND b."+fieldName.toLowerCase()+" = "+value);
                        }
                        else {
                            where.append(" AND b."+fieldName.toLowerCase()+" LIKE '%"+value+"%' ");
                        }
                    }

                }
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void querySpecial(BuildingSearchRequest buildingSearchRequest,StringBuilder where)
        {
            Long staffId = buildingSearchRequest.getStaffId();
            if (staffId != null) {
                where.append(" AND assignmentbuilding.staffid = " + staffId);
            }
            Long rentAreaTo = buildingSearchRequest.getAreaTo();
            Long rentAreaFrom = buildingSearchRequest.getAreaFrom();
            if (rentAreaTo != null || rentAreaFrom != null) {
                if (rentAreaFrom != null) {
                    where.append(" AND rentarea.value >= " + rentAreaFrom);
                }
                if (rentAreaTo != null) {
                    where.append(" AND rentarea.value <= " + rentAreaTo);
                }

            }
            Long rentPriceTo = buildingSearchRequest.getRentPriceTo();
            Long rentPriceFrom = buildingSearchRequest.getRentPriceFrom();
            if (rentPriceTo != null || rentPriceFrom != null) {
                if (rentPriceFrom != null) {
                    where.append(" AND b.rentprice >= " + rentPriceFrom);
                }
                if (rentPriceTo != null) {
                    where.append(" AND b.rentprice <= " + rentPriceTo);
                }
            }   if(buildingSearchRequest.getTypeCode() != null && buildingSearchRequest.getTypeCode().size()!=0) {
            List<String> code = new ArrayList<>();
            for(String item:buildingSearchRequest.getTypeCode()) {
                code.add(""+item+"");
            }
            where.append(" AND b.type LIKE '%"+String.join(",", code)+"%' ");
        }
    }
    @Override
    public List<BuildingEntity> search(BuildingSearchRequest request){
        StringBuilder sql = new StringBuilder("SELECT * FROM building b ");
        StringBuilder where = new StringBuilder("WHERE 1=1 ");
        joinTable(request,sql);
        queryNomal(request,where);
        querySpecial(request,where);
        sql.append(where);
        sql.append(" GROUP BY b.id ");
        Query query = entityManager.createNativeQuery(sql.toString(),BuildingEntity.class);
        System.out.println(sql);
        return query.getResultList();
    }
}
