package com.AnnualLeaveApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Document("user")
@Getter
@Setter
@AllArgsConstructor
public class User {

    @Id
    private String id;
    private String name;
    private int workDaysNumber;
    private List<Application> applicationList = new ArrayList<>();

    public User() {
    }

    public User(String s, String test) {
    }

    public User(String id) {
        this.getId();
    }
}
