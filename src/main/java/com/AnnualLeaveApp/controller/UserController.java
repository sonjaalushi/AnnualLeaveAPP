package com.AnnualLeaveApp.controller;

import com.AnnualLeaveApp.AnnualLeaveAppApplication;
import com.AnnualLeaveApp.model.Application;
import com.AnnualLeaveApp.model.ApplicationStatus;
import com.AnnualLeaveApp.model.User;
import com.AnnualLeaveApp.repository.ApplicationRepository;
import com.AnnualLeaveApp.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LogManager.getLogger(AnnualLeaveAppApplication.class);
    @Autowired
    UserRepository userRepository;

    @Autowired
    ApplicationRepository applicationRepository;

    //create application based on user
    @PostMapping(value = "/createApplicationsByUserId/{id}")
    public ResponseEntity<User> createApplications(@PathVariable("id") String id, @RequestBody Application application) {
        application.setUserId(id);
        application.setStatus(ApplicationStatus.PENDING);
        User user = userRepository.findById(id).get();
        logger.info("User is " + user);
        //condition if user has more than 90 work days can apply for vacations
        if (user.getWorkDaysNumber() > 90) {
            Application application1 = applicationRepository.save(application);
            List<Application> applicationList = user.getApplicationList();
            applicationList.add(application1);
            user.setApplicationList(applicationList);
            User savedUser = userRepository.save(user);
        } else throw new RuntimeException("You cannot apply because you have less than 90 days work");
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //get all applications of a user
    @GetMapping("/getApplications/{id}")
    public ResponseEntity<List<Application>> getApplications(@PathVariable String id) {
        User user = userRepository.findById(id).get();
        List<Application> applicationList = user.getApplicationList();
        //stream of lambda expression
        logger.info(user.getApplicationList().stream().count());
        //see all application that have pending status
        user.getApplicationList().stream().filter(c -> c.getStatus().equals(ApplicationStatus.PENDING));
        return new ResponseEntity<>(applicationList, HttpStatus.OK);
    }

    //user can update application by id
    @PostMapping("updateApplicationById/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable("id") String id, @RequestBody Application application) {
        application.setUserId(id);
        Application application1 = applicationRepository.findById(id).get();
        Application application2 = applicationRepository.save(application);
        return new ResponseEntity<>(application2, HttpStatus.OK);
    }

    //user can delete application by id
    @DeleteMapping("deleteAppById/{id}")
    public ResponseEntity<String> deleteApplication(@PathVariable("id") String id) {
        applicationRepository.deleteById(id);
        return new ResponseEntity<String>("Application deleted successfully!.", HttpStatus.OK);

    }

    //user can see the remaining days off , after every application
    @GetMapping("/remainingDays/{id}")
    public int seeRemainingDays(@PathVariable("id") String id) {
        Application applicationn = new Application();
        User user = userRepository.findById(id).get();
        List<Application> applicationList = user.getApplicationList();
        long timeDiff = 0;
        int remainingDays = 30;
        //days off will be subtracted only if status is approved
        if (applicationn.getStatus().equals(ApplicationStatus.APPROVED)) {
            for (int a = 0; a < applicationList.size(); a++) {
                Application application = applicationList.get(a);
                long startDate = application.getStartDate().getTime();
                long endDate = application.getEndDate().getTime();
                timeDiff = endDate - startDate;
                int numberOfDaysOfVac = (int) (timeDiff / (1000 * 60 * 60 * 24));
                remainingDays = remainingDays - numberOfDaysOfVac;

            }
        }
        return remainingDays;

    }


}