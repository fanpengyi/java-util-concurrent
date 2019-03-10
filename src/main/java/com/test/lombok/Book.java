package com.test.lombok;


import lombok.*;
import lombok.experimental.Accessors;


/**
 * @Data   ：注解在类上；提供类所有属性的 getting 和 setting 方法，此外还提供了equals、canEqual、hashCode、toString 方法
 *         @Setter：注解在属性上；为属性提供 setting 方法
 *         @Getter：注解在属性上；为属性提供 getting 方法
 *         @Log4j ：注解在类上；为类提供一个 属性名为log 的 log4j 日志对象
 *         @NoArgsConstructor：注解在类上；为类提供一个无参的构造方法
 *         @AllArgsConstructor：注解在类上；为类提供一个全参的构造方法
 *
 *
 * 在File-Setting-Plugins-Browse Repostitories中搜索Lombok Plugin插件安装
 *
 * 安装完成先别急着重启，继续设置，在File-Setting-Build, Execution, Deployment-Compiler-Annotation Processors中点击Enable annotation processors
 *
 * 确定后重启idea
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
// 允许链式调用
@Accessors(chain=true)
public class Book {
    private int id;

    private String name;

    private String author;


    public static void main(String[] args) {
        Book book = new Book().setId(11).setName("2323").setAuthor("666");
        System.out.println(book);
    }


}
