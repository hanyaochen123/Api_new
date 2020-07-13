package com.chen.Cases;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.chen.Utils.AuthenticationUtils;
import com.chen.Utils.Contants;
import com.chen.Utils.ExcelUtils;
import com.chen.Utils.HttpUtils;
import com.pojo.Api;
import com.pojo.Case;
import com.pojo.JsonPath;
import com.pojo.WriteData;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import java.util.List;

public class Test {




    @org.testng.annotations.Test(dataProvider = "datas")
    public void test11(Api api, Case cases) throws Exception {

        String url=api.getUrl();
        String type=api.getMethod();
        String params=cases.getParams();
        String conent=api.getContentType();
        String body = HttpUtils.call(url, type, params, conent,true);
        AuthenticationUtils.gettoken(body);
        boolean b = assertResponse(cases, body);
        System.out.println(b);
        if (b){
            System.out.println("断言已通过");
        }else {
            System.out.println("断言未通过");
        }
        int RowNum= Integer.parseInt(cases.getId());
        int CellNum= Contants.FULL_CELL;
        WriteData writeData=new WriteData(RowNum,CellNum,body);
        ExcelUtils.writeDataList.add(writeData);

    }

    public boolean assertResponse(Case cases, String body) {
        boolean blog=false;
        String expectValue = cases.getExpectValue();
        List<JsonPath> jsonPaths = JSONObject.parseArray(expectValue, JsonPath.class);
        for (JsonPath jsonPath : jsonPaths) {
            String function = jsonPath.getFunction();
            String value = jsonPath.getValue();
            Object read =  JSONPath.read(body, function);
            blog=value.equals(read);
            if (!blog){
                break;
            }
        }
        return blog;
    }

    @AfterSuite
    public void toWrite(){
        ExcelUtils.writeRead();

    }
    @DataProvider(name = "datas")
    public Object [][] datas()  {
        Object[][] apiAndCaseById = ExcelUtils.getApiAndCaseById("1");


        return apiAndCaseById;



    }
}
