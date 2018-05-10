package com.arror.code_factory.handler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arror.code_factory.dao.DataBaseDAO;
import com.arror.code_factory.enums.ClassTypeEnmu;
import com.arror.code_factory.model.TableColumnDO;
import com.arror.code_factory.model.TableDO;
import com.arror.code_factory.util.ClassUtil;
import com.arror.code_factory.util.TableUtil;
import com.arror.code_factory.util.WriteUtil;

/**
 * @author zhangxianbin
 */
@Service
public class CreateDaoHandler {

	@Value("${dataBase}")
	private String dataBase;
	@Value("${basePackage}")
	private String basePackage;
	@Resource
	private DataBaseDAO dataBaseDao;

	// 获取表属性
	public void createDao(String table, String baseFile) {
		String tableDesc = "";
		TableDO tableDo = dataBaseDao.getTable(dataBase, table);
		if (tableDo != null) {
			tableDesc = tableDo.getTableComment();
		}
		String className = ClassUtil.translateFirstUp(table);
		// 获取内容
		String content = getCreateDaoContent(table, tableDesc);
		// 写文件
		WriteUtil.write(baseFile, className, content, ClassTypeEnmu.DAO);
	}

	/**
	 * 获取生成PO类内容
	 * 
	 * @param columnList
	 * @param table
	 * @param tableDesc
	 * @return
	 */
	private String getCreateDaoContent(String table, String tableDesc) {
		StringBuilder content = new StringBuilder();
		// 类的名字
		String className = ClassUtil.translateFirstUp(table);
		String humpClass = ClassUtil.translateHump(table);
		String pojoName = className + "DO";
		// 类具体内容
		// basePage内容
		content.append("package ").append(basePackage).append(".dao;")
				.append("\n");
		content.append(importPoJoProperties(className));
		// 注释
		content.append(ClassUtil.classNote(tableDesc));
		// 类的头部
		content.append("public interface ").append(className).append("DAO ")
				.append("{").append("\n\n");
		// 根据id获取对象
		content.append(getById(pojoName));
		// 根据id集合获取多个对象
		content.append(listByIds(pojoName));
		// 保存对象
		content.append(save(pojoName, humpClass));
		// 保存多个对象
		content.append(saveList(pojoName));
		// 尾部
		content.append("\n").append("}");
		return content.toString();
	}

	private String getById(String pojoName) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClassUtil.methondNote()).append("	 *根据id获取对象")
				.append(pojoName).append("\n").append("	 *")
				.append(" @param id").append("\n").append("	 */").append("\n");
		sb.append("	public ").append(pojoName)
				.append(" getById(Integer id);\n");
		return sb.toString();
	}

	private String listByIds(String pojoName) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClassUtil.methondNote()).append("	 *根据id集合获取对象list")
				.append(pojoName).append("\n").append("	 *")
				.append(" @param list").append("\n").append("	 */")
				.append("\n");
		sb.append("	public List<").append(pojoName).append(">")
				.append(" listByIds(List<Integer> ids);\n");
		return sb.toString();
	}

	private String save(String pojoName, String humpClass) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClassUtil.methondNote()).append("	 *保存").append(pojoName)
				.append("\n").append("	 *").append(" @param ")
				.append(humpClass).append("Do\n").append("	 */").append("\n");
		sb.append("	public int ").append("save(").append(pojoName).append(" ")
				.append(humpClass).append("Do);\n");
		return sb.toString();
	}

	private String saveList(String pojoName) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClassUtil.methondNote()).append("	 *保存多个").append(pojoName)
				.append("\n").append("	 *").append(" @param list").append("\n")
				.append("	 */").append("\n");
		sb.append("	public void ").append("saveList(List<").append(pojoName)
				.append(">").append(" list);\n");
		return sb.toString();
	}

	/**
	 * PoJo添加引用
	 * 
	 * @param columnList
	 * @return
	 */
	private String importPoJoProperties(String className) {
		StringBuilder sb = new StringBuilder();
		sb.append("import java.util.List;").append("\n");
		sb.append("import ").append(basePackage).append(".domain.")
				.append(className + "DO;").append("\n");
		return sb.toString();
	}
}