package com.example.demo;

import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

/**
 * Servicio para gestionar la lógica de negocio relacionada con los clientes.
 * Proporciona métodos para operaciones CRUD y paginación.
 */
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * @return 
     */
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    /**
     * @param customer 
     * @return
     */
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

        /**
     * @param id 
     * @throws ResourceAccessException 
     */
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Customer not found with id " + id));
        customerRepository.delete(customer);
    }    

    /**
     * @param id 
     * @param newCustomerData 
     * @return 
     * @throws RelationNotFoundException 
     */
    public Customer updateCustomer(Long id, Customer newCustomerData) throws RelationNotFoundException {
        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setFirstName(newCustomerData.getFirstName());
                    customer.setLastName(newCustomerData.getLastName());
                    customer.setAddress(newCustomerData.getAddress());
                    customer.setPrice(newCustomerData.getPrice());
                    customer.setSize(newCustomerData.getSize());
                    customer.setDescription(newCustomerData.getDescription());
                    return customerRepository.save(customer);
                })
                .orElseThrow(() -> new RelationNotFoundException("Customer not found with id " + id));
    }

    /**
     * @param page 
     * @param size
     * @return 
     */
    public Page<Customer> getCustomers(int page, int size) {
        return customerRepository.findAll(PageRequest.of(page, size));
    }
}


