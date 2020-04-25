package com.bootzufang.service;

import com.bootzufang.pojo.LianjiaInfo;
import com.bootzufang.pojo.LianjiaRentInfo;

import java.util.List;

public interface LianjiaRentSortService {
    //获取链家所有小区信息
    List<LianjiaRentInfo> getLianjiaRentInfo();

    //获取链家随机十个小区
    List<LianjiaInfo> getLianjiaAuto();
}
