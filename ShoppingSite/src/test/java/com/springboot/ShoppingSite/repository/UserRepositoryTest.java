package com.springboot.ShoppingSite.repository;

import com.springboot.ShoppingSite.Entity.Authority;
import com.springboot.ShoppingSite.Entity.User;
import com.springboot.ShoppingSite.Repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepository_SaveUser_ReturnSavedUser(){

        User user = User.builder()
                .username("christopherrivera134@gmail.com")
                .password("password")
                .isEnabled(true)
                .authority(new Authority(1, "ROLE_USER")).build();

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getUsername().length()).isGreaterThan(0);
    }

    @Test
    public void UserRepository_FindByUsername_ReturnUserByUsername(){

        User user = User.builder()
                .username("christopherrivera134@gmail.com")
                .password("password")
                .isEnabled(true)
                .authority(new Authority(1, "ROLE_USER")).build();

        userRepository.save(user);

        User findUser = userRepository.findByUsername(user.getUsername()).get();

        Assertions.assertThat(findUser).isNotNull();
        Assertions.assertThat(findUser.getUsername().length()).isGreaterThan(0);
    }


}
