package io.yash.LoginPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

    @Autowired
    private UserRepository repo;

    @GetMapping("")
    public String getHomePage(){
        return "home";
    }

    @GetMapping("/register")
    public String getSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "signup_form";
    }

    @PostMapping("/process_registration")
    public String processRegistration(User user){
        repo.save(user);
        return "registration_success";
    }

    @GetMapping("list_users")
    public String getUserList(){
        return "users";
    }

}
