package com.sender.javamailsender.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailJob implements Job {
    private static final Logger logger = LoggerFactory.getLogger(EmailJob.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.from}")
    private String from;

    @Value("${mail.to}")
    private String to;

    @Override
    public void execute() {
        logger.info("开始发送邮件...");
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject("测试邮件");
            message.setText("这是一封测试邮件。");
            mailSender.send(message);
            logger.info("邮件发送成功！");
        } catch (Exception e) {
            logger.error("邮件发送失败！", e);
        }
    }
}