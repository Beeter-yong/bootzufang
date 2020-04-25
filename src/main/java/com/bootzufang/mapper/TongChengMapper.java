package com.bootzufang.mapper;

import com.bootzufang.pojo.RegionalNum;
import com.bootzufang.pojo.TongchengInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TongChengMapper {

    @Select("SELECT regional, COUNT(*) AS num FROM tongchengCommunityInfo GROUP BY regional")
    List<RegionalNum> getlianjiaRegionalInfo();

    //随机获取58同城有图片且有租房链接的16条数据
    @Select("SELECT * FROM tongchengCommunityInfo WHERE id >= ((SELECT MAX(id) FROM tongchengCommunityInfo)-(SELECT MIN(id) FROM tongchengCommunityInfo)) * RAND() + (SELECT MIN(id) FROM tongchengCommunityInfo) AND communityImg!='' AND rentUrl!='None' LIMIT 16")
    List<TongchengInfo> gettongchengAutoInfo();
}
