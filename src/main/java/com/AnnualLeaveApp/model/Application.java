package com.AnnualLeaveApp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;


@Document("application")
@Getter
@Setter
@AllArgsConstructor
public class Application {

    @Id
    private String id;
    private String userId;
    private String name;
    private ApplicationStatus status;
    private String description;
    private Date startDate;
    private Date endDate;

    private String message;

    public Application() {

    }

    public Application(String id) {
        this.getId();
    }


}
