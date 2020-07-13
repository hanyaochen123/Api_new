package com.chen;


import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Set;

public class Test2{
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

    public static void main(String[] args) {
        String json="{\"status\":\"0\",\"data\":\"d67fc569-731d-459e-acd3-d66fbf7f13d6\",\"message\":\"登陆成功\"}";
        formvalue(json);
    }

}
