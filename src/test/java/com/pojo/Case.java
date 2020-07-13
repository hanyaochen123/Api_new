package com.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class Case {
    @Excel(name = "用例编号")
    private String id;
    @Excel(name = "描述")
    private String desc;
    @Excel(name = "参数")
    private String params;
    @Excel(name = "接口编号")
    private String apiId;
    @Excel(name = "期望响应数据")
    private String expectValue;
    @Excel(name = "实际响应数据")
    private String fullValue;

    public Case(String id, String desc, String params, String apiId, String expectValue, String fullValue) {
        this.id = id;
        this.desc = desc;
        this.params = params;
        this.apiId = apiId;
        this.expectValue = expectValue;
        this.fullValue = fullValue;
    }
    public Case(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getExpectValue() {
        return expectValue;
    }

    public void setExpectValue(String expectValue) {
        this.expectValue = expectValue;
    }

    public String getFullValue() {
        return fullValue;
    }

    public void setFullValue(String fullValue) {
        this.fullValue = fullValue;
    }

    @Override
    public String toString() {
        return "Case{" +
                "id='" + id + '\'' +
                ", desc='" + desc + '\'' +
                ", params='" + params + '\'' +
                ", apiId='" + apiId + '\'' +
                ", expectValue='" + expectValue + '\'' +
                ", fullValue='" + fullValue + '\'' +
                '}';
    }
}
