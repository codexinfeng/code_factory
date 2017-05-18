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
	 * ��ȡ���������еı�
	 * 
	 * @return
	 */
	List<String> listTables();

	/**
	 * ��ȡdatabase�������Ϊtable������
	 * 
	 * @param dataBase
	 * @param table
	 * @return
	 */
	List<TableColumnDO> listTableColumns(@Param("dataBase") String dataBase,
			@Param("tableName") String tableName);

	/**
	 * ��ȡ��ע��
	 * 
	 * @param dataBase
	 * @param tableName
	 * @return
	 */
	TableDO getTable(@Param("dataBase") String dataBase,
			@Param("tableName") String tableName);

}
