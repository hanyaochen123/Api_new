package com.chen.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.pojo.Api;
import com.pojo.Case;
import com.pojo.WriteData;
import org.apache.poi.ss.usermodel.*;

import static com.chen.Utils.Contants.EXCEL_PATH;

public class ExcelUtils {
    //利用静态加载提前把所有数据全部加载出来且加载一次
    public static List<Api> apiList = ExcelUtils.read(0,Api.class);
    public static List<Case> caseList = ExcelUtils.read(1,Case.class);

    /**
     * 回写的数据先存到writeDataList里面然后再一次性回写到表里
     */
    public static List<WriteData> writeDataList =new ArrayList<>();

    /**
     * 根据实体类传入的值设置行号列号，设置回写的对象是接口返回的body，行号是用例编号，列号是写死的第六列
     */
    public static void writeRead(){
        FileInputStream fileInputStream=null;
        FileOutputStream fileOutputStream=null;
        try {
             fileInputStream=new FileInputStream(EXCEL_PATH);
             Workbook workbook= WorkbookFactory.create(fileInputStream);
             //获取第二个表单
            Sheet sheetAt = workbook.getSheet("用例");
            //读取存到里面的数据，然后循环设置
            for (WriteData writeData : writeDataList) {
                //设置行号
                int rowNum = writeData.getRowNum();
                Row row = sheetAt.getRow(rowNum);
                //设置列号
                int cellNum = writeData.getCellNum();
                Cell cell = row.getCell(cellNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellType(CellType.STRING);
                //设置body的值，然后回写到Excel
                cell.setCellValue(writeData.getContent());
                fileOutputStream = new FileOutputStream(EXCEL_PATH);
                workbook.write(fileOutputStream);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }if (fileOutputStream !=null){
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    /**
     * 判断传入的ApiId把两张表的apiid对应起来，让他们拥有关系，类似于数据库的外键
     * @param apiId
     * @return
     */
    public static Object[][] getApiAndCaseById(String apiId){
        List<Case> wantCase = new ArrayList<>();
        Api wantApi=null;
        //循环判断传入的apiId然后取出来放到wantApi里面
        for(Api api:apiList){
            if (apiId.equalsIgnoreCase(api.getId())){
                wantApi = api;
            }
        }
        //循环判断传入的ApiId取出来放到List里因为一个ApiId对应多个Case
        for (Case c:caseList){
            if (apiId.equalsIgnoreCase(c.getApiId())){
                wantCase.add(c);
            }
        }
        //然后把取出来的数据放到二维数组里，给testng提供数据
        Object [][] datas=new Object[wantCase.size()][2];
        for (int i = 0; i < wantCase.size(); i++) {
            datas[i][0]=wantApi;
            datas[i][1]=wantCase.get(i);

        }
        return datas;
    }
    /**
     * 利用泛型，传入Excel表单的坐标和实体类
     * @param index
     * @param clazz
     * @param <E>
     * @return
     */
    public static <E> List<E> read(int index,Class<E> clazz) {
        FileInputStream fileInputStream=null;
        try {
             fileInputStream = new FileInputStream(EXCEL_PATH);
            ImportParams params = new ImportParams();
            params.setStartSheetIndex(index);
            List<E> apis = ExcelImportUtil.importExcel(fileInputStream, clazz, params);
            return apis;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (fileInputStream != null) {

                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 无用的demo代码
     * @throws Exception
     */
    public static void reads() throws Exception {
        FileInputStream fileInputStream=new FileInputStream("src/main/resources/eason.xlsx");
        ImportParams params=new ImportParams();
        params.setStartSheetIndex(1);
        List<Case> Case = ExcelImportUtil.importExcel(fileInputStream, Case.class, params);
        for (com.pojo.Case api : Case) {
            System.out.println(api);
        }


    }

//    public static Object[][] read(){
//        Object[][]datas=null;
//        FileInputStream fileInputStream=null;
//        try {
//            fileInputStream=new FileInputStream("src/main/resources/eason.xlsx");
//            Workbook workbook = WorkbookFactory.create(fileInputStream);
//            Sheet sheet = workbook.getSheet("sheet");
//            int lastRowNum = sheet.getLastRowNum();
//            datas=new Object[lastRowNum][4];
//            for (int i = 1; i <= lastRowNum; i++) {
//                Row row=sheet.getRow(i);
//                Cell urlcell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//                urlcell.setCellType(CellType.STRING);
//                String urlCellvalue = urlcell.getStringCellValue();
//                datas[i-1][0]=urlCellvalue;
//
//                Cell typecell = row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//                typecell.setCellType(CellType.STRING);
//                String typecellvalue = typecell.getStringCellValue();
//                datas[i-1][1]=typecellvalue;
//
//                Cell paramscell = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//                paramscell.setCellType(CellType.STRING);
//                String paramscellvalue = paramscell.getStringCellValue();
//                datas[i-1][2]=paramscellvalue;
//
//                Cell conentcell = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
//                conentcell.setCellType(CellType.STRING);
//                String conentcellvalue = conentcell.getStringCellValue();
//                datas[i-1][3]=conentcellvalue;
//
//
//
//                //System.out.println("id"+idcellvalue+"---url---"+urlCellvalue+"---type---"+typecellvalue+"---params---"+paramscellvalue+"---conent-type---"+conentcellvalue);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                fileInputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//        return datas;
//
//    }

    public static void main(String[] args) {
        Object[][] apiAndCaseById = getApiAndCaseById("1");
        for (Object[] objects : apiAndCaseById) {
            System.out.println(Arrays.toString(objects));
        }


    }
}
