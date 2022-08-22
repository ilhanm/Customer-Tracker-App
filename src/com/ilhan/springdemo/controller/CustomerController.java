package com.ilhan.springdemo.controller;

import com.ilhan.springdemo.dao.CustomerDAO;
import com.ilhan.springdemo.entity.Customer;
import com.ilhan.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    // REMINDER
    // with autowired spring will looking for a class that implements the CustomerDAO, which is CustomerDAOImpl in our case.
    //if there are multiple impl then @Qualifier needed. (check https://www.udemy.com/course/spring-hibernate-tutorial/learn/lecture/5836794#questions/6413250)

    //need to inject the customer dao
    @Autowired
    private CustomerService customerService;



    @GetMapping("/list")
    public String listCustomers(Model theModel){
        //get customers from DAO
        List<Customer> theCustomers = customerService.getCustomers();

        //add the customers to the model, so we can use it in jsp
        theModel.addAttribute("customers", theCustomers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAddCustomer(Model theModel){
        Customer theCustomer = new Customer();
        theModel.addAttribute("customer", theCustomer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer){

        customerService.saveCustomer(theCustomer);
        return "redirect:/customer/list";
    }

    @RequestMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel){
        //get customer from db
        Customer theCustomer = customerService.getCustomer(theId);
        //set customer as a model attribute to prepopulate form
        theModel.addAttribute("customer", theCustomer);

        return "customer-form";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("customerId") int theId){
        customerService.deleteCustomer(theId);
        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                  Model theModel) {
        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);

        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);
        return "list-customers";
    }



}
