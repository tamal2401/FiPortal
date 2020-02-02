package com.demo.loginportal.model.dqrule;

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
@Table(name = "mdm_dqrule")
public class DqRuleMOdel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "rule_id", updatable = true, nullable = false)
	private Long ruleId;

	@Column(name = "obj_name", nullable = false)
	private String objName;

	@Column(name = "entity_name", nullable = false)
	private String entityName;

	@Column(name = "hive_fld_nm", nullable = false)
	private String hiveFilename;

	@Column(name = "rule_name")
	private String ruleName;

	@Column(name = "active_flag")
	@Enumerated(EnumType.STRING)
	private ActiveFlagEnum activeFlag;

	@Basic
	@Column(name = "creation_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStamp;

	public Long getRuleId() {
		return ruleId;
	}

	public DqRuleMOdel setRuleId(Long ruleId) {
		this.ruleId = ruleId;
		return this;
	}

	public String getObjName() {
		return objName;
	}

	public DqRuleMOdel setObjName(String objName) {
		this.objName = objName;
		return this;
	}

	public String getEntityName() {
		return entityName;
	}

	public DqRuleMOdel setEntityName(String entityName) {
		this.entityName = entityName;
		return this;
	}

	public String getHiveFilename() {
		return hiveFilename;
	}

	public DqRuleMOdel setHiveFilename(String hiveFilename) {
		this.hiveFilename = hiveFilename;
		return this;
	}

	public String getRuleName() {
		return ruleName;
	}

	public DqRuleMOdel setRuleName(String ruleName) {
		this.ruleName = ruleName;
		return this;
	}

	public ActiveFlagEnum getActiveFlag() {
		return activeFlag;
	}

	public DqRuleMOdel setActiveFlag(ActiveFlagEnum activeFlag) {
		this.activeFlag = activeFlag;
		return this;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public DqRuleMOdel setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}

	@Override
	public String toString() {
		return "DqRuleMOdel [ruleId=" + ruleId + ", objName=" + objName + ", entityName=" + entityName
				+ ", hiveFilename=" + hiveFilename + ", ruleName=" + ruleName + ", activeFlag=" + activeFlag
				+ ", timeStamp=" + timeStamp + "]";
	}
}