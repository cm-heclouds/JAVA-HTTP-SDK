package cmcc.iot.onenet.javasdk.response.key;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class KeyList {
	@JsonProperty("total_count")
	public int totalcount;
	@JsonProperty("per_page")
	public int perpage;
	@JsonProperty("page")
	public int page;
	@JsonProperty("keys")
	public ArrayList<KeysItem> keys;
	
	
	@JsonCreator
	public KeyList(@JsonProperty("total_count")int totalcount,@JsonProperty("per_page") int perpage, @JsonProperty("page")int page, @JsonProperty("keys")ArrayList<KeysItem> keys) {
		this.totalcount = totalcount;
		this.perpage = perpage;
		this.page = page;
		this.keys = keys;
	}



	public static class KeysItem{
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		@JsonProperty("create_time")
		public Date createTime;
		@JsonProperty("title")
		public String title;
		@JsonProperty("key")
		public String key;
		@JsonProperty("permissions")
		public ArrayList<PermissionsItem> permissions;
		
		public Date getCreateTime() {
			return createTime;
		}


		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public String getKey() {
			return key;
		}


		public void setKey(String key) {
			this.key = key;
		}


		public ArrayList<PermissionsItem> getPermissions() {
			return permissions;
		}


		public void setPermissions(ArrayList<PermissionsItem> permissions) {
			this.permissions = permissions;
		}


		@JsonCreator
		public KeysItem( @JsonProperty("create_time")Date createTime, @JsonProperty("title")String title,@JsonProperty("key") String key,@JsonProperty("permissions") ArrayList<PermissionsItem> permissions) {
			this.createTime = createTime;
			this.title = title;
			this.key = key;
			this.permissions = permissions;
		}


		public static class PermissionsItem{
			@JsonProperty("resources")
			public List<DevicesItem>resources;
			public List<DevicesItem> getResources() {
				return resources;
			}


			public void setResources(List<DevicesItem> resources) {
				this.resources = resources;
			}


			public ArrayList<String> getAccessMethods() {
				return accessMethods;
			}


			public void setAccessMethods(ArrayList<String> accessMethods) {
				this.accessMethods = accessMethods;
			}


			@JsonProperty("access_methods")
			public ArrayList<String> accessMethods;
			@JsonCreator
			public PermissionsItem(@JsonProperty("permissions")List<DevicesItem> resources,@JsonProperty("access_methods")ArrayList<String> accessMethods) {
				this.resources = resources;
				this.accessMethods=accessMethods;
			}


			public static class DevicesItem{
				@JsonProperty("dev_id")
				public String devid;

				@JsonCreator
				public DevicesItem(@JsonProperty("dev_id")String devid) {
					this.devid = devid;
				}
				
				
			}
		}
	}



	public int getTotalcount() {
		return totalcount;
	}



	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}



	public int getPerpage() {
		return perpage;
	}



	public void setPerpage(int perpage) {
		this.perpage = perpage;
	}



	public int getPage() {
		return page;
	}



	public void setPage(int page) {
		this.page = page;
	}



	public ArrayList<KeysItem> getKeys() {
		return keys;
	}



	public void setKeys(ArrayList<KeysItem> keys) {
		this.keys = keys;
	}
}
