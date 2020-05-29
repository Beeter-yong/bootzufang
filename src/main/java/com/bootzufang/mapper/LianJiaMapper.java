package com.bootzufang.mapper;

import com.bootzufang.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LianJiaMapper {
    //通过ID获取链家房源信息
    @Select("SELECT * FROM lianjiaRentInfo WHERE id=#{id}")
    LianjiaRentInfo getLianjiaRent(@Param("id") int id);

    //获取链家的行政区及其数目
    @Select("SELECT regional, COUNT(*) AS num FROM lianjiaCommunityInfo GROUP BY regional")
    List<RegionalNum> getlianjiaRegionalInfo();

    //获取链家的房源结构及其数目
    @Select("SELECT houseType as name, COUNT(houseType) as num FROM lianjiaRentInfo GROUP BY houseType")
    List<StringNum> getlianjiaHomeTypeInfo();

    //随机获取链家有图片且有租房链接的16条数据
    @Select("SELECT * FROM lianjiaCommunityInfo WHERE id >= ((SELECT MAX(id) FROM lianjiaCommunityInfo)-(SELECT MIN(id) FROM lianjiaCommunityInfo)) * RAND() + (SELECT MIN(id) FROM lianjiaCommunityInfo) AND communityImg!='None' AND rentUrl!='None'  LIMIT 16")
    List<LianjiaInfo> getlianjiaAutoInfo();

    //取出链家租房房源数据
//    @Select("SELECT id, price, area, address FROM lianjiaRentInfo WHERE id >= ((SELECT MAX(id) FROM lianjiaRentInfo)-(SELECT MIN(id) FROM lianjiaRentInfo)) * RAND() + (SELECT MIN(id) FROM lianjiaRentInfo) LIMIT 100")
//    @Select("SELECT id, price, area, address FROM lianjiaRentInfo")
    @Select("SELECT * FROM lianjiaRentInfo WHERE houseType=#{homeType} AND towards LIKE #{direction}")
    List<LianjiaRentSimpleInfo> getLianjiaRentInfo(@Param("homeType") String homeType, @Param("direction") String direction);

}
