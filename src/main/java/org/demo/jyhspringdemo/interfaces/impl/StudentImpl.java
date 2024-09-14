package org.demo.jyhspringdemo.interfaces.impl;

import org.apache.commons.lang3.RandomUtils;
import org.demo.jyhspringdemo.bean.Student;
import org.demo.jyhspringdemo.bean.Teacher;
import org.demo.jyhspringdemo.interfaces.IStudent;
import org.demo.jyhspringdemo.interfaces.ITeacher;
import org.demo.jyhspringdemo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.UUID;

/**
 *
 *
 * @author goufj
 * @date 2024-08-24
 */
@Service
@Transactional
public class StudentImpl implements IStudent {

    @Autowired
    private StudentMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void save(Student student) {

        mapper.insert(student);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRES_NEW)
    public void saveNew(Student student) {
        TransactionStatus txStatus = TransactionAspectSupport.currentTransactionStatus();

        System.out.println("student save: "+txStatus);
        mapper.insert(student);
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.NEVER)
    public void saveNever(Student student) {
        TransactionStatus txStatus = TransactionAspectSupport.currentTransactionStatus();

        System.out.println("student saveNever: "+txStatus);
        mapper.insert(student);
    }

    @Override
    public void delete(String id) {
        mapper.deleteById(id);
    }

    @Override
    public Student getStudent(String id) {
        return mapper.selectById(id);
    }

    @Override
    public List<Student> getStudents() {
        return mapper.findAll();
    }

    @Override
    public void update(Student student) {
        mapper.updateById(student);
    }

    @Override
    @Cacheable(cacheNames = "say", key = "'p_'+ #name")
    public String sayHello(String name) {
        return "hello+" + name + "-->" + UUID.randomUUID().toString();
    }


    @Override
    public void m3_3(){

//        TransactionStatus txStatus = TransactionAspectSupport.currentTransactionStatus();
//
//        System.out.println("student m3_3(): "+txStatus);
        //student
        Student bean = new Student();

        bean.setId((RandomUtils.nextInt()));
        bean.setName("测试name"+bean.getId());

        this.saveNever(bean);

        //teacher
        m3_4();

    }

    @Autowired
    private ITeacher teacher;

    public void m3_4(){

//        TransactionStatus txStatus = TransactionAspectSupport.currentTransactionStatus();
//
//        System.out.println("student m3_4(): "+txStatus);

        Teacher teacher1 = new Teacher();
        teacher1.setId(UUID.randomUUID().toString());
        teacher1.setName("粗茶淡饭的");
        teacher.teachNever(teacher1);
    }

}
