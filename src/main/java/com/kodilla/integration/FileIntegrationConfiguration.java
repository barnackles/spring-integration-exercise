package com.kodilla.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.StandardIntegrationFlow;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;

import java.io.File;

@Configuration
@EnableIntegration
public class FileIntegrationConfiguration {

    @Bean
    StandardIntegrationFlow fileIntegrationFlow(
            FileReadingMessageSource fileAdapter,
            FileWritingMessageHandler outputFileHandler) {
        //indicate how to initiate
        return IntegrationFlow.from(fileAdapter, config -> config.poller(Pollers.fixedDelay(1000)))
                .handle(outputFileHandler)
                .get();
    }

    @Bean
    FileReadingMessageSource fileAdapter() {
        FileReadingMessageSource fileSource = new FileReadingMessageSource();
        fileSource.setDirectory(new File("data/input"));

        return fileSource;
    }



    @Bean
    FileWritingMessageHandler outputFileAdapter() {
        File directory = new File("data/output/");
        SpelExpressionParser parser = new SpelExpressionParser();
        System.out.println(parser.parseExpression("headers.targetPath"));

        FileWritingMessageHandler handler = new FileWritingMessageHandler(directory);
        //append to already existing file
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        //indicate file name
        handler.setFileNameGenerator(message -> "result.csv");
        handler.setExpectReply(false);

        return handler;
    }

}