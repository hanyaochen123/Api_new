package com.pojo;


public class WriteData {
    private int RowNum;
    private int CellNum;
    private String content;

    public WriteData(int rowNum, int cellNum, String content) {
        RowNum = rowNum;
        CellNum = cellNum;
        this.content = content;
    }

    public WriteData(){

    }

    @Override
    public String toString() {
        return "WriteData{" +
                "RowNum=" + RowNum +
                ", CellNum=" + CellNum +
                ", content='" + content + '\'' +
                '}';
    }

    public int getRowNum() {
        return RowNum;
    }

    public void setRowNum(int rowNum) {
        RowNum = rowNum;
    }

    public int getCellNum() {
        return CellNum;
    }

    public void setCellNum(int cellNum) {
        CellNum = cellNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
