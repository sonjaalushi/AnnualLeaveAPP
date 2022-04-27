package com.AnnualLeaveApp.model;

import junitparams.JUnitParamsRunner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(JUnitParamsRunner.class)
class UserTest {

    @Test
    void getId() {
        User user = new User();
        user.setId("1");
        assertTrue(user.getId() == "1");
    }

    @Test
    void getName() {
        User user = new User();
        user.setName("test");
        assertTrue(user.getName() == "test");
    }

    @Test
    void getWorkDaysNumber() {
        User user = new User();
        user.setWorkDaysNumber(1);
        assertEquals(user.getWorkDaysNumber(), 1);
    }

    @Test
    void getApplicationList() {
        User user = new User();
        user.setApplicationList(new ArrayList<>());
        assertEquals(user.getApplicationList(), new ArrayList<Application>());
    }

    @Test
    void testConstructor() {
        User user = new User("1", "test", 1, new ArrayList<Application>());
        assertEquals("1", user.getId());
        assertEquals("test", user.getName());
        assertEquals(1, user.getWorkDaysNumber());
    }

}