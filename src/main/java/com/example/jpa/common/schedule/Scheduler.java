package com.example.jpa.common.schedule;

import com.example.jpa.logs.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class Scheduler {

    private final LogService logService;

//    @Scheduled(cron = "0 0 4 * * *")
    @Scheduled(fixedDelay = 1000 * 60)
    public void deleteLog() {
        log.info("##########################");
        log.info("스케쥴 실행");
        logService.deleteLog();
    }
}
