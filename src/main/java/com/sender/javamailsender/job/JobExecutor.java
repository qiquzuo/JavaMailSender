package com.sender.javamailsender.job;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class JobExecutor {
    private static final int DEFAULT_POOL_SIZE = 10;

    private ThreadPoolTaskExecutor taskExecutor;

    public JobExecutor() {
        this(DEFAULT_POOL_SIZE);
    }

    public JobExecutor(int poolSize) {
        taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(poolSize);
        taskExecutor.setMaxPoolSize(poolSize);
        taskExecutor.setQueueCapacity(100);
        taskExecutor.initialize();
    }

    public void execute(Job job) {
        taskExecutor.execute(() -> {
            job.execute();
        });
    }

    public void shutdown() {
        taskExecutor.shutdown();
    }
}