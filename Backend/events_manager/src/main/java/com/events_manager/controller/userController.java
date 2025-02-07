package com.events_manager.controller;
import com.events_manager.model.*;
import com.events_manager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/users")
public class userController {
    private final userService userService;

    @Autowired
    public userController(userService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<user> getAllUsers() throws ExecutionException, InterruptedException {
        return userService.getAllUsers();
    }

    @PostMapping
    public String addUser(@RequestBody user user) throws ExecutionException, InterruptedException {
        return userService.addUser(user);
    }

    @GetMapping("/{id}")
    public user getUser(@PathVariable String id) throws ExecutionException, InterruptedException {
        return userService.getUser(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) throws ExecutionException, InterruptedException {
        return userService.deleteUser(id);
    }


}
