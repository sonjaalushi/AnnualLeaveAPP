package com.AnnualLeaveApp.controller;

import com.AnnualLeaveApp.AbstractTest;
import com.AnnualLeaveApp.model.User;
import com.AnnualLeaveApp.repository.ApplicationRepository;
import com.AnnualLeaveApp.repository.UserRepository;
import com.AnnualLeaveApp.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
class AdminControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @InjectMocks
    AdminController adminController;
    @Mock
    UserService userService;
    private Object Student;
    @Mock
    ApplicationRepository applicationRepository;
    @Mock
    UserRepository userRepository;

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId("1");
        user.setName("sonja");
        user.setWorkDaysNumber(1);
        user.setApplicationList(new ArrayList<>());
        Optional<User> userOptional = Optional.of(user);
        User user1 = userOptional.get();
        // providing knowledge
        when(userRepository.findById(any(String.class))).thenReturn(userOptional);
        ResponseEntity r = new ResponseEntity(HttpStatus.OK);
        when(userRepository.save(eq(user1))).thenReturn(user);
        User user2 = adminController.createUser(user);
        assertThat(user2.getWorkDaysNumber()).isNotNull();
    }


}