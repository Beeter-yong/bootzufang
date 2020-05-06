package com.bootzufang.utils;

import com.bootzufang.pojo.LianjiaRentSimpleInfo;
import com.bootzufang.pojo.Matrix;
import com.bootzufang.pojo.SortResult;

import java.util.*;

/**
 * 排序
 */
public class Sort {

    public void sort(List<LianjiaRentSimpleInfo> list){
        long startTime=System.currentTimeMillis();   //获取开始时间
        List<Matrix> matrixList = getConvert(list);
        sortTopsis(matrixList);
        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }

    /**
     * 使用TOPSIS对矩阵进行排序
     */
    private void sortTopsis(List<Matrix> matrixList){   //需要传参用户的理性数据
        Double price_best = 3000d;
        Double area_best = 50d;
        Double distance_best = 4000d;
        Double max_price = 0d;
        Double max_area = 0d;
        Double max_distance = 0d;
        Matrix matrix;
        //指标正向化
        /*
        //求最对大绝对值
        for (int i = 0; i < matrixList.size(); i++){
            matrix = matrixList.get(i);
            if(Math.abs(matrix.getPrice() - price_best) > max_price){
                max_price = Math.abs(matrix.getPrice() - price_best);
            }
            if(Math.abs(matrix.getArea() - area_best) > max_area){
                max_area = Math.abs(matrix.getArea() - area_best);
            }
            if(Math.abs(matrix.getDistance() - distance_best) > max_distance){
                max_distance = Math.abs(matrix.getDistance() - distance_best);
            }
        }
        //中间型转化为极大型
        List<Matrix> matrixListProcess = new ArrayList<>(5000);
        for (int i = 0; i < matrixList.size(); i++){
            matrix = matrixList.get(i);
            //处理价格
            Double abs = Math.abs(matrix.getPrice() - price_best);
            matrix.setPrice(1-(abs/max_price));
            //处理面积
            abs = Math.abs(matrix.getArea() - area_best);
            matrix.setArea(1-(abs/max_area));
            //处理距离
            abs = Math.abs(matrix.getDistance() - distance_best);
            matrix.setDistance(1-(abs/max_distance));
            matrixListProcess.add(matrix);
        }
        matrixList = null;
        */

        //价格采用极小型,面积是极大型，距离是极小型
        for(int i = 0; i < matrixList.size(); i++){
            matrix = matrixList.get(i);
            if(matrix.getPrice() > max_price){
                max_price = matrix.getPrice();
            }
            if(matrix.getDistance() > max_distance){
                max_distance = matrix.getDistance();
            }
        }
        List<Matrix> matrixListProcess = new ArrayList<>(5000);
        for (int i = 0; i < matrixList.size(); i++){
            matrix = matrixList.get(i);
            matrix.setPrice(max_price - matrix.getPrice());
            matrix.setDistance(max_distance - matrix.getDistance());
            matrixListProcess.add(matrix);
        }
        System.out.println("正向化后："+matrixListProcess.size() +matrixListProcess);

        //标准化处理
        List<Matrix> matrixListBiaoZhun = new ArrayList<>(5000);
        double sum_price = 0;
        double sum_area = 0;
        double sum_distance = 0;
        for(int i = 0; i < matrixListProcess.size(); i++){
            matrix = matrixListProcess.get(i);
            sum_price = sum_price + Math.pow(matrix.getPrice(), 2);
            sum_area = sum_area + Math.pow(matrix.getArea(), 2);
            sum_distance = sum_distance + Math.pow(matrix.getDistance(), 2);
        }
        sum_price = Math.pow(sum_price, 0.5);
        sum_area = Math.pow(sum_area, 0.5);
        sum_distance = Math.pow(sum_distance, 0.5);
        for(int i = 0; i < matrixListProcess.size(); i++){
            matrix = matrixListProcess.get(i);
            matrix.setPrice(matrix.getPrice() / sum_price);
            matrix.setArea(matrix.getArea() / sum_area);
            matrix.setPrice(matrix.getDistance() / sum_distance);
            matrixListBiaoZhun.add(matrix);
        }
        System.out.println("标准化："+ matrixListBiaoZhun);
        //求权重-熵权法
        Map<String, Double> map = getWeight(matrixListBiaoZhun);
        Double wPrice = map.get("WPrice");
        Double wArea = map.get("WArea");
        Double wDistance = map.get("WDistance");

        //多指标评分
        Double zPriceMax = 0d;
        Double zAreaMax = 0d;
        Double zDistanceMax = 0d;
        Double zPriceMin = matrixListBiaoZhun.get(1).getPrice();
        Double zAreaMin = matrixListBiaoZhun.get(1).getArea();
        Double zDistanceMin = matrixListBiaoZhun.get(1).getDistance();
        for(int i = 0; i < matrixListBiaoZhun.size(); i++){
            matrix = matrixListBiaoZhun.get(i);
            if(matrix.getPrice() > zPriceMax){
                zPriceMax = matrix.getPrice();
            }
            if(matrix.getPrice() < zPriceMin){
                zPriceMin = matrix.getPrice();
            }
            if(matrix.getArea() > zAreaMax){
                zAreaMax = matrix.getArea();
            }
            if(matrix.getArea() < zAreaMin){
                zAreaMin = matrix.getArea();
            }
            if(matrix.getDistance() > zDistanceMax){
                zDistanceMax = matrix.getDistance();
            }
            if(matrix.getDistance() < zAreaMin){
                zDistanceMin = matrix.getDistance();
            }
        }

       //计算评分存入结果集中
        List<SortResult> sortResults = new ArrayList<>(5000);

        for (int i = 0; i < matrixListBiaoZhun.size(); i++){
            matrix = matrixListBiaoZhun.get(i);
            Double DMax = wPrice * Math.pow((zPriceMax - matrix.getPrice()), 2) + wArea * Math.pow((zAreaMax - matrix.getArea()), 2) + wDistance * Math.pow((zDistanceMax - matrix.getDistance()), 2);
            DMax = Math.pow(DMax, 0.5);
            Double DMin = wPrice * Math.pow((zPriceMin - matrix.getPrice()), 2) + wArea * Math.pow((zAreaMin - matrix.getArea()), 2) + wDistance * Math.pow((zDistanceMin - matrix.getDistance()), 2);
            DMin = Math.pow(DMin, 0.5);

            SortResult sortResult = new SortResult();
            sortResult.setId(matrix.getId());
            sortResult.setScore(DMin/(DMax + DMin));
            sortResults.add(sortResult);
        }
        System.out.println("结果评分：" + sortResults.size() + sortResults);

        //排序返回
        Collections.sort(sortResults, new Comparator<SortResult>() {
            @Override
            public int compare(SortResult o1, SortResult o2) {
                if(o1.getScore() - o2.getScore() < 0) return 1;
                if(o1.getScore() - o2.getScore() > 0) return -1;
                return 0;
            }
        });
        System.out.println("排序后：" + sortResults);

    }

