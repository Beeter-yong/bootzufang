package com.bootzufang.mapper;

import com.bootzufang.pojo.LianjiaInfo;
import com.bootzufang.pojo.LianjiaRentInfo;
import com.bootzufang.pojo.RegionalNum;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LianJiaMapper {

    //获取链家的行政区及其数目
    @Select("SELECT regional, COUNT(*) AS num FROM lianjiaCommunityInfo GROUP BY regional")
    List<RegionalNum> getlianjiaRegionalInfo();

    //随机获取链家有图片且有租房链接的16条数据
    @Select("SELECT * FROM lianjiaCommunityInfo WHERE id >= ((SELECT MAX(id) FROM lianjiaCommunityInfo)-(SELECT MIN(id) FROM lianjiaCommunityInfo)) * RAND() + (SELECT MIN(id) FROM lianjiaCommunityInfo) AND communityImg!='None' AND rentUrl!='None'  LIMIT 16")
    List<LianjiaInfo> getlianjiaAutoInfo();

    //取出链家所有租房房源数据
    @Select("SELECT * FROM lianjiaRentInfo")
    List<LianjiaRentInfo> getLianjiaRentInfo();

}
