package com.arror.code_factory.util;

/**
 * @author zhangxianbin
 */
public class ClassUtil {
	/**
	 * ���ַ���ת��Ϊ�շ�����
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
	 * ���ַ���ת��Ϊ����ĸ��д���շ�����
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

	// ������ע��
	public static String methondNote() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n	/**").append("\n").append("	 *").append("\n");
		return sb.toString();
	}
}
