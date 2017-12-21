package com.waterhub.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waterhub.web.model.MyResponse;
import com.waterhub.web.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class IndexController {

    private static final String BASE_URL = "http://localhost:8080/api/";
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;

    public IndexController() {
        objectMapper = new ObjectMapper();
        restTemplate = new RestTemplate();
    }

    @GetMapping("/")
    public String index(HttpSession httpSession, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute("user");

        modelMap.addAttribute("user", user);
        return "index/dashboard";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("message") String message, HttpSession httpSession, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null)
            return "redirect:/";
        else {
            modelMap.addAttribute("message", message);
            return "index/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession httpSession,
                        RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "user/verify-user";

        HashMap<String, String> params = new HashMap<>();

        params.put("email", email);
        params.put("password", password);

        HttpEntity<HashMap> request = new HttpEntity<>(params);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
        MyResponse<User> myResponse = new MyResponse<>();
        User user = null;

        try {
            myResponse = objectMapper.readValue(response.getBody(), new TypeReference<MyResponse<User>>() {});
            user = myResponse.getData();
        } catch (IOException e) {
            myResponse.setMessage("Internal server error");
        }

        if (user == null) {
            redirectAttributes.addFlashAttribute("message", myResponse.getMessage());
            return "redirect:/login";
        } else {
            httpSession.setAttribute("user", user);
            return "redirect:/";
        }
    }
}
