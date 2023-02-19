package com.springboot.ShoppingSite.repository;

import com.springboot.ShoppingSite.Entity.Authority;
import com.springboot.ShoppingSite.Entity.ConfirmationToken;
import com.springboot.ShoppingSite.Entity.User;
import com.springboot.ShoppingSite.Repository.ConfirmationTokenRepository;
import com.springboot.ShoppingSite.Repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ConfirmationTokenRepositoryTest {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void ConfirmationTokenRepository_SaveToken_ReturnSavedToken(){
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .tokenId(1)
                .confirmationToken("one-two-three")
                .createdDate(new Date())
                .build();

        ConfirmationToken savedToken = confirmationTokenRepository.save(confirmationToken);

        Assertions.assertThat(savedToken).isNotNull();
        Assertions.assertThat(savedToken.getTokenId()).isGreaterThan(0);
    }

    @Test
    public void ConfirmationTokenRepository_FindToken_ReturnTokenByName(){
        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .tokenId(1)
                .confirmationToken("one-two-three")
                .createdDate(new Date())
                .build();

        confirmationTokenRepository.save(confirmationToken);

        ConfirmationToken findToken = confirmationTokenRepository.findByConfirmationToken(confirmationToken.getConfirmationToken()).get();

        Assertions.assertThat(findToken).isNotNull();
        Assertions.assertThat(findToken.getTokenId()).isEqualTo(1);
    }

    @Test
    public void ConfirmationTokenRepository_FindTokens_ReturnTokensByUsername(){

        User user = new User("Chris", "pass", true, new Authority(1, "ROLE_USER"));

        userRepository.save(user);

        ConfirmationToken confirmationToken = ConfirmationToken.builder()
                .tokenId(1)
                .confirmationToken("one-two-three")
                .createdDate(new Date())
                .user(user)
                .build();

        confirmationTokenRepository.save(confirmationToken);

        List<ConfirmationToken> findTokens = confirmationTokenRepository.findTokenByUsername(confirmationToken.getUser().getUsername()).get();


        Assertions.assertThat(findTokens).isNotNull();
        Assertions.assertThat(findTokens.size()).isEqualTo(1);
    }

}
