package com.chen.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONWriter;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;

public class MycookiePost {
    private String url;
    private ResourceBundle bundle;
    private CookieStore cookieStore;


    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }
    @Test
    public  void test() throws IOException {

        String cookie=bundle.getString("test.cookie");
        String result=url+cookie;

        HttpGet get=new HttpGet(result);

        DefaultHttpClient httpClient=new DefaultHttpClient();

        HttpResponse response=httpClient.execute(get);

        String s = EntityUtils.toString(response.getEntity(), "utf-8");

        cookieStore= httpClient.getCookieStore();
        List<Cookie> cookies = cookieStore.getCookies();
        for (Cookie cookie1 : cookies) {
            String name = cookie1.getName();
            String value = cookie1.getValue();
            System.out.println(name+value);
            System.out.println(cookie1);
        }
        System.out.println(s);
    }
    @Test(dependsOnMethods = {"test"})
    public void post() throws IOException {
        Map<String,String> map=new HashMap<>();
        String login = bundle.getString("test.post.login");
        String result = url+login;

        DefaultHttpClient client=new DefaultHttpClient();
        client.setCookieStore(cookieStore);
        HttpPost post=new HttpPost(result);

        map.put("user","hyc");
        map.put("pwd","123");
        JSONObject jsonObject1=new JSONObject(map);
        post.setHeader("content-type","application/json");
        StringEntity stringEntity=new StringEntity(jsonObject1.toString(),"utf-8");
        post.setEntity(stringEntity);
        HttpResponse httpResponse=client.execute(post);
        String s = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        JSONObject jsonObject=new JSONObject(s);
        System.out.println(jsonObject.get("data"));
        System.out.println(s);


    }
}
