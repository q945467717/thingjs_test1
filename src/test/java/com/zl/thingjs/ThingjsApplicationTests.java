package com.zl.thingjs;

import com.zl.model.Line;
import com.zl.service.LineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThingjsApplicationTests {

    @Autowired
    private LineService lineService;

    @Test
    public void contextLoads() {

        Line line = lineService.oneLine("一号线");

        System.out.println(line);

    }

}
