package com.exercice.threadsDemo.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolTaskExecutorConfig {
    @Value(value = "${thread.executor.setCorePoolSize}")
    private int coorPoolSize;
    @Value(value = "${thread.executor.setMaxPoolSize}")
    private int maxPoolSize;
    @Value(value = "${thread.executor.setQueueCapacity}")
    private int queueCapacity;
    @Value(value = "${thread.executor.setThreadNamePrefix}")
    private String threadNamePrefix;

    @Bean(name = "logExecutor")
    public ThreadPoolTaskExecutor logExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(coorPoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.initialize();
        return executor;
        }
}
