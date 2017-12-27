package com.waterhub.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class AdminController {

    private static final String BASE_URL = "http://localhost:8080/api/";
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;

    public AdminController() {
        objectMapper = new ObjectMapper();
        restTemplate = new RestTemplate();
    }
}
