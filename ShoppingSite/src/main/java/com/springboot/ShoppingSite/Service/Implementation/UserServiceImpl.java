package com.springboot.ShoppingSite.Service.Implementation;

import com.springboot.ShoppingSite.Entity.User;
import com.springboot.ShoppingSite.Repository.UserRepository;
import com.springboot.ShoppingSite.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        Optional<User> users = userRepository.findByUsername(username);

        User user = users.orElseThrow( () ->{
            throw new UsernameNotFoundException("User not found");
        });

        return user;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public boolean isUserLoggedIn(String user){
        if(user.equals("anonymousUser")){
            return false;
        }

        return true;
    }

    @Override
    public boolean doesUserExist(String username) {

        Optional<User> result = userRepository.findByUsername(username);

        if(result.isPresent()) return true;

        return false;
    }
}
