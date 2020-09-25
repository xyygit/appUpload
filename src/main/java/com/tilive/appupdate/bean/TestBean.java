package com.tilive.appupdate.bean;

import lombok.Data;
//@Data注解可以实现在编译器自动添加set和get函数的效果。该注解是lombok提供
@Data
public class TestBean {
    private Long id;
    private String name;
    private Integer age;
}
