package com.pyh.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

/**
 * 类GsonString2DateTest的实现描述：@TODO
 *
 * @author panyinghua 2019-12-16 17:07
 */
public class GsonString2DateTest {

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static void main(String[] args) {
        Date date = gson.fromJson("\"2019-12-11 00:00:00\"", java.util.Date.class);
        System.out.println(date);
    }

}
