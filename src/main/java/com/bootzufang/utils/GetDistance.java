package com.bootzufang.utils;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 对两个坐标点距离的计算
 */
public class GetDistance {

    public static Long getDistance(String startLonLat, String endLonLat, String select){
//    public static Long getDistance(){
        //返回起始地startAddr与目的地endAddr之间的距离，单位：米
        Long result = 0L;
        String queryUrl;
        switch (select){
            case "0"://驾车路线
                queryUrl = "http://api.map.baidu.com/directionlite/v1/driving?origin="+startLonLat+"&destination="+endLonLat+"&ak=Ed1XKlLKps6Tiqqd4q7q3GHiHrNEW8SZ";
                break;
            case  "1"://骑行路线规划
                queryUrl = "http://api.map.baidu.com/directionlite/v1/riding?origin="+startLonLat+"&destination="+endLonLat+"&ak=Ed1XKlLKps6Tiqqd4q7q3GHiHrNEW8SZ";
                break;
            case  "2"://步行路线规划
                queryUrl = "http://api.map.baidu.com/directionlite/v1/walking?origin="+startLonLat+"&destination="+endLonLat+"&ak=Ed1XKlLKps6Tiqqd4q7q3GHiHrNEW8SZ";
                break;
            case  "3"://公交路线规划
                queryUrl = "http://api.map.baidu.com/directionlite/v1/transit?origin="+startLonLat+"&destination="+endLonLat+"&ak=Ed1XKlLKps6Tiqqd4q7q3GHiHrNEW8SZ";
                break;
        }

        queryUrl = "http://api.map.baidu.com/directionlite/v1/walking?origin="+startLonLat+"&destination="+endLonLat+"&ak=Ed1XKlLKps6Tiqqd4q7q3GHiHrNEW8SZ";
        String queryResult = getResponse(queryUrl);

        JSONObject jo = JSONObject.fromObject(queryResult);
        net.sf.json.JSONObject ja = jo.getJSONObject("result");
        //获取距离
        Object obj=JSONObject.fromObject(ja.getJSONArray("routes").getString(0)).get("distance");
        if(obj==null){
            return result;
        }
        result = Long.parseLong(obj.toString());
        System.out.println("result=   "+result);
        return result;
    }

    private static String getResponse(String serverUrl){
        //用JAVA发起http请求，并返回json格式的结果
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while((line = in.readLine()) != null){
                result.append(line);
            }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
