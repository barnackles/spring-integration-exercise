package com.kodilla.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlowBuilder;
import org.springframework.integration.file.FileReadingMessageSource;

import java.io.File;

@Configuration
public class FileIntegrationConfiguration {

    @Bean
    IntegrationFlowBuilder fileIntegrationFlow(FileReadingMessageSource fileAdapter) {
        return IntegrationFlow.from(fileAdapter)
                .tr
    }
    @Bean
    FileReadingMessageSource fileAdapter() {
        FileReadingMessageSource fileSource = new FileReadingMessageSource();
        fileSource.setDirectory(new File("data/input"));

        return fileSource;
    }

}