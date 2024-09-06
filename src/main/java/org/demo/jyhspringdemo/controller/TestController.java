package org.demo.jyhspringdemo.controller;

import org.apache.commons.lang3.RandomUtils;
import org.demo.jyhspringdemo.bean.Student;
import org.demo.jyhspringdemo.bean.Teacher;
import org.demo.jyhspringdemo.interfaces.IStudent;
import org.demo.jyhspringdemo.interfaces.ITeacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author goufj
 * @date 2024-08-24
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Resource
    private IStudent student;

    @Resource
    private ITeacher teacher;

    /**
     * 默认事务传播属性测试
     */
    @GetMapping("/test1")
    public void test1() {
        m1();
    }

    /**
     * requried_new事务传播属性测试
     */
    @GetMapping("/test2")
    public void test2() {
        m1_1();

    }



    public void m1() {
        Student bean = new Student();

        bean.setId(RandomUtils.nextInt());
        bean.setName("测试name"+bean.getId());

        student.save(bean);
        m2();
    }

    public void m1_1(){

        Student bean = new Student();

        bean.setId((RandomUtils.nextInt()));
        bean.setName("测试name"+bean.getId());

        student.saveNew(bean);

        m2_2();

    }

    public void m2() {

        Teacher teacher1 = new Teacher();
        teacher1.setId(UUID.randomUUID().toString());
        teacher1.setName("粗茶淡饭的");
        boolean teach = teacher.teach(teacher1);

        throw new RuntimeException();
    }

    public void m2_2(){

        Teacher teacher1 = new Teacher();
        teacher1.setId(UUID.randomUUID().toString());
        teacher1.setName("粗茶淡饭的");
        boolean teach = teacher.teachNew(teacher1);
    }
}
