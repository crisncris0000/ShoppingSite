package com.springboot.ShoppingSite.Service.Implementation;

import com.springboot.ShoppingSite.Entity.ConfirmationToken;
import com.springboot.ShoppingSite.Entity.User;
import com.springboot.ShoppingSite.Repository.UserRepository;
import com.springboot.ShoppingSite.Service.ConfirmationTokenService;
import com.springboot.ShoppingSite.Service.EmailSenderService;
import com.springboot.ShoppingSite.Service.Implementation.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ConfirmationTokenService confirmationTokenService;

    private int workload = 12;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<User> users = userRepository.findByUsername(username);

       User user = users.get();

       if(user == null){
           throw new UsernameNotFoundException("User not found");
       }

       if(!user.isEnabled()){
           sendEmailVerification(user);
           throw new DisabledException("User is disabled");
       }

       return new MyUserDetails(user);
    }

    public String cryptPassword(String password){
        String salt = BCrypt.gensalt(workload);
        String cryptPassword = BCrypt.hashpw(password, salt);

        return cryptPassword;
    }

    public void sendEmailVerification(User user){

        String webUrl = "http://" + request.getServerName() + ":" +
                request.getServerPort() + request.getContextPath();

        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        emailSenderService.sendEmail(
                user.getUsername(),
                "Email verification"
                ,"Please confirm your email using this link " +
                        webUrl + "/confirm-account?token=" + confirmationToken.getConfirmationToken()
        );
        confirmationTokenService.saveToken(confirmationToken);
    }

}
