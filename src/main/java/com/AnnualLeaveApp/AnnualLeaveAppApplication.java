package com.AnnualLeaveApp;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan({"com.AnnualLeaveApp.controller", "com.AnnualLeaveApp.service", "com.AnnualLeaveApp"})
//@EnableMongoRepositories("com.AnnualLeaveApp.repository")
public class AnnualLeaveAppApplication {

    private static Logger logger = LogManager.getLogger(AnnualLeaveAppApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AnnualLeaveAppApplication.class, args);
        logger.info("Annual Leave App");


    }


}
