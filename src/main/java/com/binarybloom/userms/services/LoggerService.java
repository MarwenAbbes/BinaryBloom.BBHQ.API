package com.binarybloom.userms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {

    private static final Logger logger = LoggerFactory.getLogger(LoggerService.class);

    public void info(String msg) {
        logger.info(msg);
    }

    public void error(String msg) {
        logger.error(msg);
    }
}
