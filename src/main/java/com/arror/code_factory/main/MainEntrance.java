package com.arror.code_factory.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arror.code_factory.handler.GenerateTableSqlHandler;

/**
 * @author zhangxianbin
 */
public class MainEntrance {

	public static void main(String[] args) {

		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath:applicationContext.xml");
		GenerateTableSqlHandler handler = ac
				.getBean(GenerateTableSqlHandler.class);
		handler.handler();
	}
}