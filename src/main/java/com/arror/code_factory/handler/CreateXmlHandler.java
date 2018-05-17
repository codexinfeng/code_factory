package com.arror.code_factory.handler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arror.code_factory.dao.DataBaseDAO;
import com.arror.code_factory.enums.ClassTypeEnmu;
import com.arror.code_factory.model.TableColumnDO;
import com.arror.code_factory.util.ClassUtil;
import com.arror.code_factory.util.WriteUtil;

/**
 * @author zhangxianbin
 */
@Service
public class CreateXmlHandler {

	@Value("${dataBase}")
	private String dataBase;
	@Value("${basePackage}")
	private String basePackage;
	@Resource
	private DataBaseDAO dataBaseDao;

	// 获取表属性
	public void createXml(String table, String baseFile) {
		// 获取表的列属性
		List<TableColumnDO> columnList = dataBaseDao.listTableColumns(dataBase,
				table);
		// 类的名字
		String className = ClassUtil.translateFirstUp(table);
		// // 类的名字
		String content = getCreateXmlContent(table, columnList);

		// 写文件
		WriteUtil.write(baseFile, className, content, ClassTypeEnmu.XML);
	}

	/**
	 * 获取生成PO类内容
	 * 
	 * @param columnList
	 * @param table
	 * @param tableDesc
	 * @return
	 */
	private String getCreateXmlContent(String table,
			List<TableColumnDO> columnList) {
		StringBuilder content = new StringBuilder();
		// 类具体内容
		// xml规范
		content.append(xmlRule());
		String className = ClassUtil.translateFirstUp(table);
		content.append(mapper(className));
		// sql的定义
		String columns = columns(columnList);
		content.append(columns);
		// 保存
		content.append(save(table, columnList, className));
		// 保存多个
		content.append(saveList(table, columnList));
		// 获取单个
		content.append(getById(table, className));
		// 获取多个
		content.append(listByIds(table, className));
		content.append("\n\n</mapper>");
		return content.toString();
	}

	/**
	 * PoJo添加引用
	 * 
	 * @param columnList
	 * @return
	 */
	private String xmlRule() {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n    <!DOCTYPE mapper\n        PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n        \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
		return sb.toString();
	}

	private String mapper(String className) {
		StringBuilder sb = new StringBuilder();
		sb.append("<mapper namespace=\"").append(basePackage).append(".dao.")
				.append(className).append("DAO\">\n\n");
		return sb.toString();

	}

	private String columns(List<TableColumnDO> columnList) {
		StringBuilder sb = new StringBuilder();
		sb.append("	<sql id=\"columns\">");
		for (int i = 0; i < columnList.size(); i++) {
			TableColumnDO columnDo = columnList.get(i);
			sb.append(columnDo.getColumnName()).append(" as ");
			sb.append(ClassUtil.translateHump(columnDo.getColumnName()));
			if (i != columnList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("</sql>\n");
		return sb.toString();
	}

	// 保存单个
	private String save(String table, List<TableColumnDO> columnList,
			String className) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"	<insert id=\"save\" useGeneratedKeys=\"true\" keyProperty=\"id\" parameterType=\"")
				.append(basePackage).append(".domain.").append(className)
				.append("DO").append("\" >\n		insert into ").append(table)
				.append("( ");
		// 属性定义
		for (int i = 0; i < columnList.size(); i++) {
			TableColumnDO columnDo = columnList.get(i);
			if (columnDo.getColumnName().equals("id")) {
				continue;
			}
			sb.append(columnDo.getColumnName());
			if (i != columnList.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append(" ) values (");
		List<String> dateList = new ArrayList<>();
		dateList.add("create_time");
		dateList.add("update_time");
		for (int i = 0; i < columnList.size(); i++) {
			TableColumnDO columnDo = columnList.get(i);
			if (columnDo.getColumnName().equals("id")) {
				continue;
			}
			String columnName = columnDo.getColumnName();
			if (dateList.contains(columnName)) {
				sb.append("now()");
				if (i != columnList.size() - 1) {
					sb.append(",");
				}
			} else {
				sb.append("#{").append(
						ClassUtil.translateHump(columnDo.getColumnName()));
				if (i != columnList.size() - 1) {
					sb.append("},");
				}
			}
		}
		sb.append(")");
		sb.append("\n	</insert>");

		return sb.toString();
	}

	// 保存多个
	private String saveList(String table, List<TableColumnDO> columnList) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"\n	<insert id=\"saveList\" parameterType=\"java.util.List\" >\n		insert into ")
				.append(table).append("( ");
		// 属性定义
		for (int i = 0; i < columnList.size(); i++) {
			TableColumnDO columnDo = columnList.get(i);
			if (columnDo.getColumnName().equals("id")) {
				continue;
			}
			sb.append(columnDo.getColumnName());
			if (i != columnList.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append(" ) values\n");
		sb.append("		<foreach collection=\"list\" item=\"item\" separator=\",\">\n");
		sb.append("			(");

		List<String> dateList = new ArrayList<>();
		dateList.add("create_time");
		dateList.add("update_time");
		for (int i = 0; i < columnList.size(); i++) {
			TableColumnDO columnDo = columnList.get(i);
			if (columnDo.getColumnName().equals("id")) {
				continue;
			}
			String columnName = columnDo.getColumnName();
			if (dateList.contains(columnName)) {
				sb.append("now()");
				if (i != columnList.size() - 1) {
					sb.append(",");
				}
			} else {
				sb.append("#{").append(
						ClassUtil.translateHump(columnDo.getColumnName()));
				if (i != columnList.size() - 1) {
					sb.append("},");
				}
			}
		}
		sb.append(")\n");
		sb.append("		</foreach>");
		sb.append("\n	</insert>");

		return sb.toString();
	}

	// 获取单个
	private String getById(String table, String className) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n	<select id=\"getById\" resultType=\"")
				.append(basePackage).append(".domain.").append(className)
				.append("DO").append("\" >\n		select ")
				.append("<include refid=\"columns\"/>");
		sb.append(" from ").append(table).append(" where id=#{id}");
		sb.append("\n	</select>");

		return sb.toString();
	}

	// 获取多个
	private String listByIds(String table, String className) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n	<select id=\"listByIds\" resultType=\"")
				.append(basePackage).append(".domain.").append(className)
				.append("DO").append("\" >\n		select ")
				.append("<include refid=\"columns\"/>");

		sb.append(" from ").append(table).append(" where id in");
		sb.append("\n		<foreach collection=\"list\" item=\"item\" separator=\",\" open=\"(\" close=\")\">\n");
		sb.append("				#{item}\n");
		sb.append("		</foreach>");
		sb.append("\n	</select>");
		return sb.toString();
	}
}