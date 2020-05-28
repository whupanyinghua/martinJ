package com.pyh.fastjson;

import com.alibaba.fastjson.JSONObject;

public class JsonObjectTest {

    public static void main(String[] args) {
        String txt = "{\"tt\":\"111\"}";
        Object obj = JSONObject.parseObject(txt, Object.class);
        System.out.println(obj);
    }

}
