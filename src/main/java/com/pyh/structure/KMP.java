package com.pyh.structure;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * 类KMP的实现描述：KMP算法
 *
 * @author panyinghua 2020-4-29 14:29
 */
public class KMP {

    public static void main(String[] args) {
        String destStr = "ababac";
        char[] dest = destStr.toCharArray();
        System.out.println("dest   is " + String.valueOf(dest));
        System.out.println("next[] is " + Arrays.toString(getNext(dest)));
        System.out.println("newN[] is " + Arrays.toString(getNewNext(dest)));
        String sourceStr = "sss"+destStr+"bbb";
        System.out.println("source is " + sourceStr);
        System.out.println("matchp is " + match(sourceStr, destStr));
        System.out.println("new matchp is " + match(sourceStr, destStr, getNewNext(dest)));
    }

    public static int match(String source, String pattern) {
        char[] sources = source.toCharArray();
        char[] dests = pattern.toCharArray();
        int[] next = getNext(dests);
        int matchPos = -1;
        int i=0,j=0;
        while(i<sources.length && j<dests.length) {
            if(j==-1 || sources[i]==dests[j]) {
                i++;j++;
            } else {
                j=next[j];
            }
        }
        if(j==dests.length) {
            // 代表匹配到
            matchPos = i-dests.length;
        }

        return matchPos;
    }

    public static int match(String source, String pattern, int[] next) {
        char[] sources = source.toCharArray();
        char[] dests = pattern.toCharArray();
        int matchPos = -1;
        int i=0,j=0;
        while(i<sources.length && j<dests.length) {
            if(j==-1 || sources[i]==dests[j]) {
                i++;j++;
            } else {
                j=next[j];
            }
        }
        if(j==dests.length) {
            // 代表匹配到
            matchPos = i-dests.length;
        }

        return matchPos;
    }


    public static int[] getNext(char[] dest) {
        if(null == dest || dest.length==0) {
            return null;
        }

        int length = dest.length;
        int[] next = new int[length];
        int k=-1,j=0;
        next[0]=-1;
        while(j<length-1) {
            if(k==-1 || dest[k]==dest[j]) {
                k++;
                j++;
                /*if(j<length) {// while循环可以使用length-1，匹配的判断分支里边已经对j进行了自增
                    next[j] = k;
                }*/
                next[j] = k;
            } else {
                k=next[k];
            }
        }
        return next;
    }

    /**
     * 改进后的next求值方式
     * @param dest
     * @return
     */
    public static int[] getNewNext(char[] dest) {
        if(null == dest || dest.length==0) {
            return null;
        }

        int length = dest.length;
        int[] next = new int[length];
        int k=-1,j=0;
        next[0]=-1;
        while(j<length-1) {// while循环可以使用length-1，匹配的判断分支里边已经对j进行了自增
            if(k==-1 || dest[k]==dest[j]) {
                k++;
                j++;
                if(dest[k]==dest[j]){
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k=next[k];
            }
        }
        return next;
    }
}
