package com.demo.batchprocessing.repository;

import com.demo.batchprocessing.domain.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

@org.springframework.stereotype.Repository
public interface MessageRepository extends ReactiveMongoRepository<Message,String> {

}
