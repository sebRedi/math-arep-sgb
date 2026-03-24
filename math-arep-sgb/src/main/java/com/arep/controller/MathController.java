package com.arep.controller;

import com.arep.model.MathResponse;
import com.arep.service.MathOperationsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MathController {

    private final MathOperationsService mathOperationsService;

    @Value("${server.port}")
    private String port;

    public MathController(MathOperationsService mathOperationsService) {
        this.mathOperationsService = mathOperationsService;
    }

    @GetMapping("/health")
    public String health() {
        return "OK from math-service on port " + port;
    }

    @GetMapping("/api/math/linealSearch")
    public MathResponse linealSearch(@RequestParam String list, @RequestParam int value) {
        int result = mathOperationsService.linearSearch(list, value);
        return new MathResponse("linearSearch", "inputlist : " + list, "value : " + value, "output : " + String.valueOf(result), "math-service:" + port);
    }

    @GetMapping("/api/math/binarySearch")
    public MathResponse binarySearch(@RequestParam String list, @RequestParam int value) {
        int result = mathOperationsService.binarySearch(list, value);
        return new MathResponse("binarySearch", "inputlist : " + list, "value : " + value, "output : " + String.valueOf(result), "math-service:" + port);
    }
}