package org.demo.jyhspringdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.demo.jyhspringdemo.bean.Teacher;
import org.springframework.stereotype.Repository;

/**
 * @author goufj
 * @date 2024-09-06
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
}
