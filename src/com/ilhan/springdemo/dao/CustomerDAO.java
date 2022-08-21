package com.ilhan.springdemo.dao;

import com.ilhan.springdemo.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    public List<Customer> getCustomers();

    void saveCustomer(Customer newCustomer);
}
