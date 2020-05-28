package com.pyh.jvm;

/**
 * 类ClassInitOrder的实现描述：类初始化的顺序研究
 *
 * @author panyinghua 2020-5-12 16:52
 */
public class ClassInitOrder {

    public static void main(String[] args) {
        System.out.println("i'm main class.");
        Parent parent = new Child();
    }

    public static class Parent {
        public Parent() {
            System.out.println("i'm Parent.");
        }

        static {
            System.out.println("i'm Parent static code");
        }

        {
            System.out.println("i'm Parent code");
        }
    }

    public static class Child extends Parent {
        public Child() {
            System.out.println("i'm child.");
        }

        static {
            System.out.println("i'm child static code");
        }

        {
            System.out.println("i'm child code");
        }

    }
}
