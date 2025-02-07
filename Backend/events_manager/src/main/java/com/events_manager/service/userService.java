package com.events_manager.service;
import com.events_manager.repository.*;
import com.events_manager.model.user;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class userService {
    private final userRepository userRepository;

    public userService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String addUser(user user) throws ExecutionException, InterruptedException {
        return userRepository.saveUser(user);
    }

    public user getUser(String id) throws ExecutionException, InterruptedException {
        return userRepository.getUserById(id);
    }

    public String deleteUser(String id) throws ExecutionException, InterruptedException {
        return userRepository.deleteUser(id);
    }

    public List<user> getAllUsers() throws ExecutionException, InterruptedException {
        return userRepository.getAllUsers();
    }
}
