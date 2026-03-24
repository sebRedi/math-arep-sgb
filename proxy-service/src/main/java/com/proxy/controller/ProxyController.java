package com.proxy.controller;

import com.proxy.service.ProxyForwardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ProxyController {

    private final ProxyForwardService proxyForwardService;

    public ProxyController(ProxyForwardService proxyForwardService) {
        this.proxyForwardService = proxyForwardService;
    }

    @GetMapping("/proxy/health")
    public ResponseEntity<String> proxyHealth() {
        return ResponseEntity.ok(proxyForwardService.healthStatus());
    }

    
    @GetMapping("/api/proxy/linealSearch")
    public ResponseEntity<String> linealSearch(@RequestParam String list, @RequestParam int value) {
        String response = proxyForwardService.forward("/api/math/linealSearch?list=" + list + "&value=" + value);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/proxy/binarySearch")
    public ResponseEntity<String> binarySearch(@RequestParam String list, @RequestParam int value) {
        String response = proxyForwardService.forward("/api/math/binarySearch?list=" + list + "&value=" + value);
        return ResponseEntity.ok(response);
    }
}