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

	// ��ȡ������
	public void createPojo(String table, String baseFile) {
		// ��ȡ���������
		List<TableColumnDO> columnList = dataBaseDao.listTableColumns(dataBase,
				table);
		String tableDesc = "";
		TableDO tableDo = dataBaseDao.getTable(dataBase, table);
		if (tableDo != null) {
			tableDesc = tableDo.getTableComment();
		}
		// �������
		String className = ClassUtil.translateFirstUp(table);
		// ��ȡ����
		String content = getCreatePojoContent(columnList, className, tableDesc);
		// д�ļ�
		WriteUtil.write(baseFile, className, content, ClassTypeEnmu.POJO);
	}

	/**
	 * ��ȡ����PO������
	 * 
	 * @param columnList
	 * @param table
	 * @param tableDesc
	 * @return
	 */
	private String getCreatePojoContent(List<TableColumnDO> columnList,
			String className, String tableDesc) {
		StringBuilder content = new StringBuilder();
		// ���������
		// import ����
		content.append(importPoJoProperties(columnList));
		// ע��
		content.append(ClassUtil.classNote(tableDesc));
		// ��
		content.append("public class ").append(className).append("DO ")
				.append("{").append("\n");

		// ���Զ���
		for (TableColumnDO columnDo : columnList) {
			content.append(getDefineProperty(columnDo));
		}
		// get set����
		for (TableColumnDO columnDo : columnList) {
			content.append(getDefineGetAndSet(columnDo));
		}
		content.append("\n").append("}");
		return content.toString();
	}

	/**
	 * PoJo�������
	 * 
	 * @param columnList
	 * @return
	 */
	private String importPoJoProperties(List<TableColumnDO> columnList) {
		StringBuilder sb = new StringBuilder();
		if (columnList == null || columnList.isEmpty()) {
			return "";
		}
		// import����
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
	 * ��������
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
	 * set get����
	 * 
	 * @param columnDo
	 * @return
	 */
	private String getDefineGetAndSet(TableColumnDO columnDo) {
		StringBuilder sb = new StringBuilder();
		String columnName = columnDo.getColumnName();
		// �շ�������ʽ
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
