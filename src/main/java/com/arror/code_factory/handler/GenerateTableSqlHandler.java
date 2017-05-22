package com.arror.code_factory.handler;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arror.code_factory.dao.DataBaseDAO;

/**
 * @author zhangxianbin
 */
@Service
public class GenerateTableSqlHandler {
	@Resource
	private DataBaseDAO dataBaseDao;
	@Resource
	private CreatePoJoHandler createPoJoHandler;
	@Resource
	private CreateDaoHandler createDaoHandler;
	@Resource
	private CreateServiceHandler createServiceHandler;
	@Resource
	private CreateServiceImplHandler createServiceImplHandler;
	@Resource
	private CreateXmlHandler createXmlHandler;

	public void handler() {
		String baseFile = System.getProperty("user.dir") + File.separator
				+ "code" + File.separator;
		List<String> tableList = dataBaseDao.listTables();
		String table = "area_city";
		// for (String table : tableList) {

		createPoJoHandler.createPojo(table, baseFile);
		System.out.println("PO 类初始化完毕");
		// DAO类创建
		createDaoHandler.createDao(table, baseFile);
		System.out.println("DAO 类初始化完毕");
		// SERVICE类创建
		createServiceHandler.createService(table, baseFile);
		System.out.println("SERVICE 类初始化完毕");
		// SERVICE类创建
		createServiceImplHandler.createServiceImpl(table, baseFile);
		System.out.println("SERVICEIMPL 类初始化完毕");
		// SERVICE类创建
		createXmlHandler.createXml(table, baseFile);
		System.out.println("XML 初始化完毕");
		// }

	}

}
