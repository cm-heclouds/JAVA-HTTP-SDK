package com.cmcciot.onenet.api;

import cmcc.iot.onenet.javasdk.api.dtu.DeleteDtuParser;
import cmcc.iot.onenet.javasdk.response.BasicResponse;
import org.junit.Test;


public class ApiTest {

    @Test
    public void testDeleteDtuParserApi() {
        String key = "m4EubNp9WCeAxjFu4lVw=kn2idE=";
        Integer id = 385;
 

        /**
    	 * TCP透传查询
    	 * @param name： 名字,精确匹配名字（可选）,String
    	 * @param key:masterkey 或者 该设备的设备apikey
    	 */
        DeleteDtuParser api = new DeleteDtuParser(id, key);
        BasicResponse<Void> response = api.executeApi();
        System.out.println("errno:"+response.errno+" error:"+response.error);
    }

}

