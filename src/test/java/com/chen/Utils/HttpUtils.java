package com.chen.Utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

public class HttpUtils {
    /**
     * restful风格的get请求，比如127.0.0.1/user/1
     * @param url
     * @param istoken
     * @return
     * @throws Exception
     */
    public static String restget(String url,boolean istoken) throws Exception {
        HttpGet get=new HttpGet(url);
        CloseableHttpClient aDefault = HttpClients.createDefault();
        if (istoken){
            AuthenticationUtils.istokenSetHeaders(get);
        }
        long l = System.currentTimeMillis();
        CloseableHttpResponse execute = aDefault.execute(get);
        long l1 = System.currentTimeMillis();
        System.out.println("接口的相应数值为"+(l-l1)+"ms");
        HttpEntity entity = execute.getEntity();
        StatusLine statusLine = execute.getStatusLine();
        System.out.println(statusLine.getStatusCode());
        System.out.println(entity);
        String s = EntityUtils.toString(entity, "UTF-8");
        return s;

    }

    /**
     * 普通风格的get请求，比如127.0.0.1/user?username=hyc&password=123456
     * @param url
     * @param params
     * @param istoken
     * @return
     * @throws IOException
     */
    public static String get(String url,String params,boolean istoken) throws IOException {
        HttpGet get=new HttpGet(url+"?"+params);
        CloseableHttpClient aDefault = HttpClients.createDefault();
        if (istoken){
            AuthenticationUtils.istokenSetHeaders(get);
        }
        long l = System.currentTimeMillis();
        CloseableHttpResponse execute = aDefault.execute(get);
        long l1 = System.currentTimeMillis();
        System.out.println("接口的相应数值为"+(l-l1)+"ms");
        HttpEntity entity = execute.getEntity();
        String s = EntityUtils.toString(entity, "UTF-8");
        StatusLine statusLine = execute.getStatusLine();
        System.out.println(statusLine.getStatusCode());
        System.out.println(entity);
        return s;
    }

    /**
     * form-data格式的入参
     * @param url
     * @param params
     * @param istoken
     * @return
     * @throws Exception
     */
    public static String formPost(String url,String params,boolean istoken) throws Exception {
        HttpPost post=new HttpPost(url);
        post.setHeader(Contants.HEAD_POST_KEY,Contants.HEAD_POST_FORM_VALUE);
        if (istoken){
            AuthenticationUtils.istokenSetHeaders(post);
        }
        post.setEntity(new StringEntity(params));
        CloseableHttpClient aDefault = HttpClients.createDefault();
        CloseableHttpResponse execute = aDefault.execute(post);
        HttpEntity entity = execute.getEntity();
        String s = EntityUtils.toString(entity, "utf-8");
        int statusCode = execute.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        System.out.println(s);
        return s;

    }

    public static String jsonPost(String url,String params,boolean istoken) throws Exception{
        HttpPost post=new HttpPost(url);
        post.setHeader(Contants.HEAD_POST_KEY,Contants.HEAD_POST_JSON_VALUE);
        if (istoken){
            AuthenticationUtils.istokenSetHeaders(post);
        }
        post.setEntity(new StringEntity(params,"UTF-8"));
        CloseableHttpClient aDefault = HttpClients.createDefault();
        CloseableHttpResponse execute = aDefault.execute(post);
        HttpEntity entity = execute.getEntity();
        String s = EntityUtils.toString(entity);
        System.out.println(s);
        return s;

    }

    public static String jsonpatch(String url,String params,boolean istoken) throws Exception {
        HttpPatch patch=new HttpPatch(url);
        patch.setHeader(Contants.HEAD_POST_KEY,Contants.HEAD_POST_JSON_VALUE);
        if (istoken){
            AuthenticationUtils.istokenSetHeaders(patch);
        }
        patch.setEntity(new StringEntity(params,"UTF-8"));
        CloseableHttpClient aDefault = HttpClients.createDefault();
        CloseableHttpResponse execute = aDefault.execute(patch);
        String s = EntityUtils.toString(execute.getEntity());
        int statusCode = execute.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        System.out.println(s);
        return s;

    }

    public static String formvalue(String json){
        String result="";
        HashMap<String,String> hashMap = JSONObject.parseObject(json, HashMap.class);
        Set<String> strings = hashMap.keySet();
        for (String string : strings) {
            String value = hashMap.get(string);
            result+=string+"="+value+"&";
        }
        result=result.substring(0,result.length()-1);
        System.out.println(result);
        return result;

    }

    public static String call(String url,String type,String params,String conent,boolean istoken)  {
        System.out.println("---"+conent+"---");
        try {
            if ("json".equalsIgnoreCase(conent)) {
                if ("post".equalsIgnoreCase(type)) {
                    return HttpUtils.jsonPost(url, params,istoken);
                } else if ("get".equalsIgnoreCase(type)) {
                    return HttpUtils.restget(url,istoken);
                } else if ("patch".equalsIgnoreCase(type)) {
                    return HttpUtils.jsonpatch(url, params,istoken);
                }
            } else if ("form".equalsIgnoreCase(conent)) {
                if ("post".equalsIgnoreCase(type)) {
                    params = formvalue(params);
                    return HttpUtils.formPost(url, params,istoken);
                } else if ("get".equalsIgnoreCase(type)) {
                    return HttpUtils.get(url, params,istoken);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;


    }

    public static void main(String[] args) throws Exception {
        HttpUtils.formPost("http://106.12.120.83/user/login","username=hyc&password=123456",true);
    }
}