    /**
     * 熵权法求权重
     */
    private Map<String, Double> getWeight(List<Matrix> list){
        //求出各个指标的和
        Double sum_price = 0d;
        Double sum_area = 0d;
        Double sum_distance = 0d;
        for (int i = 0; i < list.size(); i++){
            sum_price = sum_price + list.get(i).getPrice();
            sum_area = sum_area + list.get(i).getArea();
            sum_distance = sum_distance + list.get(i).getDistance();
        }
        Double e_price = 0d;
        Double e_area = 0d;
        Double e_distance = 0d;
        for(int i = 0; i < list.size(); i++){
            //各指标项所占比重
            Double p_price = list.get(i).getPrice() / sum_price;
            Double p_area = list.get(i).getArea() / sum_area;
            Double p_distance = list.get(i).getDistance() / sum_distance;
            //计算熵值第一步
            if(p_price != 0d && p_area != 0d && p_distance != 0d) {
                e_price = e_price + p_price * Math.log(p_price);
                e_area = e_area + p_area * Math.log(p_area);
                e_distance = e_distance + p_distance * Math.log(p_distance);
            }
        }
        //计算计算信息熵冗余度
        Double d_price = 1 + (1 / Math.log(list.size()) * e_price);
        Double d_area = 1 + (1 / Math.log(list.size()) * e_area);
        Double d_distance = 1 + (1 / Math.log(list.size()) * e_distance);
        Double w_sum = d_price + d_area + d_distance;
        Double w_price = d_price / w_sum;
        Double w_area = d_area / w_sum;
        Double w_distance = d_distance / w_sum;

        Map<String, Double> map = new HashMap();
        map.put("WPrice", w_price);
        map.put("WArea", w_area);
        map.put("WDistance", w_distance);
        return map;
    }
    /**
     * 将所有小区的地理位置转化为坐标
     * 求出与目标点之间的距离
     */
    private List<Matrix> getConvert(List<LianjiaRentSimpleInfo> list){  //需要更改函数传入目标地址
        Coordinate coordinate = new Coordinate();
        GetDistance Distance = new GetDistance();
        List<Matrix> matrixList = new ArrayList<Matrix>(5000);
        Map<String, Double> distanceMap = new HashMap<>(5000);

        for(int i = 0; i < list.size(); i++){
            Matrix matrix = new Matrix();
            matrix.setId(list.get(i).getId());
            matrix.setPrice(Double.parseDouble(list.get(i).getPrice()));
            matrix.setArea(Double.parseDouble(list.get(i).getArea().replace("㎡","")));

            Double distance;
            if(!distanceMap.containsKey(list.get(i).getAddress())){
                //如果已经查询过这个地址就不再请求网络查询，而是从哈希表中获取
                Map<String, Float> map = coordinate.getLatAndLngByAddress("北京市"+list.get(i).getAddress());
                String startLonLat = Float.toString(map.get("lat")) + "," + Float.toString(map.get("lng"));
                String endLonLat = "39.915,116.404";
                distance = Distance.getDistance(startLonLat, endLonLat, "1");
                distanceMap.put(list.get(i).getAddress(), distance);
            }else{
                distance = distanceMap.get(list.get(i).getAddress());
            }

            matrix.setDistance(distance);
            matrixList.add(matrix);
        }

        return matrixList;
    }


}
