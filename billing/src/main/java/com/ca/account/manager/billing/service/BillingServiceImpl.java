package com.ca.account.manager.billing.service;

import com.ca.account.manager.billing.enity.Billing;
import com.ca.account.manager.billing.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BillingServiceImpl implements BillingService {
    @Autowired
    private BillingRepository billingRepository;

    @Override
    public Billing saveBilling(Billing billing) {
        return billingRepository.save(billing);
    }


    @Override
    public void deleteBilling(Billing billing) {
        billingRepository.delete(billing);

    }


    @Override
    public Billing updateBilling(Billing billing) {
        return billingRepository.save(billing);
    }

    @Override
    public Billing findById(long id) {
        return billingRepository.findById(id).get();
    }

    @Override
    public List<Billing> findAll() {
        return List.of();
    }
}




