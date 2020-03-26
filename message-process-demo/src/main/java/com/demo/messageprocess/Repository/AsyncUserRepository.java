package com.demo.messageprocess.Repository;

import com.demo.messageprocess.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsyncUserRepository extends ReactiveMongoRepository<User,String> {
}
