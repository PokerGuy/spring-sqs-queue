package com.thezlotnicks;

import com.thezlotnicks.service.MessageSenderService;
import com.thezlotnicks.service.QueueReaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    private QueueReaderService queueReaderService;

    @Autowired
    private MessageSenderService messageSenderService;

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Application started, bitches");
        SpringApplication.run(Application.class, args);
        logger.info("Application is finished...");
    }

    @Override
    public void run(String... args) {
        logger.info("Running...");
        // Send a message to the queue showing how to use the messageSenderService
        messageSenderService.sendMessage("YOLO!");

        // Loop forever polling the queue using long polling
        while (1 == 1) {
            logger.info("Calling queue reader service...");
            queueReaderService.getMessages();
        }
    }
}
