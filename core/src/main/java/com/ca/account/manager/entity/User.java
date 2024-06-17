package com.ca.account.manager.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name= "employees")
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name="user_Id")
    private String UserId;

    @Column(name="user_name")
    private String UserName;
    @Column(name="user_role")
    private String UserRole;
    @Column(name="user_access")
    private String User_access;
    @Column(name="user_status")
    private String User_status;
    @Column(name="user_assigned")
    private String Userassgined;
}





