package org.demo.jyhspringdemo.bean;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author goufj
 * @date 2024-09-06
 */
@TableName("teacher")
public class Teacher {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
