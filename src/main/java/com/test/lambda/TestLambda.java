package com.test.lambda;


@FunctionalInterface
interface Foo{
     int add(int a,int b);

    default int div(int x,int y)
    {
        return x/y;
    }

    public static int sub(int x,int y)
    {
        return x - y;
    }
}

/**
 *
 * @Description: Lambda Express-----> 函数式编程
 * @author zzyy
 * @date 2018年5月25日
 * Lambda Express
 * 1	前提，接口里面有且仅有一个方法声明
 * 2	拷贝中括号----写死右箭头----落地大括号
 * 3	@FunctionalInterface标注
 * 4	default 	方法实现
 * 5	静态方法实现
 *
 */



public class TestLambda {

    public static void main(String[] args) {
        Foo foo = null;
        foo = (int a,int b) -> {
            return a+b;
        };
        System.out.println(foo.add(1,5));

    }



}
