package com.chen.Utils;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MycookieForGet {
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
    @Test
    public void yilaiTest() throws IOException {
        String view=bundle.getString("test.get.list");
        String result=url+view;
        System.out.println(result);

        HttpGet get=new HttpGet(result);
        DefaultHttpClient httpClient=new DefaultHttpClient();
        httpClient.setCookieStore(cookieStore);
        HttpResponse execute = httpClient.execute(get);
        String s = EntityUtils.toString(execute.getEntity(), "utf-8");
        StatusLine statusLine = execute.getStatusLine();
        System.out.println(statusLine);
        System.out.println(s);


    }
}
