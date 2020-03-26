package com.demo.messageprocess.Repository;

import com.demo.messageprocess.model.Message;
import com.demo.messageprocess.model.State;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message,String> {
    List<Message> findAllByReceiverAndState(String receiver, State state);
    List<Message> findAllByReceiver(String receiver);
}
