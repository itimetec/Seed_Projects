package com.itt.util;

import com.aspose.cells.Workbook;
import com.itt.constants.FileType;
import com.itt.constants.ResourceName;
import com.itt.seleniumHelper.Wait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExcelFileHandler {

    public static XSSFWorkbook workbook = null;
    public static FileInputStream fis = null;
    private static ExcelFileHandler excelFileHandler;

    private static final Logger LOGGER = LogManager.getLogger(ExcelFileHandler.class);

    private static final String TEST_DATA_PATH = "src/test/resources/TestData/";

    private static String getExcelFileExtension() {
        return ".xlsx";
    }

    public static ExcelFileHandler getInstance(String fileName) {
        excelFileHandler = new ExcelFileHandler();
        try {
            fis = new FileInputStream(getResourcePath(fileName, FileType.XLSX));
            workbook = new XSSFWorkbook(fis);
            fis.close();
        } catch (IOException e) {
            LOGGER.info("There is an error to read file - %s. IOException: ", fileName);
        }
        return excelFileHandler;
    }

    public static ExcelFileHandler getInstance(ResourceName xlsFilePath) {
        excelFileHandler = new ExcelFileHandler();
        {
            try {
                fis = new FileInputStream(getResourcePath(xlsFilePath));
                workbook = new XSSFWorkbook(fis);
                fis.close();
            } catch (IOException e) {
                LOGGER.info("There is an error to read file - %s. IOException: ", xlsFilePath);
            }
        }
        return excelFileHandler;
    }

    public static String getResourcePath(ResourceName name) {
        return Paths.get(TEST_DATA_PATH, name.toString()).toAbsolutePath().toString() + getExcelFileExtension();
    }

    public static String getResourcePath(String excelFileName, FileType fileType) {
        return Paths.get(TEST_DATA_PATH, excelFileName.toString()).toAbsolutePath().toString() + fileType;
    }

    public static String getCellData(ResourceName name, String sheetName, String colName, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            int col_Num = -1;
            if (index == -1)
                return "";

            XSSFSheet sheet = workbook.getSheetAt(index);
            XSSFRow row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
                    col_Num = i;
                    break;
                }
            }
            if (col_Num == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            XSSFCell cell = row.getCell(col_Num);

            if (cell == null)
                return "";

            if (cell.getCellType().name().equals("STRING"))
                return cell.getStringCellValue();

            else if ((cell.getCellType().name().equals("NUMERIC")) || (cell.getCellType().name().equals("FORMULA"))) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (DateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(DateUtil.getJavaDate(d));
                    cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
                }

                return cellText;
            } else if (cell.getCellType().BLANK != null)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());

        } catch (Exception e) {
            LOGGER.info("row: {}  or column: {}  does not exist in xls", rowNum, colName);
            return "row " + rowNum + " or column " + colName + " does not exist in xls";
        }
    }

    public static String getCellData(String excelFileName, String sheetName, String colName, int rowNum) {
        try {
            Wait.waitTillFileExists(getResourcePath(excelFileName, FileType.XLSX), 60, TimeUnit.SECONDS);

            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);
            int col_Num = -1;
            if (index == -1)
                return "";

            XSSFSheet sheet = workbook.getSheetAt(index);
            XSSFRow row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
                    col_Num = i;
                    break;
                }
            }
            if (col_Num == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            XSSFCell cell = row.getCell(col_Num);

            if (cell == null)
                return "";

            if (cell.getCellType().name().equals("STRING"))
                return cell.getStringCellValue();

            else if ((cell.getCellType().name().equals("NUMERIC")) || (cell.getCellType().name().equals("FORMULA"))) {

                String cellText = String.valueOf(cell.getNumericCellValue());
                if (DateUtil.isCellDateFormatted(cell)) {
                    // format in form of M/D/YY
                    double d = cell.getNumericCellValue();

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(DateUtil.getJavaDate(d));
                    cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
                }

                return cellText;
            } else if (cell.getCellType().BLANK != null)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());

        } catch (Exception e) {
            LOGGER.info("row: {}  or column: {}  does not exist in xls", rowNum, colName);
            return "row " + rowNum + " or column " + colName + " does not exist in xls";
        }
    }

    public boolean setCellData(ResourceName name, String sheetName, String colName, int rowNum, String data) {
        try {
            String filePath = getResourcePath(name);

            if (rowNum <= 0)
                return false;

            int index = workbook.getSheetIndex(sheetName);
            if (index == -1) {
                return false;
            }

            int colNum = -1;
            XSSFSheet sheet = workbook.getSheetAt(index);
            XSSFRow row = sheet.getRow(0);

            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName))
                    colNum = i;
            }

            if (colNum == -1)
                return false;

            sheet.autoSizeColumn(colNum);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                row = sheet.createRow(rowNum - 1);

            XSSFCell cell = row.getCell(colNum);
            if (cell == null)
                cell = row.createCell(colNum);

            cell.setCellValue(data);

            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            LOGGER.info("row: {}  or column: {}  does not exist in xls to write the data", rowNum, colName);
            return false;
        }
        return true;
    }

    public Boolean isColumnNamePresent(ResourceName name, String sheetName, String colName) {
        boolean columnNameAvailable = false;

        try {

            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return false;

            XSSFSheet sheet = workbook.getSheetAt(index);
            XSSFRow row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
                    columnNameAvailable = true;
                    LOGGER.info(String.format(" Column is present at : %s ", i));
                    break;
                }
            }
            return columnNameAvailable;

        } catch (Exception e) {
            LOGGER.info("There is an error to read file - %s. Exception: ", colName, e);
            return false;
        }
    }

    public boolean isColumnNamePresent(String excelFileName, String sheetName, String colName) {
        boolean columnNameAvailable = false;

        try {
            Wait.waitTillFileExists(getResourcePath(excelFileName, FileType.XLSX), 60, TimeUnit.SECONDS);

            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return false;

            XSSFSheet sheet = workbook.getSheetAt(index);
            XSSFRow row = sheet.getRow(0);
            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
                    columnNameAvailable = true;
                    LOGGER.info(String.format(" Column is present at : %s ", i));
                    break;
                }
            }
            return columnNameAvailable;

        } catch (Exception e) {
            LOGGER.info("There is an error to read file - %s. Exception: ", colName, e);
            return false;
        }
    }

    public static Boolean isSheetPresent(ResourceName name, String sheetName) {
        try {
            int index = workbook.getSheetIndex(sheetName);
            return index != -1;
        } catch (Exception e) {
            LOGGER.info("There is an error to read file - %s. IOException: ", name, e);
            return false;
        }
    }

    public static int getTotalCountOfRow(ResourceName name, String sheetName) throws IOException {
        XSSFSheet sheet = workbook.getSheet(sheetName);
        int rowNum = sheet.getLastRowNum() + 1;
        return rowNum;
    }

    public void deleteSpecifiedRow(ResourceName name, String sheetName, int rowIndex) {
        String filePath = getResourcePath(name);
        try {
            int index = workbook.getSheetIndex(sheetName);
            XSSFSheet sheet = workbook.getSheetAt(index);
            if (sheet.getRow(rowIndex - 1) != null) {
                sheet.removeRow(sheet.getRow(rowIndex - 1));
            }
            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            LOGGER.info("There is an error to read file - %s. IOException: ", name, e);
        }
    }

    public int getColumnIndex(ResourceName name, String sheetName, String colName) {
        int columnPosition;
        try {
            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return -1;

            XSSFSheet sheet = workbook.getSheetAt(index);
            XSSFRow row = sheet.getRow(0);
            for (columnPosition = 0; columnPosition < row.getLastCellNum(); columnPosition++) {
                if (row.getCell(columnPosition).getStringCellValue().trim().equals(colName.trim())) {
                    LOGGER.info(String.format(" Column is present at : %s ", columnPosition));
                    break;
                }
            }
            return columnPosition;

        } catch (Exception e) {
            LOGGER.info("There is an error to read file - %s. Exception: ", colName, e);
            return -1;
        }
    }

    public static Boolean isValuePresent(ResourceName name, String sheetName, String word) {
        Boolean status = false;
        try {
            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return false;

            XSSFSheet sheet = workbook.getSheetAt(index);
            Iterator<Row> itr = sheet.iterator();

            while (itr.hasNext()) {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getStringCellValue().equalsIgnoreCase(word)) {
                        status = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.info("There is an error to find -%s in file. Exception: ", word, e);
            return false;
        }
        return status;
    }

    public static Boolean isValuePresent(String excelFileName, String sheetName, String word) {
        Boolean status = false;
        try {
            Wait.waitTillFileExists(getResourcePath(excelFileName, FileType.XLSX), 60, TimeUnit.SECONDS);
            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return false;

            XSSFSheet sheet = workbook.getSheetAt(index);
            Iterator<Row> itr = sheet.iterator();

            while (itr.hasNext()) {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getStringCellValue().equalsIgnoreCase(word)) {
                        status = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.info("There is an error to find -%s in file. Exception: ", word, e);
            return false;
        }
        return status;
    }

    public int getColumnIndex(String name, String sheetName, String colName) {
        int columnPosition;
        try {
            Wait.waitTillFileExists(getResourcePath(name, FileType.XLSX), 60, TimeUnit.SECONDS);

            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return -1;

            XSSFSheet sheet = workbook.getSheetAt(index);
            XSSFRow row = sheet.getRow(0);
            for (columnPosition = 0; columnPosition < row.getLastCellNum(); columnPosition++) {
                if (row.getCell(columnPosition).getStringCellValue().trim().equals(colName.trim())) {
                    LOGGER.info(String.format(" Column is present at : %s ", columnPosition));
                    break;
                }
            }
            return columnPosition;
        } catch (Exception e) {
            LOGGER.info("There is an error to read file - %s. Exception: ", colName, e);
            return -1;
        }
    }

    public Map<Integer, List<String>> getFormattedCsvFile(String filename) {
        String file = TEST_DATA_PATH + "/" + filename, line, fileData = "";
        Map<Integer, List<String>> rowData = new HashMap<>();
        List<String> dataList = new ArrayList<>();
        List<String> datas = new ArrayList<>();
        boolean flag = true;
        int row = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((line = reader.readLine()) != null) {
                if (flag) {
                    rowData.put(++row, List.of(line.split(",")));
                    flag = false;
                } else {
                    fileData = fileData.concat(line);
                }
            }
            dataList.addAll(Arrays.asList(fileData.split(",")));
            int noOfColumns = rowData.get(1).size();
            for (String data : dataList) {
                noOfColumns--;
                datas.add(data);
                if (noOfColumns == 0) {
                    rowData.put(++row, Arrays.asList(datas.toString()));
                    datas.clear();
                    noOfColumns = rowData.get(1).size();
                }
            }
            return rowData;
        } catch (Exception exception) {
            LOGGER.info("There is an error to read file - %s. Exception: ", file, exception);
            return null;
        }
    }

    public static String convertBase64String(String filePath) {
        String base64ConvertedValue = "";
        File file = new File(filePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a file from file system
            byte fileData[] = new byte[(int) file.length()];
            imageInFile.read(fileData);
            base64ConvertedValue = Base64.getEncoder().encodeToString(fileData);
        } catch (FileNotFoundException exception) {
            LOGGER.info("File not found" + exception.getMessage());
        } catch (IOException exception) {
            LOGGER.info("Exception while reading the file " + exception.getMessage());
        }
        return base64ConvertedValue;
    }

    public void setDuplicateCellData(ResourceName name, String sheetName, String colName, int rowNum, List<String> data) {
        try {
            int colNum = -1, occurrence = 0;
            String filePath = getResourcePath(name);
            int index = workbook.getSheetIndex(sheetName);
            XSSFSheet sheet = workbook.getSheetAt(index);
            XSSFRow row = sheet.getRow(0);

            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().trim().equals(colName)) {
                    colNum = i;

                    sheet.autoSizeColumn(colNum);
                    XSSFRow dataRow = sheet.getRow(rowNum - 1);

                    XSSFCell cell = dataRow.getCell(colNum);
                    if (cell == null)
                        cell = dataRow.createCell(colNum);

                    cell.setCellValue(data.get(occurrence));
                    occurrence++;
                }

            }

            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            fileOut.close();

        } catch (Exception e) {
            LOGGER.info("Row: {}  or Column: {}  does not exist in xls to write the data", rowNum, colName);
            LOGGER.info("Reason : ", e.getMessage());
        }
    }

    public void convertXLSToXLSX(String resourceName) {
        Wait.waitTillFileExists(getResourcePath(resourceName, FileType.XLS), 60, TimeUnit.SECONDS);
        try {
            Workbook workbook = new Workbook(getResourcePath(resourceName, FileType.XLS));
            workbook.save(getResourcePath(resourceName, FileType.XLSX));
        } catch (Exception e) {
            throw new RuntimeException("Path set is incorrect" + e.getMessage());
        }
    }

    public static String[][] getExcelDataIn2DArray(String fileName, String SheetName) {
        XSSFSheet ExcelWSheet;
        XSSFWorkbook ExcelWBook;

        String[][] excelDataArray = null;
        try {

            FileInputStream ExcelFile = new FileInputStream(TEST_DATA_PATH + fileName + getExcelFileExtension());

            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);

            int numOfColumns = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
            int numOfRows = ExcelWSheet.getPhysicalNumberOfRows();

            excelDataArray = new String[numOfRows-1][numOfColumns];

            for (int i= 1 ; i < numOfRows; i++) {
                for (int j=0; j < numOfColumns; j++) {
                    excelDataArray[i-1][j] = ExcelWSheet.getRow(i).getCell(j).getStringCellValue();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelDataArray;
    }
}