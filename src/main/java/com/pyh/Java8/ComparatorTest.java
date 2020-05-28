package com.pyh.Java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 类ComparatorTest的实现描述：@TODO
 *
 * @author panyinghua 2020-4-20 14:36
 */
public class ComparatorTest {
        private int a;
        private int b;

        public ComparatorTest(int a, int b) {
            this.a = a;
            this.b = b;
        }

        public int getA() {
            return a;
        }

        public ComparatorTest setA(int a) {
            this.a = a;
            return this;
        }

        public int getB() {
            return b;
        }

        public ComparatorTest setB(int b) {
            this.b = b;
            return this;
        }

        @Override
        public String toString() {
            return "\n" + a + " : " + b;
        }

        public static void main(String[] args) {
            List<ComparatorTest> list = new ArrayList() {{
                add(new ComparatorTest(1, 1));
                add(new ComparatorTest(1, 2));
                add(new ComparatorTest(2, 3));
                add(null);
                add(new ComparatorTest(2, 1));
                add(new ComparatorTest(3, 4));
                add(new ComparatorTest(3, 1));
            }};

            // 按b属性倒序，再按a属性倒序排列，null放最前面
            // 相当于SQL: sort by b desc, a desc
            list.sort(Comparator.nullsFirst(Comparator
                    .comparing(ComparatorTest::getB)
                    .reversed()
                    .thenComparing(Comparator.comparing(ComparatorTest::getA).reversed())));
            System.out.println(list);

            list.sort(Comparator.comparing(ComparatorTest::getB));
            System.out.println(list);
        }
}
