package com.AnnualLeaveApp.repository;

import com.AnnualLeaveApp.model.Application;
import com.AnnualLeaveApp.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends MongoRepository<Application, String> {



}
