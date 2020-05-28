package com.pyh.excel.poi;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

/**
 * 类PoiReader的实现描述：POI读取excel文件Demo
 *
 * @author panyinghua 2020-3-24 16:13
 */
public class PoiReader {

    public static void main(String[] args) throws IOException {
        File xlsFile = new File("C:\\Users\\panyinghua\\Desktop\\msxjzx-sanxia-20200326.xlsx");
        Workbook workbook = WorkbookFactory.create(xlsFile);
        int sheetCnt = workbook.getNumberOfSheets();
        Sheet sheet = workbook.getSheetAt(0);
        Row headRow = sheet.getRow(0);
        int maxRows = sheet.getLastRowNum();
        System.out.println("读取配置excel，当前sheet"+sheet.getSheetName()+",一共有"+maxRows+"行");
        JSONArray threePeriodPremiumRates = new JSONArray();
        JSONArray sixPeriodPremiumRates = new JSONArray();
        JSONArray ninePeriodPremiumRates = new JSONArray();
        JSONArray twelvePeriodPremiumRates = new JSONArray();
        for(int i=1;i<=maxRows;i++) {
            Row row = sheet.getRow(i);
            if(null == row) {
                System.out.println("current row is null.");
                continue;
            }
            int colLength = row.getPhysicalNumberOfCells();
            for(int j=0;j<colLength;j++) {
                DecimalFormat df = new DecimalFormat("#.####");
                System.out.print(df.format(row.getCell(j).getNumericCellValue()) + " ");
            }
            System.out.print(" \n");

            JSONObject threePeriodJsonObject = new JSONObject();
            DecimalFormat df = new DecimalFormat("0.000");
            threePeriodJsonObject.put("loanRate", new DecimalFormat("0.000").format(row.getCell(0).getNumericCellValue()));
            df.applyPattern("0.00");
            threePeriodJsonObject.put("premiumRateTypeTxt", df.format(row.getCell(1).getNumericCellValue()));
            df.applyPattern("0.00000000");
            threePeriodJsonObject.put("premiumRate", df.format(row.getCell(2).getNumericCellValue()));
            if(StringUtils.equals("0.36", threePeriodJsonObject.getString("premiumRateTypeTxt"))) {
                threePeriodJsonObject.put("premiumRateType", "A");
            } else if(StringUtils.equals("0.35", threePeriodJsonObject.getString("premiumRateTypeTxt"))) {
                threePeriodJsonObject.put("premiumRateType", "B");
            } else if(StringUtils.equals("0.32", threePeriodJsonObject.getString("premiumRateTypeTxt"))) {
                threePeriodJsonObject.put("premiumRateType", "C");
            } else if(StringUtils.equals("0.24", threePeriodJsonObject.getString("premiumRateTypeTxt"))) {
                threePeriodJsonObject.put("premiumRateType", "D");
            } else if(StringUtils.equals("0.23", threePeriodJsonObject.getString("premiumRateTypeTxt"))) {
                threePeriodJsonObject.put("premiumRateType", "E");
            }
            threePeriodPremiumRates.add(threePeriodJsonObject);

            JSONObject sixPeriodJsonObject = new JSONObject();
            sixPeriodJsonObject.put("loanRate", threePeriodJsonObject.get("loanRate"));
            sixPeriodJsonObject.put("premiumRateTypeTxt", threePeriodJsonObject.get("premiumRateTypeTxt"));
            sixPeriodJsonObject.put("premiumRate", df.format(row.getCell(3).getNumericCellValue()));
            sixPeriodJsonObject.put("premiumRateType", threePeriodJsonObject.get("premiumRateType"));
            sixPeriodPremiumRates.add(sixPeriodJsonObject);

            JSONObject ninePeriodJsonObject = new JSONObject();
            ninePeriodJsonObject.put("loanRate", threePeriodJsonObject.get("loanRate"));
            ninePeriodJsonObject.put("premiumRateTypeTxt", threePeriodJsonObject.get("premiumRateTypeTxt"));
            ninePeriodJsonObject.put("premiumRate", df.format(row.getCell(4).getNumericCellValue()));
            ninePeriodJsonObject.put("premiumRateType", threePeriodJsonObject.get("premiumRateType"));
            ninePeriodPremiumRates.add(ninePeriodJsonObject);

            JSONObject twelvePeriodJsonObject = new JSONObject();
            twelvePeriodJsonObject.put("loanRate", threePeriodJsonObject.get("loanRate"));
            twelvePeriodJsonObject.put("premiumRateTypeTxt", threePeriodJsonObject.get("premiumRateTypeTxt"));
            twelvePeriodJsonObject.put("premiumRate", df.format(row.getCell(5).getNumericCellValue()));
            twelvePeriodJsonObject.put("premiumRateType", threePeriodJsonObject.get("premiumRateType"));
            twelvePeriodPremiumRates.add(twelvePeriodJsonObject);

        }

        JSONObject premiumRateConfigs = new JSONObject();
        premiumRateConfigs.put("3", threePeriodPremiumRates);
        premiumRateConfigs.put("6", sixPeriodPremiumRates);
        premiumRateConfigs.put("9", ninePeriodPremiumRates);
        premiumRateConfigs.put("12", twelvePeriodPremiumRates);

        System.out.println(premiumRateConfigs.toJSONString());
    }


}
