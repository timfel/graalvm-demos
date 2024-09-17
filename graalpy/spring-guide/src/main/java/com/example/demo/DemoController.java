package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

@Controller
public class DemoController {
    @Autowired
    SentimentAnalysisService sentimentAnalysisService; // ①

    @GetMapping("/")
    public String answer() { // ②
        return "index";
    }

    @GetMapping(value = "/analyze", produces = "application/json")
    @ResponseBody
    public Map<String, Double> answer(@RequestParam String text) { // ③
        return sentimentAnalysisService.getSentimentScore(text); // ④
    }
}
