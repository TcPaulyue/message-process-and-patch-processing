package com.demo.batchprocessing;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class Controller {

    private final DemoService demoService;

    @RequestMapping("/invokeMessage")
    public String handleMessage() throws Exception {
        log.info("invoke");
        demoService.updateMessageFlux();
        return "message processing has been invoked";
    }
    @RequestMapping("/invokeUser")
    public String handleUser() throws Exception {
        log.info("invoke");
        demoService.updateUserFlux();
        return "user processing has been invoked";
    }
}
