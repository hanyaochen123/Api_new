package com.chen;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Test {
    
    public  void test1() throws IOException {

        HttpGet get=new HttpGet("http://www.baidu.com");
        CloseableHttpClient aDefault = HttpClients.createDefault();
        long l = System.currentTimeMillis();
        CloseableHttpResponse execute = aDefault.execute(get);
        long l1 = System.currentTimeMillis();
        System.out.println("接口的相应数值为"+(l-l1)+"ms");
        HttpEntity entity = execute.getEntity();
        StatusLine statusLine = execute.getStatusLine();
        System.out.println(statusLine.getStatusCode());
        System.out.println(entity);

    }
    public void test2() throws Exception {
        HttpPost post=new HttpPost("http://106.12.120.83/user/login");
        post.setHeader("Content-Type","application/x-www-form-urlencoded");

        post.setEntity(new StringEntity("username=hyc&password=123456"));
        CloseableHttpClient aDefault = HttpClients.createDefault();
        CloseableHttpResponse execute = aDefault.execute(post);
        HttpEntity entity = execute.getEntity();
        String s = EntityUtils.toString(entity, "utf-8");
        System.out.println(s);
    }

    public static void main(String[] args) throws Exception {
        Test test=new Test();
        test.test2();
    }



}
