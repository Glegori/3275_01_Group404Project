package com.csis3275.Group404Project;

import com.csis3275.Group404Project.model.Expense;
import com.sun.rowset.internal.Row;
import org.apache.tomcat.jni.File;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.util.List;

import com.csis3275.Group404Project.dao.expenseDAO;
import org.springframework.beans.factory.annotation.Autowired;

public class excelConverter {
    private static Object Workbook;

    @Autowired
    public static File getExcelFile(List<Expense> approved){
        try{
            String filename = "data.xlsx";
            File file = new File();
            XSSFWorkbook excelWB = new XSSFWorkbook();
            XSSFSheet sheet = excelWB.createSheet("Expenses");
            Row row;

        }catch (IOException e){

        }
        return null;
    }



}
