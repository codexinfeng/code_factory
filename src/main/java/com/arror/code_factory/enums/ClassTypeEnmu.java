package com.arror.code_factory.enums;

/**
 * @author zhangxianbin
 */
public enum ClassTypeEnmu {

	POJO("pojo"), DAO("dao"), XML("xml"), SERVICE("service"), SERVICEIMPL(
			"serviceImpl");

	private String type;

	private ClassTypeEnmu(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
