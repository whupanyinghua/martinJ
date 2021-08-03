package com.pyh.tianhong;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pyh.util.HttpUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 类Tianhong的实现描述：一个有趣的小功能
 *
 * @author panyinghua 2021-5-12 15:49
 */
public class Tianhong {

    private static Random random = new Random(System.nanoTime());

    public static String targetStoreCode="00133";

    public static String batchListUrl = "https://api.tianhong.cn/coupon-ms-digi-app/mall/batchList";
    public static Map<String, String> headers2 = Maps.newHashMap();
    public static Map<String, String> nonloginheaders = Maps.newHashMap();
    static {
        headers2.put("Host","api.tianhong.cn");
        headers2.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat");
        headers2.put("content-type","application/json");
        headers2.put("x-http-channel","Coupon for rainbowcn mini project");
        headers2.put("x-http-devicetype","miniapp");
        //headers2.put("x-http-token","*****");
        headers2.put("x-http-version","2.5.3");
        headers2.put("Referer","https://servicewechat.com/wx83b25ac313aea733/243/page-frame.html");
        headers2.put("Accept-Encoding","gzip, deflate, br");


        nonloginheaders.put("Host","api.tianhong.cn");
        nonloginheaders.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36 MicroMessenger/7.0.9.501 NetType/WIFI MiniProgramEnv/Windows WindowsWechat");
        nonloginheaders.put("content-type","application/json");
        nonloginheaders.put("x-http-channel","Coupon for rainbowcn mini project");
        nonloginheaders.put("x-http-devicetype","miniapp");
        nonloginheaders.put("x-http-version","2.5.3");
        nonloginheaders.put("Accept-Encoding","gzip, deflate, br");
    }


    public static void main(String[] args) {

        //long startIndex = 1154864;
        //List<Long> targetCouponIds = getTianhongCoupunList(startIndex, Long.MAX_VALUE, 5);
        //System.out.println("targetCouponIds:"+JSONObject.toJSONString(targetCouponIds));

        long beginIndex = 1167536;
        long endIndex = 1175961;
        // 第一步：获取当前时间已经发放的优惠券最大id
        long maxIndex = getMaxCouponIdNow(beginIndex,endIndex);
        System.out.println("maxCouponId is :" + maxIndex);
        // 第二步：根据获取到的最大maxid，以及上一次执行记录的id记为minid，座位区间[minid,maxid]来求解本次目标id
        /*List<Long> couponIdList = getTianhongCoupunList(1165646,1165656,10);
        Long[] couponIds = couponIdList.toArray(new Long[]{});
        Arrays.sort(couponIds);
        System.out.println("tianhongCouponIds is :" + JSON.toJSONString(couponIds));
        System.out.println("onlyTargetCouponId is :" + JSON.toJSONString(getOnlyTargetCoupons(couponIdList)));*/
        // 第二步的优化方法：
        System.out.println("onlyTargetCouponId is :" + JSON.toJSONString(getTianhongCoupunListWithOnlyOneStore(beginIndex,maxIndex,10, targetStoreCode)));
    }

