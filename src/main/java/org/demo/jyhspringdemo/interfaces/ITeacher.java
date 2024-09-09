package org.demo.jyhspringdemo.interfaces;

import org.demo.jyhspringdemo.bean.Teacher;

/**
 * @author goufj
 * @date 2024-09-06
 */
public interface ITeacher {

    boolean teach(Teacher teacher);

    boolean teachNew(Teacher teacher);

    void teachNever(Teacher teacher);
}
