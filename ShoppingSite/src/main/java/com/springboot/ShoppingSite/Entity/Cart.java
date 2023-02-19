package com.springboot.ShoppingSite.Entity;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.DETACH,
                          CascadeType.PERSIST,
                          CascadeType.MERGE,
                          CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
