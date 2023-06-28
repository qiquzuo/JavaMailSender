package com.sender.javamailsender;

import com.sender.javamailsender.job.EmailJob;
import com.sender.javamailsender.job.JobExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class JavaMailSenderApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaMailSenderApplication.class, args);
    }

    @Autowired
    private JobExecutor jobExecutor;

    @Autowired
    private EmailJob emailJob;

    @PostConstruct
    public void init() {
        jobExecutor.execute(emailJob);
    }



}
