package com.example.shortlink.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.Semaphore;

@Configuration
public class RateLimiting {
    @Value("${rate.limit.concurrent.requests:10}")
    // Instead of a hard-coded value, I wanna change this to a variable, I will check on this later if I have time
    private int concurrentRequestsLimit;

    @Bean
    public Semaphore requestSemaphore() {
        return new Semaphore(concurrentRequestsLimit);
    }
}