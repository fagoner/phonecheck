package com.deviddle.phonecheck.schedule;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class RutineSchedule {

    @Scheduled(cron = "0 */15 * * * *")
    public void cronJobSch() throws IOException {
        LocalDateTime ld = LocalDateTime.now();
        System.out.println("I do this every 15 minutes: " + ld.toString());
    }

}
