package com.thezlotnicks.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.thezlotnicks.config.QueueConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MessageSenderService {
    private static final Logger logger = LoggerFactory.getLogger(MessageSenderService.class);

    @Autowired
    private QueueConfig queueConfig;

    public void sendMessage(String message) {
        logger.info("Sending the message: " + message);
        AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion(queueConfig.getRegion()).build();
        sqs.sendMessage(new SendMessageRequest(queueConfig.getQueueURL(), "YOLO"));
    }
}
