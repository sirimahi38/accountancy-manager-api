package com.ca.account.manager.billing.controller;


import com.ca.account.manager.billing.enity.Billing;
import com.ca.account.manager.billing.service.BillingService;
import com.ca.account.manager.common.domain.EmployeeTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;


    @RequestMapping("/billdetails/")
    public String insertBilling() {
        Billing billing = new Billing();
        billing.setBillNo(20);
        billing.setCustomer_billName("IT Dep");
        billing.setBillName("Accounts");
        billing.setBillDescName("Manager");
        Billing bill = billingService.saveBilling(billing);

 return ("Bills"+ billingService.saveBilling(billing)+ bill.getCustomer_billName() + bill.getBillDescName() + " has been inserted successfully");
    }
    @GetMapping("/billsList/")
    public List<Billing> getAllBills() {

        return billingService.findAll();
    }


}