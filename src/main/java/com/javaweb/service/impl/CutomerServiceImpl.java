package com.javaweb.service.impl;

import com.javaweb.converter.CustomerConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.TransactionEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.model.response.TransactionResponse;
import com.javaweb.repository.CustomerRepository;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CutomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<CustomerSearchResponse> searchCustomers(CustomerSearchRequest customerSearchRequest){
        List<CustomerEntity> customerEntities = customerRepository.searchCustomer(customerSearchRequest);
        List<CustomerSearchResponse> customerResponses = new ArrayList<>();
        for (CustomerEntity customerEntity : customerEntities) {
            CustomerSearchResponse customerSearchResponse = customerConverter.CustomerResponseConverter(customerEntity);
            customerResponses.add(customerSearchResponse);
        }
        return customerResponses;
    }
    @Override
    public CustomerEntity addOrUpdateCustomer(CustomerDTO customerDTO){
        CustomerEntity customer = customerConverter.customerEntityConverter(customerDTO);
        return customer;
    }
    @Override
    public ResponseDTO listStaffs(Long customerId) {
        CustomerEntity customer = customerRepository.findById(customerId).get();
        List<UserEntity> staffs = userRepository.findByStatusAndRoles_Code(1,"STAFF");
        List<UserEntity> staffAssignment = customer.getUsers();
        List<StaffResponseDTO> staffResponseDTOS = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity it : staffs) {
            StaffResponseDTO staffResponseDTO = new StaffResponseDTO();
            staffResponseDTO.setFullName(it.getFullName());
            staffResponseDTO.setStaffId(it.getId());
            if(staffAssignment.contains(it)){
                staffResponseDTO.setChecked("checked");
            }
            else {
                staffResponseDTO.setChecked("");
            }
            staffResponseDTOS.add(staffResponseDTO);
        }
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("success");
        return responseDTO;
    }
    @Override
    public ResponseDTO transactionEdit(Long transactionId){
        TransactionEntity transactionEntity = transactionRepository.findById(transactionId).get();
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setNote(transactionEntity.getNote());
        ResponseDTO responseDTO  = new ResponseDTO();
        responseDTO.setMessage("success");
        responseDTO.setData(transactionResponse);
        return responseDTO;
    }
    @Override
    public void deleteCustomer(Long[] ids){
        for (Long id : ids) {
            CustomerEntity customer = customerRepository.findById(id).get();
            customer.setActive(false);
            customerRepository.save(customer);
        }
    }
    @Override
    public void updateAssignmentCustomer(AssignmentCustomerDTO assignmentCustomerDTO){
        CustomerEntity customer = customerRepository.findById(assignmentCustomerDTO.getCustomerId()).get();
        List<Long> listStaffId = assignmentCustomerDTO.getStaffs();
        List<UserEntity> staffs = new ArrayList<>();
        for(Long id : listStaffId){
            staffs.add(userRepository.findById(id).get());
        }
        customer.setUsers(staffs);
        customerRepository.save(customer);
    }
    @Override
    public CustomerDTO findCustomerDTO(Long customerId){
        CustomerEntity customer = customerRepository.findById(customerId).get();
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }
    @Override
    public void creatTransaction(TransactionDTO transactionDTO){
        TransactionEntity transaction = modelMapper.map(transactionDTO, TransactionEntity.class);
        transaction.setCustomer(customerRepository.findById(transactionDTO.getCustomerId()).get());
        transactionRepository.save(transaction);
    }
    @Override
    public void editTransaction(TransactionDTO transactionDTO){
        TransactionEntity transaction = transactionRepository.findById(transactionDTO.getId()).get();
        transaction.setNote(transactionDTO.getNote());
        transactionRepository.save(transaction);
    }
}
