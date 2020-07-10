package com.pyh.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 类GsonString2DateTest的实现描述：@TODO
 *
 * @author panyinghua 2019-12-16 17:07
 */
public class GsonString2DateTest {

    //private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private static Gson gson = new GsonBuilder().create();

    public static void main(String[] args) {
        // 1.设置了setDateFormat，则解析日期字符串的时候，使用的是 DefaultDateTypeAdapter
        // 2.没有设置setDateFormat，则解析日期字符串使用的是DateTypeAdapter
        // 3.两个adapter里边都有三个format，按照顺序 localFormat enUsFormat iso8601Format 去解析，只要有一个满足即可
        // 4.localFormat可以解析"\"2019-12-11 00:00:00\""格式的日期
        Gson gsonTest = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Date date = gsonTest.fromJson("\"2019-12-11 00:00:00\"", java.util.Date.class);
        System.out.println(date); // Wed Dec 11 00:00:00 CST 2019
        BigDecimal.valueOf(20);
        //
        Gson gsonTest2 = new GsonBuilder().create();
        Date date2 = gsonTest2.fromJson("2019-12-11 00:00:00", java.util.Date.class);
        System.out.println(date2); // Wed Dec 11 00:00:00 CST 2019
    }

}
