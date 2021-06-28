package com.pyh.test.structure;

import com.pyh.structure.sort.Sorts;
import com.pyh.test.base.TestBase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * 类SortTest的实现描述：@TODO
 *
 * @author panyinghua 2021-6-28 18:22
 */
public class SortTest extends TestBase {

    @Test
    public void testQuickSort() {
        Random random = new Random();
        System.out.println("测试Sorts.quickSortM方法开始");
        for(int i=0;i<100;i++) {
            int[] a = random.ints(30, 100,1000).toArray();
            int[] a1 = new int[a.length];
            System.arraycopy(a, 0,a1,0,a.length);
            Sorts.quickSortM(a);
            Arrays.sort(a1);

            Assert.assertArrayEquals("Sorts.quickSortM 排序工作不正常",a,a1);

        }
        System.out.println("测试Sorts.quickSortM方法结束");
    }
}
