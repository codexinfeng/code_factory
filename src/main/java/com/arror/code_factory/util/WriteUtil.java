package com.arror.code_factory.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.arror.code_factory.enums.ClassTypeEnmu;

/**
 * @author zhangxianbin
 */
public class WriteUtil {
	// Ð´ÎÄ¼þ
	public static void write(String baseFile, String className, String content,
			ClassTypeEnmu enums) {
		String fileName = "";
		if (enums == ClassTypeEnmu.POJO) {
			fileName = baseFile + "model";
			className = className + "DO.java";
		}
		if (enums == ClassTypeEnmu.DAO) {
			fileName = baseFile + "dao";
			className = className + "DAO.java";
		}
		if (enums == ClassTypeEnmu.XML) {
			fileName = baseFile + "dao";
			className = className + "DAO.xml";
		}
		if (enums == ClassTypeEnmu.SERVICE) {
			fileName = baseFile + "service";
			className = className + "Service.java";
		}
		if (enums == ClassTypeEnmu.SERVICEIMPL) {
			fileName = baseFile + "service" + File.separator + "impl";
			className = className + "ServiceImpl.java";
		}
		File modelFile = new File(fileName, className);
		if (!modelFile.getParentFile().exists()) {
			modelFile.getParentFile().mkdirs();
		}
		try {
			modelFile.createNewFile();
		} catch (IOException e) {
		}

		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(modelFile));
			writer.write(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
