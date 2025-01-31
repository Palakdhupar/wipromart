package com.wipro.wipromart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.wipromart.entity.Customer;
import com.wipro.wipromart.entity.Product;
import com.wipro.wipromart.model.CustomerDto;
import com.wipro.wipromart.service.CustomerService;

import jakarta.validation.Valid;
@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/save")
	public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto customerDto) {
		
		customerService.saveCustomer(customerDto);		
		return new ResponseEntity<>(customerDto,HttpStatus.CREATED);
	}
	
	@GetMapping("/get/{customerId}")
	public ResponseEntity<CustomerDto> fetchCustomerById(@PathVariable("customerId") long customerId) {
		
		CustomerDto customerDto = customerService.getCustomerById(customerId);	
		return new ResponseEntity<>(customerDto,HttpStatus.OK);
	}
	
	@GetMapping("/get/all")
	public List<CustomerDto> fetchAllCustomers() {
		
		List<CustomerDto> customers = customerService.getAllCustomers();
		return customers;
	}
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) {
		
		customer = customerService.updateCustomer(customer);		
		return new ResponseEntity<>(customer,HttpStatus.OK);
	}
	@DeleteMapping("/delete/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable ("customerId") long customerId) {
		
		customerService.deleteCustomer(customerId);		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}