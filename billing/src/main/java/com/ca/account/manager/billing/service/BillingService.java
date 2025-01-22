package com.ca.account.manager.billing.service;

import com.ca.account.manager.billing.enity.Billing;

import java.util.List;

public interface BillingService {

    public Billing saveBilling(Billing billing);

    public void deleteBilling (Billing billing);

    public Billing updateBilling(Billing billing);
    public Billing findById(long id);

    public List<Billing> findAll();
}
