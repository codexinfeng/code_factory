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
public class CreateDaoHandler {

	@Value("${dataBase}")
	private String dataBase;
	@Resource
	private DataBaseDAO dataBaseDao;

	// ��ȡ������
	public void createDao(String table, String baseFile) {
		String tableDesc = "";
		TableDO tableDo = dataBaseDao.getTable(dataBase, table);
		if (tableDo != null) {
			tableDesc = tableDo.getTableComment();
		}
		String className = ClassUtil.translateFirstUp(table);
		// ��ȡ����
		String content = getCreateDaoContent(table, tableDesc);
		// д�ļ�
		WriteUtil.write(baseFile, className, content, ClassTypeEnmu.DAO);
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
		// ���������
		// ע��
		content.append(ClassUtil.classNote(tableDesc));
		// ���ͷ��
		content.append("public interface ").append(className).append("DAO ")
				.append("{").append("\n\n");
		// ����id��ȡ����
		content.append(getById(pojoName));
		// ����id���ϻ�ȡ�������
		content.append(listByIds(pojoName));
		// �������
		content.append(save(pojoName, humpClass));
		// ����������
		content.append(saveList(pojoName));
		// β��
		content.append("\n").append("}");
		return content.toString();
	}

	private String getById(String pojoName) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClassUtil.methondNote()).append("	 *����id��ȡ����")
				.append(pojoName).append("\n").append("	 *")
				.append(" @param id").append("\n").append("	 */").append("\n");
		sb.append("	public ").append(pojoName)
				.append(" getById(Integer id);\n");
		return sb.toString();
	}

	private String listByIds(String pojoName) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClassUtil.methondNote()).append("	 *����id���ϻ�ȡ����list")
				.append(pojoName).append("\n").append("	 *")
				.append(" @param list").append("\n").append("	 */")
				.append("\n");
		sb.append("	public List<").append(pojoName).append(">")
				.append(" listByIds(List<Integer> ids);\n");
		return sb.toString();
	}

	private String save(String pojoName, String humpClass) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClassUtil.methondNote()).append("	 *����").append(pojoName)
				.append("\n").append("	 *").append(" @param ")
				.append(humpClass).append("Do\n").append("	 */").append("\n");
		sb.append("	public void ").append("save(").append(pojoName).append(" ")
				.append(humpClass).append("Do);\n");
		return sb.toString();
	}

	private String saveList(String pojoName) {
		StringBuilder sb = new StringBuilder();
		sb.append(ClassUtil.methondNote()).append("	 *������").append(pojoName)
				.append("\n").append("	 *").append(" @param list").append("\n")
				.append("	 */").append("\n");
		sb.append("	public void ").append("saveList(List<").append(pojoName)
				.append(">").append(" list);\n");
		return sb.toString();
	}
}
