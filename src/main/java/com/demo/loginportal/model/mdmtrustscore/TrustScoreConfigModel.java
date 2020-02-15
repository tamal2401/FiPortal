package com.demo.loginportal.model.mdmtrustscore;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.demo.loginportal.enumss.ActiveFlagEnum;

@Entity
@Table(name = "mdm_trustscoreconfig")
public class TrustScoreConfigModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "rule_id", updatable = true, nullable = false)
	private Long ruleId;

	@Column(name = "table_nm", nullable = false)
	private String tableName;

	@Column(name = "attribute_nm_name", nullable = false)
	private String attributeName;

	@Column(name = "src_sys_cd", nullable = false)
	private String sourceSystem;

	@Column(name = "trust_score")
	private String trustScore;

	@Column(name = "active_flag")
	@Enumerated(EnumType.STRING)
	private ActiveFlagEnum activeFlag;
	
	@Column(name = "created_by")
	private String createdBy="";

	@Basic
	@Column(name = "creation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStamp;
	
	@Column(name = "updated_by")
	private String updatedBy="";
	
	@Basic
	@Column(name = "last_updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdated;

	public TrustScoreConfigModel() {
	}

	public Long getRuleId() {
		return ruleId;
	}

	public TrustScoreConfigModel setRuleId(Long ruleId) {
		this.ruleId = ruleId;
		return this;
	}

	public String getTableName() {
		return tableName;
	}

	public TrustScoreConfigModel setTableName(String tableName) {
		this.tableName = tableName;
		return this;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public TrustScoreConfigModel setAttributeName(String attributeName) {
		this.attributeName = attributeName;
		return this;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public TrustScoreConfigModel setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
		return this;
	}

	public String getTrustScore() {
		return trustScore;
	}

	public TrustScoreConfigModel setTrustScore(String trustScore) {
		this.trustScore = trustScore;
		return this;
	}

	public ActiveFlagEnum getActiveFlag() {
		return activeFlag;
	}

	public TrustScoreConfigModel setActiveFlag(ActiveFlagEnum activeFlag) {
		this.activeFlag = activeFlag;
		return this;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public TrustScoreConfigModel setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
		return this;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public TrustScoreConfigModel setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public TrustScoreConfigModel setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
		return this;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public TrustScoreConfigModel setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
		return this;
	}

	@Override
	public String toString() {
		return "TrustScoreConfigModel [ruleId=" + ruleId + ", tableName=" + tableName + ", attributeName="
				+ attributeName + ", sourceSystem=" + sourceSystem + ", trustScore=" + trustScore + ", activeFlag="
				+ activeFlag + ", createdBy=" + createdBy + ", timeStamp=" + timeStamp + ", updatedBy=" + updatedBy
				+ ", lastUpdated=" + lastUpdated + "]";
	}
	
	
	
	public static void main(String[] args) {
		String a = "123456";
		System.out.println(a.substring(a.length()-3));
	}
	
}
