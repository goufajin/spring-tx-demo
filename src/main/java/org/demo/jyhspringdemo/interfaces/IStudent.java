package org.demo.jyhspringdemo.interfaces;

import org.demo.jyhspringdemo.bean.Student;

import java.util.List;

/**
 * @author goufj
 * @date 2024-08-24
 */
public interface IStudent {

    public void save(Student student);

    public void saveNew(Student student);

    public void delete(String id);

    public Student getStudent(String id);

    public List<Student> getStudents();

    public void update(Student student);
}
