package com.ilhan.springdemo.service;

import com.ilhan.springdemo.dao.CustomerDAO;
import com.ilhan.springdemo.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerDAO customerDAO;

    @Override
    @Transactional
    public void deleteCustomer(int theId) {
        customerDAO.deleteCustomer(theId);
    }

    @Override
    @Transactional
    public List<Customer> getCustomers() {
        //customerDAO(persistence) --> here(service) --> controller
        return customerDAO.getCustomers();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer theCustomer) {
        customerDAO.saveCustomer(theCustomer);
    }

    @Transactional
    @Override
    public Customer getCustomer(int theId) {
        return customerDAO.getCustomer(theId);
    }
}
