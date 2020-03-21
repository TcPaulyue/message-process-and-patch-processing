package com.demo.messageprocess.quartz;

import com.demo.messageprocess.MessageService;
import lombok.RequiredArgsConstructor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
public class QuartzTask extends QuartzJobBean {
    private final MessageService messageService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
         messageService.getAllMessages().subscribe(message ->
                 System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                         + message.toString()));
    }
}
