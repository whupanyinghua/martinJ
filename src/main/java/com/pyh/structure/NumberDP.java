package com.pyh.structure;

/**
 * 类NumberDP的实现描述：在[0~max）中间有多少个数字1
 *
 * @author panyinghua 2020-4-17 17:08
 */
public class NumberDP {

    public static void main(String[] args) {
        // 在[0~max）中间有多少个数字1
        int count = 0;
        char c_target = '1';
        int max = 10000;
        for(int i=0;i<max;i++) {
            String ss = Integer.toString(i);
            for (char c: ss.toCharArray()) {
                if(c==c_target) {
                    count++;
                }
            }
        }
        System.out.println("[0 ~ " + max + ") contain '" + c_target + "' " + count + " times.");
    }


}
