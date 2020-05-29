package com.bootzufang.controller;

import com.bootzufang.mapper.LianJiaMapper;
import com.bootzufang.mapper.TongChengMapper;
import com.bootzufang.pojo.*;
import com.bootzufang.service.LianjiaRentSortService;
import com.bootzufang.service.impl.LianjiaRentSortServiceImpl;
import com.bootzufang.utils.Coordinate;
import com.bootzufang.utils.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @RequestMapping("/community")
    public String communityPage(){
        return "community";
    }
    @RequestMapping("/recommend")
    public String recommendPage(Model model){
        List<StringNum> lianjiaHomeTypelists = lianJiaMapper.getlianjiaHomeTypeInfo();
        model.addAttribute("lianjiaHomeTypelists", lianjiaHomeTypelists);
        return "recommend";
    }

    @RequestMapping("/recommendResult")
    public String recommendResultPage(HttpServletRequest request, Model model){
        if(request.getParameter("address")=="" || request.getParameter("direction")==null){
            System.out.println("地址或朝向为空");
            return "recommend";
        }
        Coordinate coordinate = new Coordinate();
        Map<String, Float> address = coordinate.getLatAndLngByAddress(request.getParameter("address"));
        String endLonLat = Float.toString(address.get("lat"))+","+Float.toString(address.get("lng"));
        String homeType = request.getParameter("homType");
        String direction = "%" + request.getParameter("direction") + "%";
        List<LianjiaRentSimpleInfo> lists = lianjiaRentSortService.getLianjiaRentInfo(homeType, direction);
        //排序
        Sort s = new Sort();
        List<SortResult> sortResults = s.sort(lists, endLonLat, request.getParameter("tripType"));
        //推荐
        List<LianjiaRentInfo> lianjiaRentInfos = new ArrayList<>(10);
        if(sortResults.size() > 10){
            for (int i = 0; i < 10; i++){
                Integer id = sortResults.get(i).getId();
                LianjiaRentInfo lianjiaRentInfo = lianJiaMapper.getLianjiaRent(id);
                System.out.println(lianjiaRentInfo);
                lianjiaRentInfos.add(lianjiaRentInfo);
            }
        }else {
            for(int i = 0; i < sortResults.size(); i++){
                Integer id = sortResults.get(i).getId();
                LianjiaRentInfo lianjiaRentInfo = lianJiaMapper.getLianjiaRent(id);
                System.out.println(lianjiaRentInfo);
                lianjiaRentInfos.add(lianjiaRentInfo);
            }
        }
        model.addAttribute("lianjiaRentInfos", lianjiaRentInfos);
        return "recommendResult";
    }

    @RequestMapping("/test2")
    @ResponseBody
    public String test2(){
        List<StringNum> lists = lianJiaMapper.getlianjiaHomeTypeInfo();
        System.out.println(lists.size());
        System.out.println(lists);

        return "aaa";
    }
}
