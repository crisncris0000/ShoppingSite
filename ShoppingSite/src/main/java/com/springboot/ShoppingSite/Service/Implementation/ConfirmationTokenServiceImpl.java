package com.springboot.ShoppingSite.Service.Implementation;

import com.springboot.ShoppingSite.Entity.ConfirmationToken;
import com.springboot.ShoppingSite.Entity.User;
import com.springboot.ShoppingSite.Repository.ConfirmationTokenRepository;
import com.springboot.ShoppingSite.Service.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public ConfirmationToken findTokenById(int id) {

        Optional<ConfirmationToken> result = confirmationTokenRepository.findById(id);

        ConfirmationToken confirmationToken = result.orElseThrow(() ->{
            throw new NotFoundException("Token with the id: " + id + " not found");
        });

        return confirmationToken;
    }

    @Override
    public void saveToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    @Override
    public ConfirmationToken findConfirmationToken(String confirmationToken) {
        Optional<ConfirmationToken> result = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        ConfirmationToken token = result.orElseThrow(() ->{
           throw new NotFoundException("Token has not been found");
        });

        return token;
    }

    @Override
    public boolean doesTokenExist(String token) {

        ConfirmationToken confirmationToken = findConfirmationToken(token);

        if(confirmationToken == null){
            return false;
        }

        return true;
    }

    @Override
    public List<ConfirmationToken> findTokensByUsername(String username) {

        Optional<List<ConfirmationToken>> result = confirmationTokenRepository.findTokenByUsername(username);

        List<ConfirmationToken> tokens = result.orElseThrow(() -> {
           throw new NotFoundException("Token thats binded to the user: " + username + " has not been found");
        });

        return tokens;
    }

    @Override
    public void deleteToken(ConfirmationToken token) {
        confirmationTokenRepository.delete(token);
    }

    @Override
    public void deleteAllTokens(List<ConfirmationToken> token) {
        confirmationTokenRepository.deleteAll(token);
    }
}
