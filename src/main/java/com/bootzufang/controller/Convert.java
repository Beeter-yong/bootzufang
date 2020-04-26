package com.bootzufang.controller;

import com.bootzufang.mapper.LianJiaMapper;
import com.bootzufang.mapper.TongChengMapper;
import com.bootzufang.pojo.*;
import com.bootzufang.service.LianjiaRentSortService;
import com.bootzufang.service.impl.LianjiaRentSortServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
//@ResponseBody
public class Convert {
    @Autowired
    private LianJiaMapper lianJiaMapper;
    @Autowired
    private TongChengMapper tongChengMapper;
    @Autowired
    private LianjiaRentSortServiceImpl lianjiaRentSortService;

    @RequestMapping("/")
    public String indexPage(Model model){
        //获取链家与58同城的行政区和数量
        List<RegionalNum> lianjiaRegionalNums = lianJiaMapper.getlianjiaRegionalInfo();
        List<RegionalNum> tongchengRegionalNums = tongChengMapper.getlianjiaRegionalInfo();
        lianjiaRegionalNums.get(3).setNum(lianjiaRegionalNums.get(2).getNum() + lianjiaRegionalNums.get(3).getNum());
        lianjiaRegionalNums.remove(2);
        model.addAttribute("lianjiaRegionalNum", lianjiaRegionalNums);
        model.addAttribute("tongchengRegionalNums", tongchengRegionalNums);

        //随机获取链家与58同城各16条小区数据
        List<LianjiaInfo> lianjiaAutoInfo = lianJiaMapper.getlianjiaAutoInfo();
        List<TongchengInfo> tongchengAutoInfo = tongChengMapper.gettongchengAutoInfo();
        model.addAttribute("lianjiaAutoInfos", lianjiaAutoInfo);
        model.addAttribute("tongchengAutoInfos", tongchengAutoInfo);

        return "index";
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test1(){
        System.out.println("==--------------------------------");
        lianjiaRentSortService.getLianjiaRentInfo();

        return "aaa";
    }
}
