package com.demo.loginportal.model.mdmtrustscore;

public class RequestTrustScoreModel {

	private String tableName;
	private String attrName;
	private String srcSystem;
	private String tScore;
	private String aFlag;
	
	public RequestTrustScoreModel(String tableName, String attrName, String srcSystem, String tScore, String aFlag) {
		super();
		this.tableName = tableName;
		this.attrName = attrName;
		this.srcSystem = srcSystem;
		this.tScore = tScore;
		this.aFlag = aFlag;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getSrcSystem() {
		return srcSystem;
	}
	public void setSrcSystem(String srcSystem) {
		this.srcSystem = srcSystem;
	}
	public String getScore() {
		return tScore;
	}
	public void settScore(String tScore) {
		this.tScore = tScore;
	}
	public String getaFlag() {
		return aFlag;
	}
	public void setaFlag(String aFlag) {
		this.aFlag = aFlag;
	}
	@Override
	public String toString() {
		return "RequestTrustScoreModel [tableName=" + tableName + ", attrName=" + attrName + ", srcSystem=" + srcSystem
				+ ", tScore=" + tScore + ", aFlag=" + aFlag + "]";
	}
}
