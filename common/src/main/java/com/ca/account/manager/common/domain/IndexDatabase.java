package com.ca.account.manager.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "index_database")
public class IndexDatabase {

    @Column(name = "id_code")
    @Id
    private String idCode;
    @Column(name = "id_state")
    private String idState;
    @Column(name = "id_dialect")
    private String iddialect;
    @Column(name = "id_driver")
    private String iddriver;
    @Column(name = "id_url")
    private String idurl;
    @Column(name = "id_username")
    private String idusername;
    @Column(name = "id_password")
    private String idpassword;
    @Column(name = "id_schema")
    private String idschema;
    @Column(name = "id_configuration")
    private String idconfiguration;

}
