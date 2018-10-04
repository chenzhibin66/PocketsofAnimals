package cn.bmob.sdkdemo.bean;

import cn.bmob.v3.BmobObject;

public class AdMasterItemEntity extends BmobObject {


	private String Id = "";

	private String FieldType = "";

	private String DefaultValue = "";

	private String  Name="";
	
	public String getName() {
		return Name;
	}
	
	public void setName(String name) {
		Name = name;
	}

	public String getDefaultValue() {
		return DefaultValue;
	}

	public String getFieldType() {
		return FieldType;
	}

	public String getId() {
		return Id;
	}
	
}

