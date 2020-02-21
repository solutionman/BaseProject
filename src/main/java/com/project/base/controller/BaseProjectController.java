package com.project.base.controller;

import com.project.base.model.Role;
import com.project.base.model.SimpleRecord;
import com.project.base.model.User;
import com.project.base.service.SimpleRecordService;
import com.project.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BaseProjectController {

    @Autowired
    private SimpleRecordService simpleRecordService;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    String login(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String currentUser = authentication.getName();
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    String getLogin(Model model, String error, String logout){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String currentUser = authentication.getName();

        String debug = "debug";
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

    @RequestMapping(value = "/secured", method = RequestMethod.GET)
    String secured(Model model){

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
//        String currentUser = authentication.getName();
        User currentUser = userService.findByUsername(authentication.getName());
        model.addAttribute("currentUser",currentUser);
        String isAdmin = "no";
        for(Role role : currentUser.getRoles() ){
            if(role.getName().equals("ADMIN")){
                isAdmin = "yes";
                break;
            }
        }
        model.addAttribute( "isAdmin", isAdmin );

        return "secured";
    }

    @GetMapping("/registration")
     String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
     }

     @PostMapping("/registration")
     String createUser(@ModelAttribute User user){

        if( null == userService.findByUsername(user.getUsername())){
            userService.save(user);
            return "redirect: /base/secured";
        }
        return "/registration";
     }

    @Value("injected message.")
    String message;

    @RequestMapping("/")
    String index(Model model){
        model.addAttribute("message",message);

        User user = userService.findByUsername("user");
        List<User> userList = userService.findAll();
//        List<User> users = userService.findUsersByUsertype("admin");

        return "index";
    }

    @GetMapping("/welcome")
    String welcome(Model model){
        String welcomePage = "Welcome page";
        model.addAttribute("welcomePage",welcomePage);

        List<SimpleRecord> simpleRecordList = simpleRecordService.findAll();
        model.addAttribute("simpleRecordList", simpleRecordList);

        SimpleRecord simpleRecord = simpleRecordService.findById(1);
        model.addAttribute("simpleRecord",simpleRecord);

        model.addAttribute( "newSimpleRecord", new SimpleRecord() );

        return "/welcome";
    }

    @PostMapping("/insertRecord")
    String insertRecord(@ModelAttribute SimpleRecord simpleRecord ){
        
        simpleRecordService.save(simpleRecord);

        return "redirect: /base/welcome";
    }

    @GetMapping("/deleteSimpleRecord")
    public String deleteSimpleRecord(@RequestParam(name="id")Long id){
//        SimpleRecord simpleRecord = simpleRecordService.findById(id);
        simpleRecordService.deleteById(id);

        return "redirect: /base/welcome";
    }

    @GetMapping("/users")
    String users(Model model){
        List<User> userList = userService.findAll();
        model.addAttribute("userList", userList);
        return "users";
    }

    @GetMapping("/editUser")
    String editUser(@RequestParam(name="id")Long id, Model model){
        User user = userService.findById(id);
        user.setPassword(null);
        model.addAttribute("user",user);
        return "editUser";
    }

    @PostMapping("/editUser")
    String doEditUser(@ModelAttribute User user){
        User userToEdit = userService.findById(user.getId());
//        User test = userService.findByUsername( user.getUsername() );
        if( null != user.getUsername() && ( null == userService.findByUsername( user.getUsername()) ) ){
            userToEdit.setUsername(user.getUsername());
        }
        if( null != user.getPassword() ){ userToEdit.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); }
        if( null != user.getRoles() ){ userToEdit.setRoles( user.getRoles() ); }

        userService.update( userToEdit );

        return "redirect: /base/users";
    }

    @GetMapping("/deleteUser")
    String deleteUser(@RequestParam(name="id")Long id, Model model){
        User user = userService.findById(id);
        userService.delete(user);
        return "redirect: /base/users";
    }

}
