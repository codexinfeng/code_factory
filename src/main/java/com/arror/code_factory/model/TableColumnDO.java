package com.arror.code_factory.model;

/**
 * @author zhangxianbin
 * 
 *         ���ֶ�����
 */
public class TableColumnDO {
	/**
	 * �ֶ���
	 */
	private String columnName;
	/**
	 * �ֶ�����
	 */
	private String dataType;

	/**
	 * �ֶ�����
	 */
	private String columnComment;

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

}
