package com.javaweb.api.admin;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.model.dto.AssignmentCustomerDTO;
import com.javaweb.model.dto.CustomerDTO;
import com.javaweb.model.dto.TransactionDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "customerAPIOfAdmin")
@RequestMapping("/api/customer")
public class CustomerAPI {
    @Autowired
    private CustomerService customerService;
    @PostMapping
    public void AddOrUpdateBuilding(@RequestBody CustomerDTO customerDTO) {
        CustomerEntity customer= customerService.addOrUpdateCustomer(customerDTO);
        System.out.println("ok");
    }
    @DeleteMapping("/{ids}")
    public void deleteCustomer(@PathVariable Long[] ids) {
        //Xuong DB xóa building theo ds id gửi về
        customerService.deleteCustomer(ids);
        System.out.println("ok");
    }
    @GetMapping("/{id}/staffs")
    public ResponseDTO loadStaffs(@PathVariable Long id) {
        ResponseDTO result = customerService.listStaffs(id);
        return result;
    }
    @PostMapping("/assigment")
    public void updateAssigmentCustomer(@RequestBody AssignmentCustomerDTO assignmentCustomerDTO) {
        customerService.updateAssignmentCustomer(assignmentCustomerDTO);
        System.out.println("OK");
    }
    @PostMapping("/contact")
    public void ContactCustomer(@RequestBody CustomerDTO CustomerDTO) {
        customerService.addOrUpdateCustomer(CustomerDTO);
        System.out.println("OK");
    }
    @PostMapping("/transaction")
    public void creatTransaction(@RequestBody TransactionDTO transactionDTO) {
        if(transactionDTO.getNote()!=""){
            customerService.creatTransaction(transactionDTO);
        }
        System.out.println("OK");
    }
    @PostMapping("/transactionEdit")
    public void editTransaction(@RequestBody TransactionDTO transactionDTO) {
        customerService.editTransaction(transactionDTO);
        System.out.println("OK");
    }
    @GetMapping("/{transactionId}/details")
    public ResponseDTO loadTransactionDetails(@PathVariable Long transactionId) {
        ResponseDTO result = customerService.transactionEdit(transactionId);
        return result;
    }
}
