package com.thezlotnicks.service;

//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.thezlotnicks.config.QueueConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueueReaderService {
    private static final Logger logger = LoggerFactory.getLogger(QueueReaderService.class);

    @Autowired
    private QueueConfig queueConfig;

    public void getMessages() {
        // Make sure that your bash environment variables for AWS_SECRET_ACCESS_KEY and AWS_ACCESS_KEY_ID are set or use the commented out lines and hopefully store your creds in vault
        logger.info("Getting messages...");
        //BasicAWSCredentials bAWSc = new BasicAWSCredentials("AWS_ACCESS_KEY_ID", "AWS_SECRET_ACCESS_KEY");
        //AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion(Regions.US_WEST_2).withCredentials(new AWSStaticCredentialsProvider(bAWSc)).build();
        AmazonSQS sqs = AmazonSQSClientBuilder.standard().withRegion(queueConfig.getRegion()).build();


        ReceiveMessageRequest receive_request = new ReceiveMessageRequest()
                .withQueueUrl(queueConfig.getQueueURL())
                .withWaitTimeSeconds(20);
        List<Message> messages = sqs.receiveMessage(receive_request).getMessages();
        for (Message message : messages) {
            logger.info(message.toString());
            sqs.deleteMessage(queueConfig.getQueueURL(), message.getReceiptHandle());
        }
        logger.info("Done getting messages...");
    }
}
