package com.arror.code_factory.service;

import java.util.List;

import com.arror.code_factory.model.AreaCityDO;

/**
 *
 * 城市基础数据表
 *
 */
public interface AreaCityService {

	/**
	 *
	 * 根据id获取对象AreaCityDO
	 * 
	 * @param id
	 */
	public AreaCityDO getById(Integer id);

	/**
	 *
	 * 根据id集合获取对象listAreaCityDO
	 * 
	 * @param list
	 */
	public List<AreaCityDO> listByIds(List<Integer> ids);

	/**
	 *
	 * 保存AreaCityDO
	 * 
	 * @param areaCityDo
	 */
	public void save(AreaCityDO areaCityDo);

	/**
	 *
	 * 保存多个AreaCityDO
	 * 
	 * @param list
	 */
	public void saveList(List<AreaCityDO> list);

}