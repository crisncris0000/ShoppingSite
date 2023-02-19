package com.springboot.ShoppingSite.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "authority")
    private String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
