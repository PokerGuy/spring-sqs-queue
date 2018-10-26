# Behold the SQS Read Messages and Send Them Too-inator

This is a pretty simple and dumb little Spring Boot application that exists to send a message to an AWS SQS Queue and to read messages from said queue.
This sample does not cover creating the queue which should be done in Terraform or Cloudformation, but to get going you can go to the AWS Console
and do it fairly quickly.

To install and get it running:
```
git clone https://github.com/PokerGuy/spring-sqs-queue.git
cd spring-sqs-queue
```

Go to the resources/application.properties file and modify the QUEUE_URL and REGION to match your specific setup.

```
export AWS_ACCESS_KEY_ID=<Your access key>
export AWS_SECRET_ACCESS_KEY=<Your secret key>
mvn clean package
mvn spring-boot:run
```

And that's it. Each long poll lasts 20 seconds. If you were to make a request every 20 seconds for a year, this application would cost you about 60 cents a year.
That right there is the reason I love AWS.

**NOTE:** Assumes Java 8 and Maven installed.
