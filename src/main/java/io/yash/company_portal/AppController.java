package io.yash.company_portal;

import io.yash.company_portal.entity.CustomDate;
import io.yash.company_portal.entity.Event;
import io.yash.company_portal.entity.User;
import io.yash.company_portal.repository.EventRepository;
import io.yash.company_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private EventRepository eventRepo;

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
        userRepo.save(user);
        return "registration_success";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/list_users")
    public String getUserList(Model model){
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/view_calendar")
    public String viewCalendar(Model model){
        model.addAttribute("date", new CustomDate());
        return "calendar";
    }

    @PostMapping("/load_calendar")
    public String loadCalendar(CustomDate date, Model model){
        model.addAttribute("date", date.getDate());
        List<Event> listEvents = eventRepo.findEventsByDate(date.getDate());
        model.addAttribute("listEvents",listEvents);
        return "calendar_updated";
    }

    @GetMapping("/create_event")
    public String createEvent(Model model){
        model.addAttribute("event", new Event());
        return "create_event";
    }

    @PostMapping("/process_event_creation")
    public String processEventCreation(Event event, Principal principal, Model model){
        model.addAttribute("date", new CustomDate());

        String userEmail = principal.getName();
        User loggedInUser = userRepo.findByEmail(userEmail);

        event.setFullName(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
        eventRepo.save(event);
        return "calendar";
    }
}
