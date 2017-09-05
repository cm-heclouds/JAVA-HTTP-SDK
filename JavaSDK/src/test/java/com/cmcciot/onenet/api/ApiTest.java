package com.cmcciot.onenet.api;

import cmcc.iot.onenet.javasdk.api.device.AddDevicesApi;
import cmcc.iot.onenet.javasdk.api.device.FindDevicesListApi;
import cmcc.iot.onenet.javasdk.model.Location;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import cmcc.iot.onenet.javasdk.response.device.DeviceList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuzhou on 2017/3/21.
 */
public class ApiTest {

    @Test
    public void testFinddevicesList() {
        String key = "9ylHzkz25nre41i=SuJR=F=k5kU=";

        /**
         * 模糊查询设备
         * 参数顺序与构造函数顺序一致
         * @param keywords:匹配关键字（可选，从id和title字段中左匹配）,String
         * @param authinfo:鉴权信息（可选，对应注册时的sn参数，唯一设备编号）,Object
         * @param devid: 指定设备ID，多个用逗号分隔，最多100个（可选）,String
         * @param begin:起始时间，包括当天（可选）,Date
         * @param end:结束时间，包括当天（可选）,Date
         * @param tags:设备标签（可选，可为一个或多个）,List<String>
         * @param isPrivate： 设备私密性，Boolean类型
         * @param page:指定页码，最大页数为10000（可选）,Integer
         * @param perpage:指定每页输出设备个数，默认30，最多100（可选）,Integer
         * @param isOnline:在线状态（可选）
         * @param key:masterkey
         */
        FindDevicesListApi api = new FindDevicesListApi(null, null, null, null, null, null, null, null, null, null,
                key);
        BasicResponse<DeviceList> response = api.executeApi();
        System.out.println("errno:"+response.errno+" error:"+response.error);
        System.out.println(response.getJson());
    }

}

