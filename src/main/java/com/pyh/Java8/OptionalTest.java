package com.pyh.Java8;

import java.util.Optional;

/**
 * 类OptionalTest的实现描述：@TODO
 *
 * @author panyinghua 2020-4-20 10:09
 */
public class OptionalTest {
    public static void main(String[] args) {
        String str = "pyh test";
        Optional<String> optionalS = Optional.ofNullable(str);
        optionalS.ifPresent(System.out::println);
    }
}
