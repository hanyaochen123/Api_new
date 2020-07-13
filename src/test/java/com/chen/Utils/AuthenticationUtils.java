package com.chen.Utils;

import com.alibaba.fastjson.JSONPath;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.springframework.boot.test.json.JsonbTester;

import java.awt.image.ImageProducer;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationUtils {
    /**
     * token和需要传入下一个接口的数据都在这个map里存着,如果下个接口需要上一个接口的数据，就在gettoken方法里put就行了
     */
    public static final Map<String,String> token=new HashMap<>();

    public static void main(String[] args) {
        String s="{\"status\":\"0\",\"data\":\"62b0ef65-1e8e-4a08-bf93-b5147ee83dde\",\"message\":\"登陆成功\"}";
        gettoken(s);
        String token = AuthenticationUtils.token.get("token");
        System.out.println(token);
    }

    /**
     * 登录鉴权，如果下一个接口需要上一个接口的id或者其他字段，直接下面代码增加if判断即可
     * * @param response
     */
    public static void gettoken(String response){
        Object read = JsonPath.read(response, "$.data");
        if (read!=null){
            token.put("token",read.toString());
        }
    }

    /**
     * 判断在请求中是否存在token值，如果存在就设置到header里
     * @param request
     */
    public static void istokenSetHeaders(HttpRequest request){
        String token = AuthenticationUtils.token.get("token");
        System.out.println("token为:"+ AuthenticationUtils.token);
        if (StringUtils.isNotBlank(token)){
            request.setHeader("Authorization", token);
        }


    }
}
