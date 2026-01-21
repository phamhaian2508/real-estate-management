package com.javaweb.controller.admin;

import com.javaweb.enums.Transaction;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.request.CustomerSearchRequest;
import com.javaweb.repository.TransactionRepository;
import com.javaweb.service.CustomerService;
import com.javaweb.service.IUserService;
import org.omg.IOP.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomerController {
    @Autowired
    private IUserService userService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TransactionRepository transactionRepository;
    @RequestMapping(value = "/admin/customer-list", method = RequestMethod.GET)
    public ModelAndView CustomerList(@ModelAttribute CustomerSearchRequest customerSearchRequest, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/list");
        mav.addObject("customerList",customerService.searchCustomers(customerSearchRequest));
        mav.addObject("modelSearch", customerSearchRequest);
        mav.addObject("listStaffs",userService.getStaffs());
        return mav;
    }
    @RequestMapping(value = "/admin/customer-edit", method = RequestMethod.GET)
    public ModelAndView CustomerEdit(@ModelAttribute("customerEdit") CustomerDTO customerDTO, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        mav.addObject("customerEdit",customerDTO);
        return mav;
    }
    @RequestMapping(value = "/admin/customer-edit-{id}", method = RequestMethod.GET)
    public ModelAndView buildingEdit(@PathVariable("id") Long Id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/customer/edit");
        CustomerDTO customerDTO = customerService.findCustomerDTO(Id);
        mav.addObject("customerEdit",customerDTO);
        mav.addObject("transactionList", Transaction.type());
        mav.addObject("CSKHList", transactionRepository.findByCodeAndCustomerId("CSKH",Id));
        mav.addObject("DDXList", transactionRepository.findByCodeAndCustomerId("DDX",Id));
//        mav.addObject("modelModal",)
        return mav;
    }


}
