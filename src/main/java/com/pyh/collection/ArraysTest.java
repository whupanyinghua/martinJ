package com.pyh.collection;

import java.util.Arrays;
import java.util.List;

/**
 * 类ArraysTest的实现描述：@TODO
 *
 * @author panyinghua 2020-5-19 11:02
 */
public class ArraysTest {
    public static void main(String[] args) {
        int[] aaa = {1,2,3,4};
        List<?> aaaList = Arrays.asList(aaa);
        System.out.println(aaaList);// 输出的数组里边的元素是int[]类型，而不是整型，打印的信息是 [[I@cc34f4d]

        Integer[] bbb = {1,2,3,4};
        List<?> bbbList = Arrays.asList(bbb);
        System.out.println(bbbList);// 输出的数组里边的元素是Integer类型，打印的信息是 [1, 2, 3, 4]
    }
}
