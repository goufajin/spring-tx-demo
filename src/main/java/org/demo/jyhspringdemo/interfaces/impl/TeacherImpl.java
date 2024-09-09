package org.demo.jyhspringdemo.interfaces.impl;

import org.demo.jyhspringdemo.bean.Teacher;
import org.demo.jyhspringdemo.interfaces.ITeacher;
import org.demo.jyhspringdemo.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @author goufj
 * @date 2024-09-06
 */
@Service
public class TeacherImpl implements ITeacher {

    @Autowired
    private TeacherMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean teach(Teacher teacher) {
        TransactionStatus txStatus = TransactionAspectSupport.currentTransactionStatus();

        System.out.println("teach before: "+txStatus);

        int insert = mapper.insert(teacher);
        return insert > 0;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.REQUIRES_NEW)
    public boolean teachNew(Teacher teacher) {
        TransactionStatus txStatus = TransactionAspectSupport.currentTransactionStatus();

        System.out.println("teachNew save: "+txStatus);
        int insert = mapper.insert(teacher);
        if (true) {
            throw new RuntimeException();

        }
        return insert > 0;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class, propagation = Propagation.NEVER)
    public void teachNever(Teacher teacher) {
        TransactionStatus txStatus = TransactionAspectSupport.currentTransactionStatus();

        System.out.println("teachNever save: "+txStatus);

        mapper.insert(teacher);
        throw new RuntimeException();
    }
}
