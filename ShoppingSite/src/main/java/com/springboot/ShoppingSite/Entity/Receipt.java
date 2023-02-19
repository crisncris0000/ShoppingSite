package com.springboot.ShoppingSite.Entity;

import javax.persistence.*;

@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "total")
    private int total;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", total=" + total +
                '}';
    }
}
