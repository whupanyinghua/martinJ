package com.pyh.jvm;

/**
 * 类HelloB的实现描述：@TODO
 *
 * @author panyinghua 2020-5-12 16:59
 */
public class HelloB extends HelloA {
    public HelloB() {
        System.out.println("HelloB");
    }

    {
        System.out.println("i'm B class");
    }

    static {
        System.out.println("static B");
    }

    public static void main(String[] args) {
        System.out.println("hello world!");
        HelloA a = new HelloB();
    }
}

class HelloA {
    public HelloA() {
        System.out.println("HelloA");
    }

    {
        System.out.println("i'm A class");
    }

    static {
        System.out.println("static A");
    }
}
