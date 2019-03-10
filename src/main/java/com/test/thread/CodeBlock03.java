package com.test.thread;

class Code{
    public Code(){
        System.out.println("Code的构造方法1111");
    }
    {
        System.out.println("Code的构造块2222");
    }
    static {
        System.out.println("Code的静态代码块3333");
    }
}
public class CodeBlock03{   //CodeBlock03.class--->static block---
    {
        System.out.println("CodeBlock03的构造块444");
    }
    static {
		System.out.println("CodeBlock03的静态代码块555");
	}
    public CodeBlock03(){
        System.out.println("CodeBlock03的构造方法666");
    }
    public static void main(String[] args){
        System.out.println("======我是美丽分割线=========CodeBlock03的main方法777");
        new Code();
        new CodeBlock03();
    }
}
/**
 * CodeBlock03的静态代码块555  ----  先加载这个类的静态代码块
 ======我是美丽分割线=========CodeBlock03的main方法777 ---打印main方法
 Code的静态代码块3333 ---- 调用父类的静态代码块
 Code的构造块2222   ----  加载父类的构造块 （动态代码块）
 Code的构造方法1111 ----   父类构造方法
 CodeBlock03的构造块444 ----子类父类的构造块 （动态代码块）
 CodeBlock03的构造方法666----   子类构造方法
 */