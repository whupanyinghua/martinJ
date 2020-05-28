package com.pyh.Java8;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.function.Function;

/**
 * 类FunctionTest的实现描述：@TODO
 *
 * @author panyinghua 2020-4-20 14:48
 */
public class FunctionTest {
    public static void main(String[] args) {
        FunctionClass functionClass = new FunctionClass();
        Function<Void, String> function = (Void) -> "hello,function";
        //Function<?, String> functionq = functionClass::getF1;

        Map<String, String> testMap = Maps.newHashMap();
        testMap.forEach((k, v) -> {
            try {
                System.out.println("test");
            } catch(Exception ex) {
                //throw new Exception("test");
            }
        });
    }

    public static class FunctionClass {
        private String f1;

        public String getF1() {
            return f1;
        }
    }
}
