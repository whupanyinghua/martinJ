package com.pyh.structure;

/**
 * 类FunnyWorldReverse的实现描述：单词翻转的问题
 * 例如给定输入： "dog   loves  pig"
 * 翻转后的输出： "pig  loves   dog"
 *
 * @author panyinghua 2021-7-23 18:06
 */
public class FunnyWorldReverse {

    public static void main(String[] args) {
        char[] txt = "dog   loves  pig".toCharArray();
        System.out.println(txt);
        reverse(txt);
        System.out.println(txt);
    }

    public static void reverse(char[] txt) {
        int length = txt.length;
        // 第一次翻转，把整个字符串都翻转
        reverseInternal(txt, 0, length-1);
        // gip sevop god
        // 第二次翻转单个单词，逐步把每一个单词都进行翻转操作即可
        int begin=0;
        int end=0;
        for(int i=0;i<=txt.length;i++) {
            if(i==txt.length || txt[i]==' ') {
                end=i-1;
                if(end>begin) {
                    reverseInternal(txt, begin, end);
                    begin = i + 1;
                } else {
                    begin = i + 1;
                }
            }
        }
    }

    public static void reverseInternal(char[] txt, int begin, int end) {
        // 第一次翻转

        // 第一种方式，for循环
        /*int length = end - begin + 1;
        for (int i = begin; i < begin+length/2; i++) {
            char tmp = txt[i];
            txt[i] = txt[end - i + begin];
            txt[end - i + begin] = tmp;
        }*/

        // 第二种方式，更简单直观，while循环双指针的方式
        int low = begin;
        int high = end;
        while(low<high) {
            char tmp = txt[low];
            txt[low] = txt[high];
            txt[high] = tmp;
            low++;
            high--;
        }
    }
}
