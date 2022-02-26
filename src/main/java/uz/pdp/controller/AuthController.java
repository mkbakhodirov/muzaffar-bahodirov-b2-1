package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.User;
import uz.pdp.repository.UserRepository;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/login")
    public String login(@ModelAttribute User user,
                        Model model
    ) {
        User user1 = userRepository.login(user);
        if (user1 != null && user1.getRole().equals(1)) {
            model.addAttribute("username", user1.getUsername());
            return "admin-cabinet";
        } else {
            model.addAttribute("res", "username " + user.getUsername() + "is not correct");
            return "login";
        }
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        boolean res = userRepository.add(user);
        if (res) {
            model.addAttribute("username", user.getUsername());
            return "admin-cabinet";
        } else {
            model.addAttribute("res", user.getUsername() + " already exists");
            return "register";
        }
    }
}
