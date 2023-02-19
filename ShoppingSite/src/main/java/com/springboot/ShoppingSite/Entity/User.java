package com.springboot.ShoppingSite.Entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @OneToOne(cascade = {CascadeType.DETACH,
                         CascadeType.MERGE,
                         CascadeType.REFRESH,
                         CascadeType.PERSIST})
    @JoinColumn(name = "role_id")
    private Authority authority;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authority=" + authority +
                '}';
    }
}
