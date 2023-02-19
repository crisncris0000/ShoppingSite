package com.springboot.ShoppingSite.Service;


import com.springboot.ShoppingSite.Entity.ConfirmationToken;
import com.springboot.ShoppingSite.Entity.User;

import java.util.List;

public interface ConfirmationTokenService {

    public ConfirmationToken findTokenById(int id);

    public void saveToken(ConfirmationToken token);

    public ConfirmationToken findConfirmationToken(String confirmationToken);

    public boolean doesTokenExist(String token);

    public List<ConfirmationToken> findTokensByUsername(String username);

    public void deleteToken(ConfirmationToken token);

    public void deleteAllTokens(List<ConfirmationToken> tokens);

}
