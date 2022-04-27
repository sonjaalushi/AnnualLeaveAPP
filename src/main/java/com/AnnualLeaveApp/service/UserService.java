package com.AnnualLeaveApp.service;

import com.AnnualLeaveApp.model.User;
import com.AnnualLeaveApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(@RequestBody User user) {
        return userRepository.save(user);

    }
}
