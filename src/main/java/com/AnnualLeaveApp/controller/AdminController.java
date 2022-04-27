package com.AnnualLeaveApp.controller;


import com.AnnualLeaveApp.AnnualLeaveAppApplication;
import com.AnnualLeaveApp.model.Application;
import com.AnnualLeaveApp.model.User;
import com.AnnualLeaveApp.repository.ApplicationRepository;
import com.AnnualLeaveApp.repository.UserRepository;
import com.AnnualLeaveApp.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private static Logger logger = LogManager.getLogger(AnnualLeaveAppApplication.class);
    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    //admin can create users
    @PostMapping("/createUser")
    public User createUser(User user) {
        user.setApplicationList(new ArrayList<>());
        userService.createUser(user);
        logger.info("User Created Successfully");
        return user;
    }

    //admin can change the application status by application_id of user_id in order to confirm or reject the vacations
    //if application is rejected a message is required on the response body
    @PostMapping(value = "user/{user_id}/confirm/application/{application_id}")
    public User updateApplicationStatus(@PathVariable("user_id") String user_id, @PathVariable("application_id") String application_id, @RequestBody Application application) {
        User user = userRepository.findById(user_id).get();
        Application applicationn = applicationRepository.findById(application_id).get();
        applicationn.setMessage(application.getMessage());
        applicationn.setStatus(application.getStatus());
        List<Application> applicationList = user.getApplicationList();
        applicationList = applicationList.stream().map(app -> {
            if (app.getId().equals(applicationn.getId())) {
                app.setMessage(applicationn.getMessage());
                app.setStatus(applicationn.getStatus());
            }
            return app;
        }).collect(Collectors.toList());
        user.setApplicationList(applicationList);
        applicationRepository.save(applicationn);
        return userRepository.save(user);


    }

}
