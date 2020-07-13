package com.chen.Cases;

import com.chen.Utils.AuthenticationUtils;
import com.chen.Utils.ExcelUtils;
import com.chen.Utils.HttpUtils;
import com.pojo.Api;
import com.pojo.Case;
import org.testng.annotations.DataProvider;

public class ToList {
    @org.testng.annotations.Test(dataProvider = "datas")
    public void test11(Api api, Case cases) throws Exception {
        System.out.println("---"+api.getContentType()+"---");
        String url=api.getUrl();
        String type=api.getMethod();
        String params=cases.getParams();
        String conent=api.getContentType();
        String cell = HttpUtils.call(url, type, params, conent,true);
        AuthenticationUtils.gettoken(cell);
        System.out.println(cell);


    }
    /**
     * 数据提供者，需要测试哪个Api就传入指定的ApiId，getApiAndCaseById方法会把两张表里面的接口和用例关联起来
     * @return
     */
    @DataProvider(name = "datas")
    public Object [][] datas()  {
        Object[][] apiAndCaseById = ExcelUtils.getApiAndCaseById("5");


        return apiAndCaseById;



    }
}
