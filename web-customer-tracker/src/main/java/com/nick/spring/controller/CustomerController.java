package com.nick.spring.controller;

import com.nick.spring.entity.Customer;
import com.nick.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class    CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String customerList(Model model){

        // get customers from the service

        List<Customer> customerList =  customerService.getCustomers();

        // add the customers to the model

        model.addAttribute("customers", customerList);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        Customer customer = new Customer();

        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer){

        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id,
                                    Model model){

        Customer customer = customerService.getCustomer(id);

        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id){

        customerService.deleteCustomer(id);

        return "redirect:/customer/list";
    }
}
