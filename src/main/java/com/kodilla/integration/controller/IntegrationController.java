package com.kodilla.integration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@RestController
public class IntegrationController {
    Random random = new Random();


    @GetMapping("/create/{fileContent}")
    public String create(@PathVariable String fileContent) {

        String num = String.valueOf(random.nextInt());
        String fileName = "data/input/input" + num + ".txt";
        Path file = Paths.get(fileName);
        try {
            Files.writeString(file, fileContent, StandardCharsets.UTF_8);
            return "Writing";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String generateNum() {
        StringBuilder random = new StringBuilder();
        return random.toString();
    }


}
