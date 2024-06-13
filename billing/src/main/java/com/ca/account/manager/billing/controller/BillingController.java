package com.ca.account.manager.billing.controller;


import com.ca.account.manager.billing.enity.Billing;
import com.ca.account.manager.billing.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @GetMapping("/hi")
    public String hello() {
        return ("hello");
    }

    @RequestMapping("/billdetails")
    public String insertBilling() {
        Billing billing = new Billing();
        billing.setBillNo(20);
        billing.setCustomer_billName("IT Dep");
        billing.setBillName("Accounts");
        billing.setBillDescame("Manager");
        Billing bill = billingService.saveBilling(billing);
        return "Billing " + bill.getBillName() +
                bill.getBillNo() + bill.getCustomer_billName() + bill.getBillDescame() + " has been inserted successfully";
    }


    @GetMapping("/billingList")
    public List<Billing> getAllBills() {
        return billingService.findAll();
    }
}