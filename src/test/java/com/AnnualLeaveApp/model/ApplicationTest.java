package com.AnnualLeaveApp.model;

import junitparams.JUnitParamsRunner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(JUnitParamsRunner.class)
class ApplicationTest {

    @Test
    void test() {
        Application application = new Application();
        application.setName("test");
        assertTrue(application.getName() == "test");
    }

    @Test
    void test1() {
        Application application = new Application();
        application.setMessage("test");
        assertTrue(application.getMessage() == "test");
    }


    @Test
    void test2() {
        Application application = new Application();
        application.setId("1");
        assertTrue(application.getId() == "1");
    }


    @Test
    void test3() {
        Application application = new Application();
        application.setDescription("test");
        assertTrue(application.getDescription() == "test");
    }


    @Test
    void test4() {
        Application application = new Application();
        application.setUserId("1");
        assertTrue(application.getUserId() == "1");
    }


    @Test
    void test7() {
        Application application = new Application();
        application.setStatus(ApplicationStatus.PENDING);
        assertTrue(application.getStatus().equals(ApplicationStatus.PENDING));
    }


}