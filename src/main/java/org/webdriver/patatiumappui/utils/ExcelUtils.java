package org.webdriver.patatiumappui.utils;


import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ExcelUtils {
    public int numOfRows;
    private String filePath;
    private String sheetName;
    private Workbook workBook;
    private Sheet sheet;
    public List<String> columnHeaderList;
    private List<List<String>> listData;
    private List<Map<String, String>> mapData;
    private boolean flag;

    public ExcelUtils(String filePath, String sheetName) {
        this.filePath = filePath;
        this.sheetName = sheetName;
        this.flag = false;
        this.load();
    }

    public ExcelUtils(String filePath) {
        this.filePath = filePath;
        this.flag = false;
        this.load();
    }


    public void load() {
        FileInputStream inStream = null;
        try {
            inStream = new FileInputStream(new File(filePath));
            workBook = WorkbookFactory.create(inStream);
            if (sheetName == null) {
                sheet = workBook.getSheetAt(0);
            } else {
                sheet = workBook.getSheet(sheetName);
            }


            numOfRows = sheet.getLastRowNum() + 1;
            this.getColumnHeaderList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getColumnHeaderList() {
        columnHeaderList = new ArrayList<String>();
        for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
            Cell cell = sheet.getRow(0).getCell(i);
            columnHeaderList.add(this.getCellValue(cell));
        }
    }


    public String getCellValue(Cell cell) {
        String cellValue = "";
        DataFormatter formatter = new DataFormatter();
        if (cell != null) {
            switch (cell.getCellTypeEnum()) {
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = formatter.formatCellValue(cell);
                    } else {
                        double value = cell.getNumericCellValue();
                        int intValue = (int) value;
                        cellValue = value - intValue == 0 ?
                                String.valueOf(intValue) : String.valueOf(value);
                    }
                    break;
                case STRING:
                    cellValue = cell.getStringCellValue().replaceAll("\n", "");
                    break;
                case BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case FORMULA:
                    cellValue = String.valueOf(cell.getCellFormula());
                    break;
                case _NONE:
                    cellValue = "0";
                    break;
                case BLANK:
                    cellValue = "0";
                    break;
                case ERROR:
                    cellValue = "0";
                    break;
                default:
                    cellValue = cell.toString().trim();
                    break;
            }
        }
        return cellValue.trim();

    }


    /**
     * 获取列表所有数据
     */
    public void getSheetData() {
        listData = new ArrayList<List<String>>();
        columnHeaderList = new ArrayList<String>();
        mapData = new ArrayList<Map<String, String>>();
        //numOfRows = sheet.getLastRowNum() + 1;
        for (int i = 0; i < numOfRows; i++) {
            Row row = sheet.getRow(i);
            Map<String, String> map = new HashMap<String, String>();
            List<String> list = new ArrayList<String>();
            if (row != null) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (i == 0) {
                        columnHeaderList.add(getCellValue(cell));
                    } else {
                        map.put(columnHeaderList.get(j),
                                this.getCellValue(cell));
                    }
                    list.add(this.getCellValue(cell));
                }
            }
            if (i > 0) {
                mapData.add(map);
            }
            listData.add(list);
        }
        flag = true;

    }

    public List<Map<String, String>> getMapData(int begin, int end) {
        columnHeaderList = new ArrayList<String>();
        mapData = new ArrayList<Map<String, String>>();
        for (int i = begin; i < end; i++) {
            Row row = sheet.getRow(i);
            Map<String, String> map = new HashMap<String, String>();
            if (row != null) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    map.put(columnHeaderList.get(j),
                            this.getCellValue(cell));

                }
            }
            if (i > 0) {
                mapData.add(map);
            }
        }
        return mapData;
    }

    public List<Map<String, String>> getMapData(int begin) {
        int LastRow = sheet.getLastRowNum();
        return this.getMapData(begin, LastRow);
    }


    public List<List<String>> getListData(int begin, int end) {
        List<List<String>> listData = new ArrayList<List<String>>();
        for (int i = begin; i < end; i++) {
            Row row = sheet.getRow(i);
            List<String> list = new ArrayList<String>();
            if (row != null) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    String cell = row.getCell(j).toString();
                    list.add(cell);
                }
            }
            listData.add(list);
        }
        return listData;
    }

    public List<List<String>> getListData(int begin) {
        int LastRow = sheet.getLastRowNum() + 1;
        return this.getListData(begin, LastRow);
    }


    public Map<String, String> getRowData(int index) {
        Row row = sheet.getRow(index);
        Map<String, String> map = new HashMap<String, String>();
        if (row != null) {
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                map.put(columnHeaderList.get(j),
                        this.getCellValue(cell));

            }
        }
        return map;
    }

    public Map<String, String> getRowData(Row row) {
        Map<String, String> map = new HashMap<String, String>();
        if (row != null) {
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                map.put(columnHeaderList.get(j),
                        this.getCellValue(cell));

            }
        }
        return map;
    }


    public String getCellData(int row, int col) {
        if (row <= 0 || col <= 0) {
            return null;
        }
        if (!flag) {
            this.getSheetData();
        }
        if (listData.size() >= row && listData.get(row - 1).size() >= col) {
            return listData.get(row - 1).get(col - 1);
        } else {
            return null;
        }
    }

    public String getCellData(int row, String headerName) {
        if (row <= 0) {
            return null;
        }
        if (!flag) {
            this.getSheetData();
        }
        if (mapData.size() >= row &&
                mapData.get(row - 1).containsKey(headerName)) {
            return mapData.get(row - 1).get(headerName);
        } else {
            return null;
        }
    }

    public List<List<String>> getListData() {
        return listData;
    }

    public List<Map<String, String>> getMapData() {
        return mapData;
    }

    public Sheet getSheet() {
        return this.sheet;
    }


    /**
     * @param path 对象库文件地址
     * @return 返回locator 哈希表  locatorName:locator
     */
    public static HashMap<String, Locator> getLocatorMap(String path) {
        HashMap<String, Locator> locatorHashMap = new HashMap<>();
        ExcelUtils excelUtils = new ExcelUtils(path);
        excelUtils.getSheetData();
        List<List<String>> sheet = excelUtils.getListData(5);
        for (int i = 1; i <= sheet.size(); i++)//遍历Page节点
        {
            List<String> list = sheet.get(i);
            Locator locator = new Locator();
            locator.setType(getByType(list.get(0)));
            locator.setValue(list.get(2));
            locator.setTimout(Integer.parseInt(list.get(2)));
            locator.setLocatorName(list.get(4));
            locatorHashMap.put(list.get(4), locator);
        }
        return locatorHashMap;
    }

    /**
     * @param type
     */
    public static Locator.ByType getByType(String type) {
        Locator.ByType byType = Locator.ByType.xpath;
        if (type == null || type.equalsIgnoreCase("xpath")) {
            byType = Locator.ByType.xpath;
        } else if (type.equalsIgnoreCase("id")) {
            byType = Locator.ByType.id;
        } else if (type.equalsIgnoreCase("linkText")) {
            byType = Locator.ByType.linkText;
        } else if (type.equalsIgnoreCase("name")) {
            byType = Locator.ByType.name;
        } else if (type.equalsIgnoreCase("className")) {
            byType = Locator.ByType.className;
        } else if (type.equalsIgnoreCase("cssSelector")) {
            byType = Locator.ByType.cssSelector;
        } else if (type.equalsIgnoreCase("partialLinkText")) {
            byType = Locator.ByType.partialLinkText;
        } else if (type.equalsIgnoreCase("tagName")) {
            byType = Locator.ByType.tagName;
        }
        return byType;
    }

    public static void main(String[] args) {
        ExcelUtils excelUtils = new ExcelUtils("F:\\数据\\demo.xlsx", "Sheet1");
        excelUtils.getSheetData();
        System.out.println(excelUtils.getMapData().subList(0, 10));

    }


}
