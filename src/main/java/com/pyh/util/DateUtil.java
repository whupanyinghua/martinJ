package com.pyh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类DateUtil的实现描述：@TODO
 *
 * @author panyinghua 2020-5-20 9:52
 */
public class DateUtil {


    public static void main(String[] args) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        System.out.println(String.format("now date is %s,format str is %s", now.toString(), simpleDateFormat.format(now)));

        String dateStr = "2020-05-20";
        try {
            // 输出的是now dateStr is 2020-05-20,format Date is Thu Dec 05 00:00:00 CST 2019，与预期不一致
            // parse的时候，精度的问题？
            System.out.println(String.format("now dateStr is %s,format Date is %s", dateStr, simpleDateFormat.parse(dateStr)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            // 输出的是
            // parse的时候，精度的问题？
            simpleDateFormat.setLenient(false); // 表示严格验证，此处会抛出异常了
            System.out.println(String.format("now dateStr is %s,format Date is %s", dateStr, simpleDateFormat.parse(dateStr)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date nn = simpleDateFormat.parse("2021-07-25 34:12:25");
            System.out.println(nn);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
