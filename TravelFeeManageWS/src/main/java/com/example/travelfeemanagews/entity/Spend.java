package com.example.travelfeemanagews.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "spend")
public class Spend {

    @Id
    @Column(name = "id")
    private Integer id;//消费项id

    @Column(name = "matter")
    private String matter;//消费项名称

    @Column(name = "spend")
    private Float spend;//消费金额

    @Column(name = "budgetid")
    private Integer budgetid;//对应预算id
}

