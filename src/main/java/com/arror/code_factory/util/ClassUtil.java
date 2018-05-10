package com.arror.code_factory.util;

/**
 * @author zhangxianbin
 */
public class ClassUtil {
	/**
	 * 将字符串转换为驼峰命名
	 * 
	 * @param target
	 * @return
	 */
	public static String translateHump(String target) {
		if (target == null || target == "") {
			return "";
		}

		String[] targets = target.split("_");
		StringBuilder sb = new StringBuilder();
		if (targets.length != 0) {
			sb.append(targets[0]);
		}

		for (int i = 1; i < targets.length; i++) {
			String temp = targets[i];
			sb.append(temp.substring(0, 1).toUpperCase()).append(
					temp.substring(1).toLowerCase());
		}
		return sb.toString();

	}

	/**
	 * 将字符串转换为首字母大写的驼峰命名
	 * 
	 * @param target
	 * @return
	 */
	public static String translateFirstUp(String target) {
		if (target == null || target == "") {
			return "";
		}

		String[] targets = target.split("_");
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < targets.length; i++) {
			String temp = targets[i];
			sb.append(temp.substring(0, 1).toUpperCase()).append(
					temp.substring(1).toLowerCase());
		}
		return sb.toString();
	}

	public static String classNote(String tableDesc) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n/**").append("\n").append(" *").append("\n").append(" *")
				.append("       ").append(tableDesc).append("\n").append(" *")
				.append("\n").append("*/").append("\n");
		return sb.toString();
	}

	// 方法的注释
	public static String methondNote() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n	/**").append("\n").append("	 *").append("\n");
		return sb.toString();
	}
}