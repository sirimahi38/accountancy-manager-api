package com.ca.account.manager.common.repository.event;

import com.ca.account.manager.common.repository.event.domain.PersistentEventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface WorkflowEventLogRepository extends JpaRepository<PersistentEventLog, BigInteger> {
}
