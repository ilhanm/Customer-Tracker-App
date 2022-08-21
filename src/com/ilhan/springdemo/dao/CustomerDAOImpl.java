package com.ilhan.springdemo.dao;

import com.ilhan.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    //inject the session factory
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public List<Customer> getCustomers() {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        //create a query
        Query<Customer> theQuery = currentSession.createQuery("from Customer", Customer.class);
        //execute query and get results
        List<Customer> myCustomers = theQuery.getResultList();
        System.out.println("CUSTOMERS RETRIEVED FROM DB: " + myCustomers);


        //return results
        return myCustomers;
    }
}