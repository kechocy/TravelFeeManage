package com.example.travelfeemanagews.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "budget")
public class Budget {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "traffic")
    private Float traffic;//交通预算

    @Column(name = "food")
    private Float food;//饮食预算

    @Column(name = "accommodation")
    private Float accommodation;//住宿预算

    @Column(name = "play")
    private Float play;//游玩预算

    @Column(name = "shopping")
    private Float shopping;//购物预算

    @Column(name = "other")
    private Float other;//其它预算

    @Column(name = "total")
    private Float total;//总预算

    @Column(name = "userid")
    private Integer userid;//对应用户预算
}
