package com.bootzufang;
import com.bootzufang.pojo.LianjiaRentInfo;
import com.bootzufang.service.LianjiaRentSortService;
import com.bootzufang.service.impl.LianjiaRentSortServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Test1 {

    @Test
    public void test1(){
        LianjiaRentSortService lianjiaRentSortService = new LianjiaRentSortServiceImpl();
//        List<LianjiaRentInfo> list1 = lianjiaRentSortService.getLianjiaRentInfo();
        lianjiaRentSortService.getLianjiaAuto();

    }
}
