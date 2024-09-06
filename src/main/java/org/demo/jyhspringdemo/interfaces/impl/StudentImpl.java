package org.demo.jyhspringdemo.interfaces.impl;

import org.demo.jyhspringdemo.bean.Student;
import org.demo.jyhspringdemo.interfaces.IStudent;
import org.demo.jyhspringdemo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

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

}
