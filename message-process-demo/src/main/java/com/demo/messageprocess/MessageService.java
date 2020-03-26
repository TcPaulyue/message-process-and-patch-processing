package com.demo.messageprocess;


import com.demo.messageprocess.Repository.AsyncMessageRepository;
import com.demo.messageprocess.Repository.MessageRepository;
import com.demo.messageprocess.Repository.UserRepository;
import com.demo.messageprocess.model.Message;
import com.demo.messageprocess.model.State;
import com.demo.messageprocess.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final AsyncMessageRepository asyncMessageRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public Flux<Message> getAllMessages(){
        return asyncMessageRepository.findAll();
    }

    public List<Message> getMessageByUserName(String user){
        return messageRepository.findAllByReceiver(user);
    }

    public void checkMessage(String receiver){
        List<Message> messageList = messageRepository.findAllByReceiverAndState(receiver, State.UNCHECKED);
        for(Message message:messageList){
            message.setState(State.CHECKED);
            messageRepository.save(message);
        }
    }

    public void sendMessageToGroup(String sender, String  content, String userGroup){
        List<User> users = userRepository.findAllByUserGroup(userGroup);
        users.forEach(user -> {
            Message message = new Message();
            message.setState(State.UNCHECKED);
            message.setSender(sender);
            message.setReceiver(user.getName());
            message.setContent(content);
            asyncMessageRepository.save(message);
        });
    }
}
