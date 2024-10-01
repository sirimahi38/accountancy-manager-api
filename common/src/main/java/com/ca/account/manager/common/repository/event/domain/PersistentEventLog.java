package com.ca.account.manager.common.repository.event.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "WF_HISTORY")
@Data
@AllArgsConstructor// required by builder
@NoArgsConstructor // required by JPA
@Builder
public class PersistentEventLog {
    @Id
    @Column(nullable = false, unique = true, name = "WFH_ID")
    private BigInteger id;

    @Column(nullable = false, name = "WFH_INSTANT")
    private Timestamp instant;

    @Column(name = "WFH_AU_CODE")
    private String currentUser;

    @Column(nullable = false, name = "WFH_G_ID")
    private BigInteger graphId;

    @Column(name = "WFH_N_ID")
    private BigInteger nodeId;

    @Column(nullable = false, name = "WFH_E_ID")
    private BigInteger executionId;

    @Column(name = "WFH_O_ID")
    private BigInteger documentObjectId;

    @Column(nullable = false, name = "WFH_CLASS")
    private String eventName;

    @Column(nullable = false, name = "WFH_VALUE")
    private String eventData;

    @Column(name = "WFH_ON_BEHALF_OF")
    private String userOnBehalfOf;


}
