package com.arror.code_factory.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.arror.code_factory.model.TableColumnDO;
import com.arror.code_factory.model.TableDO;

/**
 * @author zhangxianbin
 */
public interface DataBaseDAO {

	/**
	 * 获取库里面所有的表
	 * 
	 * @return
	 */
	List<String> listTables();

	/**
	 * 获取database库里表名为table的属性
	 * 
	 * @param dataBase
	 * @param table
	 * @return
	 */
	List<TableColumnDO> listTableColumns(@Param("dataBase") String dataBase,
			@Param("tableName") String tableName);

	/**
	 * 获取表注释
	 * 
	 * @param dataBase
	 * @param tableName
	 * @return
	 */
	TableDO getTable(@Param("dataBase") String dataBase,
			@Param("tableName") String tableName);

}