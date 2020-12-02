package com.csis3275.Group404Project;

import com.csis3275.Group404Project.model.Expense;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class csvHelper {
    private static File file;
    private static PrintWriter writer;
    private static List<String[]> LineData = new ArrayList<>();
    public static File getCSVFile(List<Expense> approvedList) {
        file = new File("data.csv");
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LineData.clear();
        LineData.add(new String[]{"Id","ExpenseName","ExpenseCost","Date","ExpenseType","ExpenseStatus","User"});
        try{
            writer = new PrintWriter(file);
            for (Expense expense: approvedList) {
            LineData.add(new String[]{
                    String.valueOf(expense.getId()),
                    expense.getExpenseName(),
                    String.valueOf(expense.getExpenseCost()),
                    expense.getDate(),
                    expense.getExpenseType(),
                    expense.getExpenseStatus(),
                    expense.getUser()});
            }
            for (String[] line: LineData) {
                for(String entry:line){
                    entry = handleSpecChar(entry);
                }
                writer.println(Arrays.asList(line).stream()
                        .collect(Collectors.joining(",")));
                writer.flush();
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(file.getAbsoluteFile());
        return file;
    }
    public static String handleSpecChar(String data){
        return data.replaceAll("\\R", " ")
                .replaceAll(",", "|")
                .replaceAll("\"", "\"\"");
    }
}
