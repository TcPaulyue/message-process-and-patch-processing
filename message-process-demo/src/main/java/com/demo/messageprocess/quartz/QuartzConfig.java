package com.demo.messageprocess.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail testQuartz2() {
        return JobBuilder.newJob(QuartzTask.class).withIdentity("QuartzTask").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger2() {
        //cron方式，每隔一小时执行一次
        return TriggerBuilder.newTrigger().forJob(testQuartz2())
                .withIdentity("QuartzTask")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 * * *  ?"))
                .build();
    }

}
