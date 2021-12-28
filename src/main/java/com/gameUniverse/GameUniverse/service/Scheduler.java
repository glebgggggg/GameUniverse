package com.gameUniverse.GameUniverse.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class Scheduler {
    @Scheduled(fixedRate = 60000, fixedDelay = 600000)
    @CacheEvict({"bookCache", "userCache"})
    public void clearCache() {
        log.info("CACHE log :::::::::::::::::::::::::> listCache was cleared!");
    }

}
