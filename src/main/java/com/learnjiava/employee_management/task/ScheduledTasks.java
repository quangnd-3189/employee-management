package com.learnjiava.employee_management.task;

import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 30000)
    public void reportCurrentTimeFixedRate() {
        System.out.println("System running");
    }
}
