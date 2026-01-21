package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    public static void joinTable(CustomerSearchRequest customerSearchRequest, StringBuilder sql) {
        Long staffId = customerSearchRequest.getStaffId();
        if(staffId != null) {
            sql.append(" INNER JOIN assignmentcustomer ON c.id = assignmentcustomer.customerid ");
        }
    }
    public static void querySpecial(CustomerSearchRequest customerSearchRequest,StringBuilder where)
    {
        Long staffId = customerSearchRequest.getStaffId();
        if (staffId != null) {
            where.append(" AND assignmentcustomer.staffid = " + staffId);
        }
    }
    public static void queryNomal(CustomerSearchRequest customerSearchRequest,StringBuilder where) {
        try {
            Field[] fields = CustomerSearchRequest.class.getDeclaredFields();
            for(Field item:fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("staffId")) {
                    Object value = item.get(customerSearchRequest);
                    if(value!=null&&value!="") {
                        if(item.getType().getName().equals("java.lang.Long")||item.getType().getName().equals("java.lang.Integer")) {
                            where.append(" AND c."+fieldName.toLowerCase()+" = "+value);
                        }
                        else {
                            where.append(" AND c."+fieldName.toLowerCase()+" LIKE '%"+value+"%' ");
                        }
                    }

                }
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public List<CustomerEntity> searchCustomer(CustomerSearchRequest request){
        StringBuilder sql = new StringBuilder("SELECT * FROM customer c ");
        StringBuilder where = new StringBuilder("WHERE 1=1 AND c.is_active=1 ");
        joinTable(request,sql);
        queryNomal(request,where);
        querySpecial(request,where);
        sql.append(where);
        sql.append(" GROUP BY c.id ");
        Query query = entityManager.createNativeQuery(sql.toString(),CustomerEntity.class);
        System.out.println(sql);
        return query.getResultList();
    }
}
