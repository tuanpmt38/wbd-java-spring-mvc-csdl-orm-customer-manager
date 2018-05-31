package com.codegym.cms.controller;

import com.codegym.cms.model.Customer;
import com.codegym.cms.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //list customer
    @GetMapping("/customers")
    public String list( Model model){
        List<Customer> customers = this.customerService.findAll();
        model.addAttribute("customers",customers);
        return "/customer/list";
    }

    //create customer
    @GetMapping("/create-customer")
    public String create(Model model){
        model.addAttribute("customer", new Customer());
        return "/customer/create";
    }

    @PostMapping("/create-customer")
    public String save(Customer customer, Model model){
        customerService.save(customer);
        model.addAttribute("message", "New customer created successfully");
        return "/customer/create";
    }

    //update customer
    @GetMapping("/edit-customer/{id}")
    public String showFormEit(@PathVariable Long id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customer);
        return "/customer/edit";
    }

    @PostMapping
    public String update(@ModelAttribute("customer") Customer customer, Model model){
        customerService.save(customer);
        model.addAttribute("customer", customer);
        model.addAttribute("message", "update successfully");
        return "/customer/edit";
    }

    //delete customer
    @GetMapping("/delete-customer/{id")
    public String showFormDelete(@PathVariable Long id, Model model){
        Customer customer = customerService.findById(id);
        model.addAttribute("customers", customer);
        return "/customer/delete";
    }

    @PostMapping("/delete-customer}")
    public String delete(@ModelAttribute("customer")Customer customer){
        customerService.remove(customer.getId());
        return "redirect:customers";
    }
}
