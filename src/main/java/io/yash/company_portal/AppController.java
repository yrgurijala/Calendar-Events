package io.yash.company_portal;

import io.yash.company_portal.entity.User;
import io.yash.company_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    @GetMapping("/list_users")
    public String getUserList(Model model){
        List<User> listUsers = repo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/view_calendar")
    public String viewCalendar(){
        return "calendar.html";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}
