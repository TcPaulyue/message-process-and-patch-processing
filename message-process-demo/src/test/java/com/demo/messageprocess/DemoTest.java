package com.demo.messageprocess;

import com.demo.messageprocess.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageService messageService;

    @Before
    public void beforeTest(){
        User user = new User();
        user.setName("A1");
        user.setUserGroup("GROUP_A");
        userRepository.save(user);
        user = new User();
        user.setName("A2");
        user.setUserGroup("GROUP_A");
        userRepository.save(user);
        user = new User();
        user.setName("A3");
        user.setUserGroup("GROUP_A");
        userRepository.save(user);
        user = new User();
        user.setName("B1");
        user.setUserGroup("GROUP_B");
        userRepository.save(user);
        user = new User();
        user.setName("B2");
        user.setUserGroup("GROUP_B");
        userRepository.save(user);
        user = new User();
        user.setName("B3");
        user.setUserGroup("GROUP_B");
        userRepository.save(user);
    }

    @Test
    public void testSendMessage(){
        messageService.sendMessageToGroup("A1","A1 send message to Group_B","GROUP_B");
        messageService.getAllMessages().subscribe(message -> System.out.println(message));
    }
}
