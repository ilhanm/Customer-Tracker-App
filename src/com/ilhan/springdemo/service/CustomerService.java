package com.ilhan.springdemo.service;

import com.ilhan.springdemo.entity.Customer;

import java.util.List;

public interface CustomerService {
     void deleteCustomer(int theId);

    public List<Customer> getCustomers();

    void saveCustomer(Customer theCustomer);

    Customer getCustomer(int theId);

    List<Customer> searchCustomers(String theSearchName);
}
