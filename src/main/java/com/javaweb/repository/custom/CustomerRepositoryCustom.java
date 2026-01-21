package com.javaweb.repository.custom;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.request.CustomerSearchRequest;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<CustomerEntity> searchCustomer(CustomerSearchRequest request);
}
