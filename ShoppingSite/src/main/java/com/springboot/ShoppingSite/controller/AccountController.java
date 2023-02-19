package com.springboot.ShoppingSite.controller;

import com.springboot.ShoppingSite.Entity.ConfirmationToken;
import com.springboot.ShoppingSite.Entity.User;
import com.springboot.ShoppingSite.Entity.UserInput;
import com.springboot.ShoppingSite.Service.AuthorityService;
import com.springboot.ShoppingSite.Service.ConfirmationTokenService;
import com.springboot.ShoppingSite.Service.EmailSenderService;
import com.springboot.ShoppingSite.Service.Implementation.MyUserDetailsService;
import com.springboot.ShoppingSite.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class AccountController{
    @Autowired
    UserService userService;

    @Autowired
    AuthorityService authorityService;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    EmailSenderService emailService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    ConfirmationTokenService confirmationTokenService;



    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {

        if(userService.doesUserExist(user.getUsername())){
            model.addAttribute("errorMessage", "User already exists!");
            return "register";
        } else {

            user.setAuthority(authorityService.findAuthorityById(1));
            user.setEnabled(false);
            String cryptPassword = userDetailsService.cryptPassword(user.getPassword());
            user.setPassword(cryptPassword);
            userService.saveUser(user);

            String webUrl = "http://" + request.getServerName() + ":" +
                            request.getServerPort() + request.getContextPath();

            ConfirmationToken confirmationToken = new ConfirmationToken(user);

            emailService.sendEmail(user.getUsername(),
                    "Email verification",
                            "Please confirm your email using this link " +
                                    webUrl + "/confirm-account?token=" + confirmationToken.getConfirmationToken()
            );

            confirmationTokenService.saveToken(confirmationToken);

            model.addAttribute("email", user.getUsername());
        }

        return "redirect:/home";
    }

    @GetMapping("/confirm-account")
    public String confirmUserAccount(Model model, @RequestParam("token") String confirmationToken){
        ConfirmationToken token = confirmationTokenService.findConfirmationToken(confirmationToken);

        if(confirmationToken != null){
            User user = userService.findByUsername(token.getUser().getUsername());

            user.setEnabled(true);
            userService.saveUser(user);
        } else {
            return "error";
        }

        return "redirect:/home";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@ModelAttribute("username") UserInput userInput, Model model){

        if(!userService.doesUserExist(userInput.getUserInput())){
            model.addAttribute("userNotFound", "User does not exist please check your spelling");
            return "reset-password";
        }

        String webUrl = "http://" + request.getServerName() + ":" +
                request.getServerPort() + request.getContextPath();

        ConfirmationToken token = new ConfirmationToken(userService.findByUsername(userInput.getUserInput()));

        emailService.sendEmail(userInput.getUserInput(), "Reset password link",
                "Please use this link to reset your password " + webUrl + "/change-password?token=" + token.getConfirmationToken());

        confirmationTokenService.saveToken(token);

        return "redirect:/home";
    }

    @GetMapping("/change-password")
    public String changePasswordPage(@RequestParam("token") String token, Model model){

        if(!confirmationTokenService.doesTokenExist(token)){
            return "token-exist-error";
        }

        ConfirmationToken confirmationToken = confirmationTokenService.findConfirmationToken(token);

        User user = userService.findByUsername(confirmationToken.getUser().getUsername());

        model.addAttribute("user", user);

        model.addAttribute("token", token);

        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@ModelAttribute("user") User user){

        User updateUser = userService.findByUsername(user.getUsername());

        String cryptPassword = userDetailsService.cryptPassword(user.getPassword());

        updateUser.setPassword(cryptPassword);

        userService.saveUser(updateUser);

        List<ConfirmationToken> tokens = confirmationTokenService.findTokensByUsername(user.getUsername());

        confirmationTokenService.deleteAllTokens(tokens);

        return "redirect:/login";
    }

}
