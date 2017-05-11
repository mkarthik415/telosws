package telosws.util;

/**
 * Created by karthikmarupeddi on 5/30/15.
 */


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;


@Component
public class ExcelHelper implements ExcelHelperInterface {

    final org.slf4j.Logger logger = LoggerFactory.getLogger(ExcelHelper.class);


    private List<Class<?>> tableColumnTypes = null;
    private String tableName;
    DecimalFormat df = new DecimalFormat("#####.#######");


    public String ExcelCountroller() {

        ClassLoader classLoader = getClass().getClassLoader();

        try {

            //create new file for update statements
            PrintWriter writer = new PrintWriter("update.txt", "UTF-8");


            //reading files from folder
            File folder = new File("/Users/karthikmarupeddi/Downloads/testing");
            File[] listOfFiles = folder.listFiles();

            for (int f = 0; f < listOfFiles.length; f++) {
                File sourceFile = listOfFiles[f];
                if (sourceFile.isFile() && sourceFile.getName().endsWith(".xlsx")) {

                    //Access each file

                    //File sourceFile = new File(classLoader.getResource("sample.xlsx").getFile());
                    FileInputStream file = new FileInputStream(sourceFile);
                    //Get the workbook instance for XLS file
                    XSSFWorkbook workbook = new XSSFWorkbook(file);
                    writer.println("--Source file is : " + sourceFile.getName());

                    System.out.println("Total number of sheets are " + workbook.getNumberOfSheets());


                    //Read all the sheets
                    for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                        //getting the sheet


                        XSSFSheet sheet = workbook.getSheetAt(sheetNum);

                        if (!sheet.getSheetName().equalsIgnoreCase("Mpoints")) {
                            //looping through the sheet
                            Iterator ite = sheet.rowIterator();
                            List<String> tableColumnNames = new ArrayList();
                            int i = 0;
                            while (ite.hasNext()) {
                                i++;
                                Row row = (Row) ite.next();
                                if (i == 1) {

                                    tableName = row.getCell(0).toString();
                                } else if (i == 2) {

                                    Iterator<Cell> cite = row.cellIterator();
                                    while (cite.hasNext()) {
                                        Cell c = cite.next();
                                        tableColumnNames.add(c.toString());
                                    }
                                } else {

                                    LinkedHashMap<String, Cell> columnValuePair = new LinkedHashMap();
                                    if (row.getCell(1) != null) {
                                        tableColumnTypes = new ArrayList();

                                        Iterator<Cell> cite = row.cellIterator();
                                        int j = 0;
                                        while (cite.hasNext()) {
                                            Cell c = cite.next();

                                            if ((c.getCellType() == Cell.CELL_TYPE_NUMERIC || c.getCellType() == Cell.CELL_TYPE_STRING) && (((Short) c.getCellStyle().getFontIndex()).toString().equals("11") ||

                                                    ((Short) c.getCellStyle().getFontIndex()).toString().equals("4")) && row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC) {


                                                System.out.println("++++++++++" + c.getCellStyle().getFontIndex() + "+++++++++++++");
                                                // System.out.println("++++++++++" + c.getCellStyle().getFillForegroundColorColor().toString() + "+++++++++++++");
                                                columnValuePair.put(tableColumnNames.get(j), c);


                                            }

                                            j++;
                                        }

                                        String finalColumns = "";
                                        String columns = null;
                                        String update = null;
                                        int numberOfColumns = 0;
                                        update = "UPDATE " + tableName + " SET ";
                                        for (String columnName : columnValuePair.keySet()) {

                                            System.out.println(columnName);


                                            if (columnValuePair.size() - 1 != numberOfColumns) {

                                                if (columnValuePair.get(columnName).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                                    // columns = columnName + " =  " + df.format(columnValuePair.get(columnName).getNumericCellValue()) + ",";

                                                    columns = columnName + " =  " + Util.formateData(columnValuePair.get(columnName).getNumericCellValue()) + ",";


                                                } else {
                                                    columns = columnName + " =  " +"'"+columnValuePair.get(columnName).getStringCellValue()+"'" + ",";
                                                }

                                            } else {
                                                if (columnValuePair.get(columnName).getCellType() == Cell.CELL_TYPE_NUMERIC) {
                                                    columns = columnName + " =  " + df.format(columnValuePair.get(columnName).getNumericCellValue());
                                                } else {
                                                    columns = columnName + " =  " +"'"+columnValuePair.get(columnName).getStringCellValue()+"'";
                                                }


                                                try {

                                                    columns = columns + " WHERE " + tableColumnNames.get(0) + " = " + df.format(row.getCell(0).getNumericCellValue()) + ";";
                                                } catch (IllegalStateException e) {
                                                    e.printStackTrace();
                                                }


                                            }

                                            numberOfColumns++;
                                            finalColumns = finalColumns + columns;


                                        }

                                        if (finalColumns != null && !finalColumns.isEmpty()) {

                                            System.out.println(update + finalColumns);
                                            writer.println(update + finalColumns);
                                        }

                                        //write update statements to the file
                                    } else
                                        continue;


                                }


                            }
                        }


                    }

                }
            }

            //close file here after all the updates.
            writer.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "passed";

    }
}
