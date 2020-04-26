package com.bootzufang.service.impl;

import com.bootzufang.mapper.LianJiaMapper;
import com.bootzufang.pojo.LianjiaInfo;
import com.bootzufang.pojo.LianjiaRentInfo;
import com.bootzufang.pojo.LianjiaRentSimpleInfo;
import com.bootzufang.service.LianjiaRentSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LianjiaRentSortServiceImpl implements LianjiaRentSortService {
    @Resource
    private LianJiaMapper lianJiaMapper;



    @Override
    public List<LianjiaRentSimpleInfo> getLianjiaRentInfo() {
        long startTime=System.currentTimeMillis();   //获取开始时间

        List<LianjiaRentSimpleInfo> lianjiaRentInfo = new ArrayList<>(40000);
        lianjiaRentInfo = lianJiaMapper.getLianjiaRentInfo();

        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println(lianjiaRentInfo.size());
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
        return null;
    }

}
