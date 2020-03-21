package com.demo.batchprocessing;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

@org.springframework.stereotype.Repository
public interface Repository extends ReactiveMongoRepository<Message,String> {

}
