# JAVA-HTTP-SDK
OneNET JAVA-HTTP-SDK
本项目是 中移物联网公司 为方便Java开发者接入 OneNET 平台而开发的SDK。关于OneNET请进入[OneNet](http://open.iot.10086.cn)
主站了解详情。如果要了解OneNet API请进入 [文档中心](http://open.iot.10086.cn/doc) 参考API文档查看。
本程序是中移物联的OneNET公众版Java版本的SDK(完整版本)。Java SDK版本为1.8.0_92，maven版本为Apache Maven 3.3.9。
本SDK包含了OneNET平台的设备、数据流、数据点、触发器和api key的增删改查，以及二进制数据存取、命令执行、Mqtt相关功能。

本程序一共包含SDK代码和其单元测试（cmcc.iot.onenet.javasdk.ApiTest.java）。SDK使用案例请直接参照单元测试即可。

##如何使用
###示例
以添加设备为例
```Java  
    public void testAdddevices() {
		String key = "9ylHzkz25nre41i=SuJR=F=k5kU=";
		String title = "devices_test";  
		String desc = "devices_test"; 
		String protocol = "HTTP"; 
		Location location =new Location(106,29,370);//设备位置{"纬度", "精度", "高度"}（可选）
		List<String> tags = new ArrayList<String>();  
		tags.add("china");
		tags.add("mobile");
		String auth_info = "201503041a5829151";   
		/****
		 * 设备新增
		 * 参数顺序与构造函数顺序一致
		 * @param title： 设备名，String
		 * @param protocol： 接入协议（可选，默认为HTTP）,String
		 * @param desc： 设备描述（可选）,String
		 * @param tags： 设备标签（可选，可为一个或多个）,List<String>
		 * @param location： 设备位置{"纬度", "精度", "高度"}（可选）,Location类型
		 * @param isPrivate： 设备私密性,Boolean类型
		 * @param authInfo： 设备唯一编号 ,Object
		 * @param other： 其他信息,Map<String, Object>
		 * @param interval： MODBUS设备 下发命令周期,Integer类型
		 * @param key： masterkey,String
		 */
		AddDevicesApi api = new AddDevicesApi(title, protocol, desc, tags, location, null, auth_info, null, null, key);
		api.build();
		BasicResponse<NewDeviceResponse> response = api.executeApi();
		System.out.println("errno:"+response.errno+" error:"+response.error);
		System.out.println(response.getJson());
		
	}
   ```  
   ###注意事项：

   更多示例请参考ApiTest中的代码，运行前请确认resources中的config.properties请求的http地址正确
