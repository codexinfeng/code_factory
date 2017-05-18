package com.arror.code_factory.model;

import java.util.Date;

/**
 *
 * ���л������ݱ�
 *
 */
public class AreaCityDO {
	/**
	 * ����id
	 */
	private Integer id;
	/**
	 * ����
	 */
	private String cityName;
	/**
	 * �����id
	 */
	private Integer parentId;
	/**
	 * ����ƴ��
	 */
	private String pinyin;
	/**
	 * ��׼������
	 */
	private String standardCityName;
	/**
	 * ��׼ƴ��
	 */
	private String standardPinYin;
	/**
	 * �Ƿ���Ч 1��Ч 0��Ч
	 */
	private Integer cancelFlag;
	/**
	 * ����ʱ��
	 */
	private Date createTime;
	/**
	 * ����ʱ��
	 */
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getStandardCityName() {
		return standardCityName;
	}

	public void setStandardCityName(String standardCityName) {
		this.standardCityName = standardCityName;
	}

	public String getStandardPinYin() {
		return standardPinYin;
	}

	public void setStandardPinYin(String standardPinYin) {
		this.standardPinYin = standardPinYin;
	}

	public Integer getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Integer cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}