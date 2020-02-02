package com.demo.loginportal.model.dqrule;

public class RuleRequestModel {

	private String objName;
	private String entityName;
	private String hFilename;
	private String rName;
	private String aFlag;
	
	public RuleRequestModel(String objName, String entityName, String hFilename, String rName, String aFlag) {
		super();
		this.objName = objName;
		this.entityName = entityName;
		this.hFilename = hFilename;
		this.rName = rName;
		this.aFlag = aFlag;
	}
	
	public String getObjName() {
		return objName;
	}
	public void setObjName(String objName) {
		this.objName = objName;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String gethFilename() {
		return hFilename;
	}
	public void sethFilename(String hFilename) {
		this.hFilename = hFilename;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getaFlag() {
		return aFlag;
	}
	public void setaFlag(String aFlag) {
		this.aFlag = aFlag;
	}
	
	@Override
	public String toString() {
		return "RuleRequestModel [objName=" + objName + ", entityName=" + entityName + ", hFilename=" + hFilename
				+ ", rName=" + rName + ", aFlag=" + aFlag + "]";
	}
}
