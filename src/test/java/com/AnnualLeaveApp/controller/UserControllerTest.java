package com.AnnualLeaveApp.controller;

import com.AnnualLeaveApp.AbstractTest;
import com.AnnualLeaveApp.model.Application;
import com.AnnualLeaveApp.model.ApplicationStatus;
import com.AnnualLeaveApp.repository.ApplicationRepository;
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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @InjectMocks
    UserController userController;
    @Mock
    UserService userService;
    private Object Student;
    @Mock
    ApplicationRepository applicationRepository;

    @Test
    public void deleteAppByIdTest() {
        String id = "1";
        this.applicationRepository.deleteById(id);
        ResponseEntity<String> application1 = userController.deleteApplication("1");
        assertThat(application1).isNotNull();
    }

    @Test
    public void testUpdateStudent() {
        Application application = new Application();
        application.setId("1");
        application.setName("sonja");
        application.setUserId("1");
        application.setDescription("test");
        application.setStatus(ApplicationStatus.PENDING);
        Optional<Application> applicationOptional = Optional.of(application);
        Application application1 = applicationOptional.get();
        // providing knowledge
        when(applicationRepository.findById(any(String.class))).thenReturn(applicationOptional);
        ResponseEntity r = new ResponseEntity(HttpStatus.OK);
        when(applicationRepository.save(eq(application1))).thenReturn(application);
        ResponseEntity<Application> application2 = userController.updateApplication("1", application);

        assertThat(application2.getBody()).isNotNull();
    }


}