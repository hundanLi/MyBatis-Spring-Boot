package com.tcl.msb.mapper;

import com.tcl.msb.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author li
 * @version 1.0
 * @date 2020/8/13 19:51
 */
//@Mapper
public interface BlogMapper {
    List<Blog> selectAll();
}
