package com.arror.code_factory.service;

import java.util.List;

import com.arror.code_factory.model.AreaCityDO;

/**
 *
 * ���л������ݱ�
 *
 */
public interface AreaCityService {

	/**
	 *
	 * ����id��ȡ����AreaCityDO
	 * 
	 * @param id
	 */
	public AreaCityDO getById(Integer id);

	/**
	 *
	 * ����id���ϻ�ȡ����listAreaCityDO
	 * 
	 * @param list
	 */
	public List<AreaCityDO> listByIds(List<Integer> ids);

	/**
	 *
	 * ����AreaCityDO
	 * 
	 * @param areaCityDo
	 */
	public void save(AreaCityDO areaCityDo);

	/**
	 *
	 * ������AreaCityDO
	 * 
	 * @param list
	 */
	public void saveList(List<AreaCityDO> list);

}