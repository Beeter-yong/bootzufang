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

    //搜索小区页面
    @RequestMapping("/searchCommunity")
    public String searchCommunity(){
        return "searchCommunity";
    }
    //搜索小区转接
    @RequestMapping("/analysisCommunity")
    public String analysisCommunity(HttpServletRequest request, Model model){
        //1、获取小区名字
        String communityName = request.getParameter("comm");
        //2、查询小区是否存在
        LianjiaInfo lianjiaInfo = null;
        try {
            lianjiaInfo = lianJiaMapper.getLianjiaInfoByName(communityName);
        }catch (Exception e){
            System.out.println("小区不存在");

        }

        //3、存在转发页面
        if(lianjiaInfo == null){
            return "searchCommunity";
        }else{
            return "forward:/community?id=" + lianjiaInfo.getId();
        }
    }

    //社区详情展示
    @RequestMapping("/community")
    public String communityPage(HttpServletRequest request, Model model){
        //1、获取社区对应id、名字
        //2、根据id查询小区数据库，获取社区信息
        LianjiaInfo lianjiaInfo = lianJiaMapper.getLianjiaInfo(Integer.parseInt(request.getParameter("id")));
        //3、根据小区名字，查询租房数据库，查询小区中的房屋结构、以及对应数量和均价
        String name = lianjiaInfo.getCommunityName();
        //4、查询房源数据库，查找网站中房屋结构、以及对应数量和均价
        List<StringNumNum> listHoseType = lianJiaMapper.getLianjiaStringNumPrice();
        //5、根据小区名字查询房源数据库，查找小区中房屋结构、以及对应的数量和均价
        List<StringNumNum> rentStringNumPrice = lianJiaMapper.getRentStringNumPrice(lianjiaInfo.getCommunityName());
        //6、根据小区名字查找房源数据库，查找小区中所有房源信息
        List<LianjiaRentInfo> rentByCommunity = lianJiaMapper.getRentByCommunity(lianjiaInfo.getCommunityName());

        //7、传入model中
        model.addAttribute("lianjiaInfo", lianjiaInfo); //小区信息
        model.addAttribute("listHoseType", listHoseType);   //网站房屋结构以及均价和数量
        model.addAttribute("rentStringNumPrice", rentStringNumPrice);   //一个小区内的房屋结构以及均价和数量
        model.addAttribute("rentByCommunity", rentByCommunity);     //一个小区内的所有房源信息

        return "community";
    }
    //推荐页面
    @RequestMapping("/recommend")
    public String recommendPage(Model model){
        List<StringNum> lianjiaHomeTypelists = lianJiaMapper.getlianjiaHomeTypeInfo();
        model.addAttribute("lianjiaHomeTypelists", lianjiaHomeTypelists);
        return "recommend";
    }

    //推荐结果
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
