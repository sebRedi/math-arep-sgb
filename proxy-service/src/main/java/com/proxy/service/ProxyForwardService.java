package com.proxy.service;

import com.proxy.model.MathResponse;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ProxyForwardService {

    private final String activeBaseUrl;
    private final String passiveBaseUrl;

    public ProxyForwardService() {
        this.activeBaseUrl = getEnvOrDefault("MATH_SERVICE_ACTIVE_URL", "ec2-32-192-239-202.compute-1.amazonaws.com");
        this.passiveBaseUrl = getEnvOrDefault("MATH_SERVICE_PASSIVE_URL", "ec2-32-193-242-81.compute-1.amazonaws.com");
    }

    public String forward(String pathAndQuery) {
        try {
            return doGet(activeBaseUrl + pathAndQuery);
        } catch (Exception activeException) {
            try {
                return doGet(passiveBaseUrl + pathAndQuery);
            } catch (Exception passiveException) {
                throw new RuntimeException(
                    "Both math services are unavailable. Active error: "
                    + activeException.getMessage()
                    + " Passive error: "
                    + passiveException.getMessage()
                );
            }
        }
    }

    public String healthStatus() {
        StringBuilder response = new StringBuilder();

        response.append("ACTIVE(").append(activeBaseUrl).append("): ");
        response.append(checkHealth(activeBaseUrl));
        response.append(" | PASSIVE(").append(passiveBaseUrl).append("): ");
        response.append(checkHealth(passiveBaseUrl));

        return response.toString();
    }

    private String checkHealth(String baseUrl) {
        try {
            return doGet(baseUrl + "/health");
        } catch (Exception e) {
            return "DOWN";
        }
    }

    private String doGet(String targetUrl) throws Exception {
        URL url = new URL(targetUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(2000);
        connection.setReadTimeout(2000);

        int responseCode = connection.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("HTTP error code: " + responseCode);
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();
        return response.toString();
    }

    private String getEnvOrDefault(String envName, String defaultValue) {
        String value = System.getenv(envName);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return value;
    }
}