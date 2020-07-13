package com.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

public class Api {
    @Excel(name = "接口编号")

    private String id;
    @Excel(name = "接口名称")
    private String name;
    @Excel(name = "请求类型")
    private String method;
    @Excel(name = "接口地址")
    private String url;
    @Excel(name = "传参类型")
    private String contentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String toString() {
        return "Api{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
