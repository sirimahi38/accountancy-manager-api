package com.ca.account.manager.billing.enity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bills")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Billing {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "bill_no")
    private int billNo;

    @Column(name = "bill_name")
    private String billName;

    @Column(name = "Cust_bill_name")
    private String customer_billName;

    @Column(name = "desc_name")
    private String billDescame;




}
