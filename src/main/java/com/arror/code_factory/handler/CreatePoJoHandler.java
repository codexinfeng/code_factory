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
public class CreatePoJoHandler {

	@Value("${dataBase}")
	private String dataBase;
	@Resource
	private DataBaseDAO dataBaseDao;

	// 获取表属性
	public void createPojo(String table, String baseFile) {
		// 获取表的列属性
		List<TableColumnDO> columnList = dataBaseDao.listTableColumns(dataBase,
				table);
		String tableDesc = "";
		TableDO tableDo = dataBaseDao.getTable(dataBase, table);
		if (tableDo != null) {
			tableDesc = tableDo.getTableComment();
		}
		// 类的名字
		String className = ClassUtil.translateFirstUp(table);
		// 获取内容
		String content = getCreatePojoContent(columnList, className, tableDesc);
		// 写文件
		WriteUtil.write(baseFile, className, content, ClassTypeEnmu.POJO);
	}

	/**
	 * 获取生成PO类内容
	 * 
	 * @param columnList
	 * @param table
	 * @param tableDesc
	 * @return
	 */
	private String getCreatePojoContent(List<TableColumnDO> columnList,
			String className, String tableDesc) {
		StringBuilder content = new StringBuilder();
		// 类具体内容
		// import 属性
		content.append(importPoJoProperties(columnList));
		// 注释
		content.append(ClassUtil.classNote(tableDesc));
		// 类
		content.append("public class ").append(className).append("DO ")
				.append("{").append("\n");

		// 属性定义
		for (TableColumnDO columnDo : columnList) {
			content.append(getDefineProperty(columnDo));
		}
		// get set定义
		for (TableColumnDO columnDo : columnList) {
			content.append(getDefineGetAndSet(columnDo));
		}
		content.append("\n").append("}");
		return content.toString();
	}

	/**
	 * PoJo添加引用
	 * 
	 * @param columnList
	 * @return
	 */
	private String importPoJoProperties(List<TableColumnDO> columnList) {
		StringBuilder sb = new StringBuilder();
		if (columnList == null || columnList.isEmpty()) {
			return "";
		}
		// import属性
		Set<String> colunSet = new HashSet<>();
		for (TableColumnDO column : columnList) {
			colunSet.add(TableUtil.typeMap.get(column.getDataType()));
		}
		if (colunSet.contains("BigDecimal")) {
			sb.append("import java.math.BigDecimal;");
		}
		String tempImport = sb.toString();
		if (colunSet.contains("Date")) {
			if (!"".equals(tempImport)) {
				sb.append("\n");
			} else {
				sb.append("import java.util.Date;");
			}

		}
		return sb.toString();
	}

	/**
	 * 定义属性
	 * 
	 * @param columnDo
	 * @return
	 */
	private String getDefineProperty(TableColumnDO columnDo) {
		StringBuilder sb = new StringBuilder();
		String comment = columnDo.getColumnComment();
		if (!(comment == null || "".equals(comment))) {
			sb.append("	/**").append("\n").append("	 *").append(comment)
					.append("\n").append("	 */").append("\n");
		}
		String dataType = columnDo.getDataType();
		if (!(dataType == null || "".equals(dataType))) {
			sb.append("	private ").append(TableUtil.typeMap.get(dataType))
					.append(" ");
		}
		sb.append(ClassUtil.translateHump(columnDo.getColumnName()))
				.append(";").append("\n");
		return sb.toString();
	}

	/**
	 * set get方法
	 * 
	 * @param columnDo
	 * @return
	 */
	private String getDefineGetAndSet(TableColumnDO columnDo) {
		StringBuilder sb = new StringBuilder();
		String columnName = columnDo.getColumnName();
		// 驼峰命名样式
		String transHump = ClassUtil.translateHump(columnName);
		String dataType = columnDo.getDataType();
		sb.append("\n	public ").append(TableUtil.typeMap.get(dataType))
				.append(" ");
		sb.append("get").append(ClassUtil.translateFirstUp(columnName))
				.append("() {").append("\n").append("			").append("return ")
				.append(transHump).append(";").append("\n").append("		")
				.append("}").append("\n\n").append("		public void set")
				.append(ClassUtil.translateFirstUp(columnName)).append("(")
				.append(TableUtil.typeMap.get(dataType)).append(" ")
				.append(transHump).append(") {\n			this.").append(transHump)
				.append(" = ").append(transHump).append(";").append("\n		}");
		return sb.toString();
	}

}
