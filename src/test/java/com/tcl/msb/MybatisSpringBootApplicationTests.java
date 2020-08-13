package com.tcl.msb;

import com.tcl.msb.mapper.BlogMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisSpringBootApplicationTests {

    @Autowired
    BlogMapper mapper;

    @Test
    void getStarted() {
        mapper.selectAll().forEach(System.out::println);
    }

}
