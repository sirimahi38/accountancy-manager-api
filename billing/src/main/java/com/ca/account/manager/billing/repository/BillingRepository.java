package com.ca.account.manager.billing.repository;

import com.ca.account.manager.billing.enity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillingRepository extends JpaRepository<Billing, Long> {

}
