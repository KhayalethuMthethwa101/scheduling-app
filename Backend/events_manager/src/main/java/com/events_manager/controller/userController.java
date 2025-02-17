package com.events_manager.controller;
import com.events_manager.model.*;
import com.events_manager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/v1/users")
public class userController {
    private final userService userService;

    @Autowired
    public userController(userService userService) {
        this.userService=userService;
    }

    @GetMapping
    public List<user> getAllUsers() throws ExecutionException, InterruptedException {
        return userService.getAllUsers();
    }

    @PostMapping("/adduser")
    public String addUser(@RequestBody user user) throws ExecutionException, InterruptedException {
        return userService.addUser(user);
    }

    @GetMapping("/{email}")
    public user getUser(@PathVariable String email) throws ExecutionException, InterruptedException {
        return userService.getUser(email);
    }

    @DeleteMapping("/{email}")
    public String deleteUser(@PathVariable String email) throws ExecutionException, InterruptedException {
        return userService.deleteUser(email);
    }

    @PutMapping("/update")
    public user updateUser(@RequestBody user user) throws ExecutionException, InterruptedException {
        return userService.updateUser(user);
    }

}