    /**
     * 获取到当前已经发放的最大id
     * @param startCouponIndex
     * @param endCouponIndex
     * @return
     */
    public static long getMaxCouponIdNow(long startCouponIndex, long endCouponIndex) {
        long resultId = -1;
        long minCouponId = startCouponIndex;
        long maxCouponId = endCouponIndex;

        List<Long> ids = new LinkedList<>();
        while(minCouponId<=maxCouponId) {
            ids.clear();
            long mid = (maxCouponId-minCouponId)/2 + minCouponId;
            ids.add(mid);
            JSONObject param = new JSONObject();
            param.put("couponIds", ids);
            System.out.println("heads:"+JSONObject.toJSONString(nonloginheaders));
            System.out.println("param:" + JSONObject.toJSONString(param));
            String resultTxt = HttpUtil.sendPost(batchListUrl, JSONObject.toJSONString(param), nonloginheaders);
            System.out.println("result:" + resultTxt);
            try {
                long sleepTime = 2000+random.nextInt(1000);
                System.out.println("sleep "+sleepTime+" ms.");
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JSONObject result = JSONObject.parseObject(resultTxt);
            int code = result.getInteger("code");
            if(code==200) {
                JSONArray data = result.getJSONArray("data");
                if(null == data || data.size()==0) {
                    maxCouponId = mid-1;
                } else {
                    resultId = mid;
                    minCouponId = mid+1;
                }
            } else if(code==5000) {
                maxCouponId = mid-1;
            } else {
                System.out.println("未知结果");
                // 当做是没有查找到优惠券
                maxCouponId = mid-1;
            }
        }

        return resultId;
    }


    /**
     * 查找区间[startCouponIndex,endIndex]内的优惠券是否是目标门店发出的优惠券
     * 包含目标门店或者其他门店也一起发放的优惠券，例如idx是个优惠券，目标门店还有其他门店都有发出该优惠券，那么idx会添加到结果集
     * @param startCouponIndex 起始id
     * @param endIndex 结束id
     * @param step 步长，每次去请求接口的时候，会带上step个优惠券id进行请求
     * @return 返回[startCouponIndex,endIndex]区间内目标门店发出的优惠券（包含目标门店或者其他门店也一起发放的优惠券）
     */
    public static List<Long> getTianhongCoupunList(long startCouponIndex, long endIndex, int step) {
        List<Long> coupunIds = new ArrayList<>();
        if(endIndex<startCouponIndex) {
            return coupunIds;
        }

        List<Long> ids = new LinkedList<>();

        long currentIndex = startCouponIndex;
        long loop = (endIndex-startCouponIndex+1)/step;
        while(loop>0) {
            ids.clear();
            for(long i=0;i<step;i++) {
                ids.add(currentIndex);
                currentIndex++;
            }
            // 去查找目标优惠券
            //System.out.println(ids);
            coupunIds.addAll(getTargetCoupons(ids));
            loop--;
        }

        // 因为最后一次进行ids的组装的时候，可能不满足step，所以放在while循环外部来进行
        ids.clear();
        for(long i=currentIndex;i<=endIndex;i++) {
            ids.add(currentIndex);
            currentIndex++;
        }
        // 去查找目标优惠券
        //System.out.println(ids);
        if(ids.size()>0) {
            coupunIds.addAll(getTargetCoupons(ids));
        }
        // 对结果数据排序(因为在批量查询id的时候，返回的结果id不一定是有序的)
        Collections.sort(coupunIds);
        return coupunIds;
    }

    /**
     * 查找区间[startCouponIndex,endIndex]内的优惠券是否是目标门店发出的优惠券
     * 只包含目标门店一个门店单独发放的优惠券，例如idx是个优惠券，只有目标门店发出该优惠券，其他家门店都没有发出，那么idx会添加到结果集
     * 注意与 getTianhongCoupunList 方法的区别
     * @param startCouponIndex 起始id
     * @param endIndex 结束id
     * @param step 步长，每次去请求接口的时候，会带上step个优惠券id进行请求
     * @param storeCode 门店编号，目标门店的编号，用于筛选优惠券所属门店
     * @return 返回[startCouponIndex,endIndex]区间内只有目标门店发出的优惠券
     */
    public static List<Long> getTianhongCoupunListWithOnlyOneStore(long startCouponIndex, long endIndex, int step, String storeCode) {
        List<Long> coupunIds = new ArrayList<>();
        if(endIndex<startCouponIndex) {
            return coupunIds;
        }

        // 原始数据初步处理
        int length = (int) (endIndex-startCouponIndex+1);
        List<Long> oriIds = new ArrayList<>(length);
        for(int i=0;i<length;i++) {
            oriIds.add(i, startCouponIndex+i);
        }
        // 原始数据随机打乱
        Collections.shuffle(oriIds);


        List<Long> ids = new LinkedList<>();

        int currentIndex = 0;
        long loop = (endIndex-startCouponIndex+1)/step;
        while(loop>0) {
            ids.clear();
            for(long i=0;i<step;i++) {
                ids.add(oriIds.get(currentIndex));
                currentIndex++;
            }
            // 去查找目标优惠券
            //System.out.println(ids);
            coupunIds.addAll(getTargetCoupons(ids, storeCode));
            loop--;
        }

        // 因为最后一次进行ids的组装的时候，可能不满足step，所以放在while循环外部来进行
        ids.clear();
        for(long i=currentIndex;i<length;i++) {
            ids.add(oriIds.get(currentIndex));
            currentIndex++;
        }
        // 去查找目标优惠券
        //System.out.println(ids);
        if(ids.size()>0) {
            coupunIds.addAll(getTargetCoupons(ids, storeCode));
        }

        // 对结果数据排序
        Collections.sort(coupunIds);
        return coupunIds;
    }

    /**
     * 获取优惠券列表ids中的属于目标门店发出的优惠券 （注意，这里的属于目标门店指的是目标门店自己发出的以及包含有目前门店的很多门店通用的优惠券）
     * @param ids
     * @return
     */
    private static List<Long> getTargetCoupons(List<Long> ids) {
        List<Long> coupunIds = Lists.newArrayList();
        JSONObject param = new JSONObject();
        param.put("couponIds", ids);
        System.out.println("heads:"+JSONObject.toJSONString(nonloginheaders));
        System.out.println("param:"+JSONObject.toJSONString(param));
        String resultTxt = HttpUtil.sendPost(batchListUrl, JSONObject.toJSONString(param), nonloginheaders);
        System.out.println("result:"+resultTxt);

        try {
            long sleepTime = 0+random.nextInt(1000);
            System.out.println("sleep "+sleepTime+" ms.");
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JSONObject result = JSONObject.parseObject(resultTxt);
        if(result.getInteger("code")==200) {
            JSONArray data = result.getJSONArray("data");
            if(null != data && data.size()>0) {
                int size = data.size();
                for(int i=0;i<size;i++) {
                    JSONObject couponObj = data.getJSONObject(i);
                    long couponId = couponObj.getLong("couponId");
                    JSONArray storeInfos = couponObj.getJSONArray("storeInfos");
                    if(null != storeInfos && storeInfos.size()>0) {
                        int storeInfoSize = storeInfos.size();
                        for(int j=0;j<storeInfoSize;j++) {
                            JSONObject storeObj = storeInfos.getJSONObject(j);
                            if(StringUtils.equals(targetStoreCode, storeObj.getString("storeCode"))) {
                                // 当前优惠券是我们关注的天虹门店发出的，可以直接结束内层循环
                                coupunIds.add(couponId);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return coupunIds;
    }

    /**
     * 获取优惠券列表ids中的属于目标门店发出的优惠券 （注意，这里的属于目标门店指的是只有目标门店自己发出的优惠券）
     * @param ids
     * @return
     */
    private static List<Long> getTargetCoupons(List<Long> ids, String storeCode) {
        List<Long> coupunIds = Lists.newArrayList();
        JSONObject param = new JSONObject();
        param.put("couponIds", ids);
        System.out.println("heads:"+JSONObject.toJSONString(nonloginheaders));
        System.out.println("param:"+JSONObject.toJSONString(param));
        String resultTxt = HttpUtil.sendPost(batchListUrl, JSONObject.toJSONString(param), nonloginheaders);
        System.out.println("result:"+resultTxt);

        try {
            long sleepTime = 0+random.nextInt(1000);
            System.out.println("sleep "+sleepTime+" ms.");
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JSONObject result = JSONObject.parseObject(resultTxt);
        if(result.getInteger("code")==200) {
            JSONArray data = result.getJSONArray("data");
            if(null != data && data.size()>0) {
                int size = data.size();
                for(int i=0;i<size;i++) {
                    JSONObject couponObj = data.getJSONObject(i);
                    long couponId = couponObj.getLong("couponId");
                    JSONArray storeInfos = couponObj.getJSONArray("storeInfos");
                    if(null != storeInfos && storeInfos.size()==1) {
                        int storeInfoSize = storeInfos.size();
                        JSONObject storeObj = storeInfos.getJSONObject(0);
                        if(StringUtils.equals(storeCode, storeObj.getString("storeCode"))) {
                            // 当前优惠券是我们关注的天虹门店发出的，可以直接结束内层循环
                            coupunIds.add(couponId);
                        }
                    } else {
                        // storeInfos结构为空表示查询不到，不是当前的场景
                        // storeInfos结构数据有多个，则表示是查询多个id，不是当前的场景
                        System.out.println("storeInfos不是当前关注的");
                    }
                }
            }
        }

        return coupunIds;
    }




    /**
     * 遍历传递进来的id列表，找出只有目标天虹商场发放的优惠券id,本方法是遍历ids中的所有id，然后一个一个id去发送请求
     * @param ids
     * @return
     */
    public static List<Long> getOnlyTargetCoupons(List<Long> ids) {
        List<Long> coupunIds = Lists.newArrayList();

        loop:
        for(Long id: ids) {
            List<Long> curIds = new ArrayList<>();
            curIds.add(id);
            JSONObject param = new JSONObject();
            param.put("couponIds", curIds);
            System.out.println("heads:"+JSONObject.toJSONString(nonloginheaders));
            System.out.println("param:"+JSONObject.toJSONString(param));
            String resultTxt = HttpUtil.sendPost(batchListUrl, JSONObject.toJSONString(param), nonloginheaders);
            System.out.println("result:"+resultTxt);

            try {
                long sleepTime = 1000+random.nextInt(1000);
                System.out.println("sleep "+sleepTime+" ms.");
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            JSONObject result = JSONObject.parseObject(resultTxt);
            if(result.getInteger("code")==200) {
                JSONArray data = result.getJSONArray("data");
                if(null != data && data.size()==1) {
                    JSONObject couponObj = data.getJSONObject(0);
                    long couponId = couponObj.getLong("couponId");
                    JSONArray storeInfos = couponObj.getJSONArray("storeInfos");
                    if(null != storeInfos && storeInfos.size()==1) {
                        JSONObject storeObj = storeInfos.getJSONObject(0);
                        if(StringUtils.equals(targetStoreCode, storeObj.getString("storeCode"))) {
                            // 当前优惠券是我们关注的天虹门店发出的，可以直接结束外层循环
                            coupunIds.add(couponId);
                            continue loop;
                        }
                    } else {
                        // storeInfos结构为空表示查询不到，不是当前的场景
                        // storeInfos结构数据有多个，则表示是查询到有多个门店发了该优惠券，不是当前的场景
                        System.out.println("storeInfos不是当前关注的");
                    }
                } else {
                    // data结构为空表示查询不到，不是当前的场景
                    // data结构数据有多个，则表示是查询多个id，不是当前的场景
                    System.out.println("data不是当前关注的");
                }
            }

        }



        return coupunIds;
    }
}
