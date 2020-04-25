package com.bootzufang.service.impl;

import com.bootzufang.mapper.LianJiaMapper;
import com.bootzufang.pojo.LianjiaInfo;
import com.bootzufang.pojo.LianjiaRentInfo;
import com.bootzufang.service.LianjiaRentSortService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class LianjiaRentSortServiceImpl implements LianjiaRentSortService {
    @Resource
    private LianJiaMapper lianJiaMapper;



    @Override
    public List<LianjiaRentInfo> getLianjiaRentInfo() {
        List<LianjiaRentInfo> listLianjiaRent = lianJiaMapper.getLianjiaRentInfo();
        System.out.println(listLianjiaRent.size());
        return null;
    }

    @Override
    public List<LianjiaInfo> getLianjiaAuto() {
        List<LianjiaInfo> list = lianJiaMapper.getlianjiaAutoInfo();
        System.out.println(list.size());
        return null;
    }

}
