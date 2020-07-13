package com.pojo;

public class JsonPath {
    private String function;
    private String value;

    public JsonPath(String function, String value) {
        this.function = function;
        this.value = value;
    }

    public JsonPath(){

    }

    @Override
    public String toString() {
        return "JsonPath{" +
                "function='" + function + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
