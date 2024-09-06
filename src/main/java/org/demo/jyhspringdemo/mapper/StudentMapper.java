package org.demo.jyhspringdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.demo.jyhspringdemo.bean.Student;

import java.util.List;

/**
 *
 *
 *
 *
 * @author goufj
 * @date 2023-09-28
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Select("select id,name from student")
    public List<Student> findAll();
}
