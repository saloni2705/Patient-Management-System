package com.patient.controller;
import java.util.HashMap ;
import java.security.Principal;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.patient.entity.UserDtls;
import com.patient.repository.UserRepository;
import com.patient.service.UserService;
import com.patient.config.*;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    @ModelAttribute
    private void userDetails(Model model, Principal principal) {
        if (principal != null) {
            String email = principal.getName();
            UserDtls user = userRepo.findByEmail(email);
            model.addAttribute("user", user);
        }
    }
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity<String> login(@RequestBody(required = false) UserDtls user) {
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String email = authentication.getName();
            UserDtls loggedInUser = userRepo.findByEmail(email);
            Map<String, Object> response = new HashMap<>();
            response.put("id", loggedInUser.getId());
            return ResponseEntity.ok("Success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    
    @GetMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return ResponseEntity.ok("Logged out successfully");
    }

    @GetMapping("/register")
    public ResponseEntity<Void> register() {
        return ResponseEntity.ok().build();
    }

    
    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody UserDtls user) {
        boolean emailExists = userService.checkEmail(user.getEmail());

        if (emailExists) {
            return ResponseEntity.badRequest().body("Email Id already exists");
        } else {
            UserDtls createdUser = userService.createUser(user);
            if (createdUser != null) {
                return ResponseEntity.ok("Registered Successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong on the server");
            }
        }
    }
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody PasswordUpdateRequest request) {
        String email = request.getEmail();
        String currentPassword = request.getCurrentPassword();
        String newPassword = request.getNewPassword();

        // Check if the email exists
        if (!userService.checkEmail(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found");
        }

        // Get the user by email
        UserDtls user = userService.getUserByEmail(email);

        // Verify if the current password matches
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect current password");
        }

        // Update the password
        userService.updateUserPasswordByEmail(email, newPassword);

        return ResponseEntity.ok("Password updated successfully");
    }




    
    @GetMapping("/viewUser/{id}")
    public ResponseEntity<UserDtls> viewUser(@PathVariable("id") Integer id) {
        Optional<UserDtls> optionalUser = userRepo.findById(id);

        if (optionalUser.isPresent()) {
            UserDtls user = optionalUser.get();
            // Set the password to an empty string
            user.setPassword("");
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
