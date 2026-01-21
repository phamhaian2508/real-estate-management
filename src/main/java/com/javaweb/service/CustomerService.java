package com.javaweb.service;


import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerSearchResponse> searchCustomers(CustomerSearchRequest customerSearchRequest);
    CustomerEntity addOrUpdateCustomer(CustomerDTO customerDTO);
    void updateAssignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO);
    ResponseDTO listStaffs(Long buildingId);
    void deleteCustomer(Long[] ids);
    CustomerDTO findCustomerDTO(Long customerId);
    void creatTransaction(TransactionDTO transactionDTO);
    void editTransaction(TransactionDTO transactionDTO);
    ResponseDTO transactionEdit(Long transactionId);

}
