package com.bootzufang.service;

import com.bootzufang.pojo.LianjiaInfo;
import com.bootzufang.pojo.LianjiaRentInfo;
import com.bootzufang.pojo.LianjiaRentSimpleInfo;

import java.util.List;

public interface LianjiaRentSortService {
    //获取链家所有小区简化信息
    List<LianjiaRentSimpleInfo> getLianjiaRentInfo();

}
