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
    public List<Customer> getCustomers() {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        //create a query
        Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
        //execute query and get results
        List<Customer> myCustomers = theQuery.getResultList();
        System.out.println("CUSTOMERS RETRIEVED FROM DB: " + myCustomers);


        //return results
        return myCustomers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theCustomer);
        System.out.println("new customer saved to db: "+ theCustomer);
    }

    @Override
    public Customer getCustomer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Customer currentCustomer = currentSession.get(Customer.class, theId);
        return currentCustomer;
    }

    @Override
    public void deleteCustomer(int theId) {
        Session currentSession = sessionFactory.getCurrentSession();
//        Customer currentCustomer = currentSession.get(Customer.class,theId);
//        currentSession.delete(currentCustomer);
       Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
       theQuery.setParameter("customerId", theId);
       theQuery.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String theSearchName) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = null;

        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);
        }

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;
    }
}
