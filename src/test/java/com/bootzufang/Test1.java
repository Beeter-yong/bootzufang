package com.bootzufang;
import com.bootzufang.pojo.LianjiaRentInfo;
import com.bootzufang.service.LianjiaRentSortService;
import com.bootzufang.service.impl.LianjiaRentSortServiceImpl;
import com.bootzufang.utils.Coordinate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@MybatisTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(classes = {BootzufangApplicationTests.class})
@Rollback(false)
public class Test1 {
//    @Autowired
//    private LianjiaRentSortServiceImpl lianjiaRentSortService;
//
//    @Test
//    public void test1(){
//        System.out.println("nininio");
//        lianjiaRentSortService.getLianjiaRentInfo();
//
//    }

    @Test
//    测试坐标转换
    public void test2(){
        Coordinate coordinate = new Coordinate();
        Map map = coordinate.getLatAndLngByAddress("北京石园");
        System.out.println(map);
    }
}
