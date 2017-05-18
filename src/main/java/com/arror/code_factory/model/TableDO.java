package com.arror.code_factory.model;

/**
 * @author zhangxianbin
 * 
 *         表属性
 */
public class TableDO {

	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 表注释
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
