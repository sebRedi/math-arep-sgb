package com.arep.model;
import java.util.ArrayList;

public class MathResponse {

    private String operation;
    private String input;
    private String value;
    private String result;
    private String instance;

    public MathResponse() {
    }

    public MathResponse(String operation, String input, String value, String result, String instance) {
        this.operation = operation;
        this.input = input;
        this.value = value;
        this.result = result;
        this.instance = instance;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }
}