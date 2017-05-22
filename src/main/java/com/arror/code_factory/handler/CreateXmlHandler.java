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
	@Resource
	private DataBaseDAO dataBaseDao;

	// ��ȡ������
	public void createXml(String table, String baseFile) {
		// ��ȡ���������
		List<TableColumnDO> columnList = dataBaseDao.listTableColumns(dataBase,
				table);
		// // �������
		String content = getCreateXmlContent(table, columnList);
		// �������
		String className = ClassUtil.translateFirstUp(table);
		// д�ļ�
		WriteUtil.write(baseFile, className, content, ClassTypeEnmu.XML);
	}

	/**
	 * ��ȡ����PO������
	 * 
	 * @param columnList
	 * @param table
	 * @param tableDesc
	 * @return
	 */
	private String getCreateXmlContent(String table,
			List<TableColumnDO> columnList) {
		StringBuilder content = new StringBuilder();
		// ���������
		// xml�淶
		content.append(xmlRule());
		// ����
		content.append(save(table, columnList));
		// ������
		content.append(saveList(table, columnList));
		// ��ȡ����
		content.append(getById(table, columnList));
		// ��ȡ���
		content.append(listByIds(table, columnList));
		content.append("\n\n</mapper>");
		return content.toString();
	}

	/**
	 * PoJo�������
	 * 
	 * @param columnList
	 * @return
	 */
	private String xmlRule() {
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n    <!DOCTYPE mapper\n        PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n        \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n<mapper namespace=\"\">\n\n");
		return sb.toString();
	}

	// ���浥��
	private String save(String table, List<TableColumnDO> columnList) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"	<insert id=\"save\" parameterType=\"\" >\n		insert into ")
				.append(table).append("( ");
		// ���Զ���
		for (int i = 0; i < columnList.size(); i++) {
			TableColumnDO columnDo = columnList.get(i);
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
		sb.append("}");
		sb.append("\n	</insert>");

		return sb.toString();
	}

	// ������
	private String saveList(String table, List<TableColumnDO> columnList) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"\n	<insert id=\"saveList\" parameterType=\"java.util.List\" >\n		insert into ")
				.append(table).append("( ");
		// ���Զ���
		for (int i = 0; i < columnList.size(); i++) {
			TableColumnDO columnDo = columnList.get(i);
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

	// ���浥��
	private String getById(String table, List<TableColumnDO> columnList) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n	<select id=\"getById\" resultType=\"\" >\n		select ");
		for (int i = 0; i < columnList.size(); i++) {
			TableColumnDO columnDo = columnList.get(i);
			String columnName = columnDo.getColumnName();
			sb.append(columnName);
			if (i != columnList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append(" from ").append(table).append(" where id=#{id}");
		sb.append("\n	<select>");

		return sb.toString();
	}

	// ���浥��
	private String listByIds(String table, List<TableColumnDO> columnList) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n	<select id=\"listByIds\" resultType=\"\" >\n		select ");
		for (int i = 0; i < columnList.size(); i++) {
			TableColumnDO columnDo = columnList.get(i);
			String columnName = columnDo.getColumnName();
			sb.append(columnName);
			if (i != columnList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append(" from ").append(table).append(" where id in");
		sb.append("\n		<foreach collection=\"list\" item=\"item\" separator=\",\" open=\"(\" close=\")\">\n");
		sb.append("				#{item}\n");
		sb.append("		</foreach>");
		sb.append("\n	</select>");
		return sb.toString();
	}
}
