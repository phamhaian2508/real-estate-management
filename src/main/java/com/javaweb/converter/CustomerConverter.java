package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.response.CustomerSearchResponse;
import com.javaweb.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;



@Component
public class CustomerConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomerRepository customerRepository;
    public CustomerSearchResponse CustomerResponseConverter(CustomerEntity item) {
        CustomerSearchResponse customerSearchResponse = modelMapper.map(item, CustomerSearchResponse.class);
        return customerSearchResponse;
    }
    public CustomerEntity customerEntityConverter(CustomerDTO item) {
        item.setActive(true);
        CustomerEntity customer = modelMapper.map(item, CustomerEntity.class);
        if(item.getFullname()!="" && item.getPhone()!=""){
            customerRepository.save(customer);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Không thể tạo");
        }
        return customer;
    }
}
