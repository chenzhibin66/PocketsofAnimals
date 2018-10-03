package cn.bmob.sdkdemo.bean;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;

public class AdMasterEntity extends BmobObject {

	private String Name = "";

	private String Master = "";

	private boolean ShowInTop = true;

	public boolean isShowInTop() {
		return ShowInTop;
	}

	public void setShowInTop(boolean showInTop) {
		ShowInTop = showInTop;
	}

	private String MasterType = "";

	private List<AdMasterItemEntity> Fields = new ArrayList<AdMasterItemEntity>();

	private boolean Enable = true;

	public void setEnable(boolean enable) {
		Enable = enable;
	}

	public boolean isEnable() {
		return Enable;
	}

	public List<AdMasterItemEntity> getFields() {
		return Fields;
	}

	public String getMaster() {
		return Master;
	}

	public String getMasterType() {
		return MasterType;
	}

	public String getName() {
		return Name;
	}

	public void setFields(List<AdMasterItemEntity> fields) {
		Fields = fields;
	}

	public void setMaster(String master) {
		Master = master;
	}

	public void setMasterType(String masterType) {
		MasterType = masterType;
	}

	public void setName(String name) {
		Name = name;
	}
}