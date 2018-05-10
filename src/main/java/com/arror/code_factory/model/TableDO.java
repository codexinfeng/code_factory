package com.arror.code_factory.model;

/**
 * @author zhangxianbin
 * 
 *         ±íÊôÐÔ
 */
public class TableDO {

	/**
	 * ±íÃû
	 */
	private String tableName;
	/**
	 * ±í×¢ÊÍ
	 */
	private String tableComment;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableComment() {
		return tableComment;
	}

	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}

}