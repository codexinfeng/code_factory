package com.arror.code_factory.model;

import java.util.Date;

/**
 *
 * 城市基础数据表
 *
 */
public class AreaCityDO {
	/**
	 * 城市id
	 */
	private Integer id;
	/**
	 * 名字
	 */
	private String cityName;
	/**
	 * 父类的id
	 */
	private Integer parentId;
	/**
	 * 城市拼音
	 */
	private String pinyin;
	/**
	 * 标准城市名
	 */
	private String standardCityName;
	/**
	 * 标准拼音
	 */
	private String standardPinYin;
	/**
	 * 是否有效 1有效 0无效
	 */
	private Integer cancelFlag;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
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