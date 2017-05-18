package com.arror.code_factory.model;

/**
 * @author zhangxianbin
 * 
 *         ±í×Ö¶ÎÊôĞÔ
 */
public class TableColumnDO {
	/**
	 * ×Ö¶ÎÃû
	 */
	private String columnName;
	/**
	 * ×Ö¶ÎÀàĞÍ
	 */
	private String dataType;

	/**
	 * ×Ö¶ÎÃèÊö
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
