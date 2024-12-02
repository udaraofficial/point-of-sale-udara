package com.springbootacademy.pos_system.controller;

import com.springbootacademy.pos_system.dto.CustomerDto;
import com.springbootacademy.pos_system.dto.request.CustomerUpdateDto;
import com.springbootacademy.pos_system.service.CustomerService;
import com.springbootacademy.pos_system.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin

public class CustomerController {

    @Autowired
    private CustomerService customerService;


    // Save Customer
    @PostMapping(
            path = "/save"
    )

    public ResponseEntity<StandardResponse> saveCustomer(@RequestBody CustomerDto customerDto) {
       String message = customerService.saveCustomer(customerDto);
       return new ResponseEntity<StandardResponse>(
               new StandardResponse(200,"Success",message),
               HttpStatus.CREATED
       );
    }
    // Update Customer
    @PutMapping(
            path = "/update"
    )
    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CustomerUpdateDto customerUpdateDto) {
        String message = customerService.updateCustomer(customerUpdateDto);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success" , message),
                HttpStatus.OK
        );
    }
    // Get All Customer Details
    @GetMapping(
            path = "/getAll"
    )
    public ResponseEntity<StandardResponse> getAllCustomers() {
        List<CustomerDto> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success" , allCustomers),
                HttpStatus.OK
        );
    }
    // Get selected Customer Details
    @GetMapping(
            path = "/get",
            params = "id"
    )
    public ResponseEntity<StandardResponse> getCustomerById(@RequestParam(value = "id") int customerId){
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success" , customerId),
                HttpStatus.OK
        );
    }
   // Delete Customer
    @DeleteMapping(
            path = "/delete/{id}"
    )
    public ResponseEntity<StandardResponse> deleteCustomer(@PathVariable(value = "id") int customerId) {
        String delete = customerService.deleteCustomer(customerId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",customerId),
                HttpStatus.OK
        );
    }
    // Select Customer by status
    @GetMapping(
            path = "/getAllCustomersByActiveState/{status}"
    )
    public ResponseEntity<StandardResponse> getAllCustomersByActiveState(@PathVariable(value = "status") boolean activeState) {
        List<CustomerDto> allCustomers = customerService.getAllCustomersByActiveState(activeState);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success" , allCustomers),
                HttpStatus.OK
        );
    }

}
