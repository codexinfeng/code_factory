package com.arror.code_factory.handler;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arror.code_factory.dao.DataBaseDAO;
import com.arror.code_factory.enums.ClassTypeEnmu;
import com.arror.code_factory.model.TableDO;
import com.arror.code_factory.util.ClassUtil;
import com.arror.code_factory.util.WriteUtil;

/**
 * @author zhangxianbin
 */
@Service
public class CreateServiceImplHandler {

	@Value("${dataBase}")
	private String dataBase;
	@Resource
	private DataBaseDAO dataBaseDao;

	// ��ȡ������
	public void createServiceImpl(String table, String baseFile) {
		String tableDesc = "";
		TableDO tableDo = dataBaseDao.getTable(dataBase, table);
		if (tableDo != null) {
			tableDesc = tableDo.getTableComment();
		}
		String className = ClassUtil.translateFirstUp(table);
		// ��ȡ����
		String content = getCreateDaoContent(table, tableDesc);
		// д�ļ�
		WriteUtil
				.write(baseFile, className, content, ClassTypeEnmu.SERVICEIMPL);
	}

	/**
	 * ��ȡ����PO������
	 * 
	 * @param columnList
	 * @param table
	 * @param tableDesc
	 * @return
	 */
	private String getCreateDaoContent(String table, String tableDesc) {
		StringBuilder content = new StringBuilder();
		// �������
		String className = ClassUtil.translateFirstUp(table);
		String humpClass = ClassUtil.translateHump(table);
		String pojoName = className + "DO";
		humpClass = humpClass + "Dao";
		// ���������
		// import ������
		content.append(importProperties());
		// ע��
		content.append(ClassUtil.classNote(tableDesc));
		// ���ͷ��
		content.append("@Service\n").append("public class ").append(className)
				.append("ServiceImpl implements ").append(className)
				.append("Service").append(" {").append("\n\n");

		// ������
		content.append(importClass(table));
		// ����id��ȡ����
		content.append(getById(pojoName, humpClass));
		// ����id���ϻ�ȡ�������
		content.append(listByIds(pojoName, humpClass));
		// �������
		content.append(save(className, humpClass));
		// ����������
		content.append(saveList(pojoName, humpClass));
		// β��
		content.append("\n").append("}");
		return content.toString();
	}

	private String importClass(String table) {
		// �������
		String className = ClassUtil.translateFirstUp(table);
		String humpClass = ClassUtil.translateHump(table);
		className = className + "DAO";
		humpClass = humpClass + "Dao";
		StringBuilder sb = new StringBuilder();
		sb.append("\n\n	@Resource\n").append("	private ").append(className)
				.append(" ").append(humpClass).append(";\n");
		return sb.toString();
	}

	private String getById(String pojoName, String humpClass) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClassUtil.methondNote()).append("	 *����id��ȡ����")
				.append(pojoName).append("\n").append("	 */").append("\n")
				.append("	@Override\n");
		sb.append("	public ").append(pojoName)
				.append(" getById(Integer id) {\n\n").append("			return ")
				.append(humpClass).append(".getById(id);");
		sb.append("\n\n	}");
		return sb.toString();
	}

	private String listByIds(String pojoName, String humpClass) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClassUtil.methondNote()).append("	 *����id���ϻ�ȡ����list<")
				.append(pojoName).append(">\n").append("	 */").append("\n")
				.append("	@Override\n");
		sb.append("	public List<")
				.append(pojoName)
				.append(">")
				.append(" listByIds(List<Integer> ids) {\n")
				.append("		if (ids == null || ids.isEmpty()) {\n			return new ArrayList<>();\n		}")
				.append("\n		return ").append(humpClass)
				.append(".listByIds(ids);").append("\n	}");
		;
		return sb.toString();
	}

	private String save(String className, String humpClass) {
		StringBuilder sb = new StringBuilder();
		String pojoName = className + "DO";
		className = className + "Do";
		sb.append(ClassUtil.methondNote()).append("	 *����").append(pojoName)
				.append("\n").append("	 */").append("\n").append("	@Override\n");
		sb.append("	public void ").append("save(").append(pojoName).append(" ")
				.append(className).append(") {\n\n").append("			")
				.append(humpClass).append(".save(").append(className)
				.append(");\n\n	}");
		return sb.toString();
	}

	private String saveList(String pojoName, String humpClass) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClassUtil.methondNote()).append("	 *������").append(pojoName)
				.append("\n").append("	 */").append("\n").append("	@Override\n");
		sb.append("	public void ")
				.append("saveList(List<")
				.append(pojoName)
				.append(">")
				.append(" list) {\n")
				.append("		if (list == null || list.isEmpty()) {\n			return;\n		}\n		")
				.append(humpClass).append(".saveList(").append("list")
				.append(");\n	}");
		return sb.toString();
	}

	/**
	 * PoJo�������
	 * 
	 * @param columnList
	 * @return
	 */
	private String importProperties() {
		StringBuilder sb = new StringBuilder();
		sb.append("import java.util.ArrayList;\n");
		sb.append("import java.util.List;\n");
		sb.append("import javax.annotation.Resource;\n");
		sb.append("import org.springframework.stereotype.Service;\n");
		return sb.toString();
	}
}
