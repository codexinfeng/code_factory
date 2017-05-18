package com.arror.code_factory.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arror.code_factory.dao.AreaCityDAO;
import com.arror.code_factory.model.AreaCityDO;

/**
 *
 * 城市基础数据表
 *
 */
@Service
public class AreaCityServiceImpl implements AreaCityService {

	@Resource
	private AreaCityDAO areaCityDao;

	/**
	 *
	 * 根据id获取对象AreaCityDO
	 */
	@Override
	public AreaCityDO getById(Integer id) {

		return areaCityDao.getById(id);

	}

	/**
	 *
	 * 根据id集合获取对象listAreaCityDO
	 */
	@Override
	public List<AreaCityDO> listByIds(List<Integer> ids) {
		if (ids == null || ids.isEmpty()) {
			return new ArrayList<>();
		}
		return areaCityDao.listByIds(ids);
	}

	/**
	 *
	 * 保存AreaCityDO
	 */
	@Override
	public void save(AreaCityDO AreaCityDo) {

		areaCityDao.save(AreaCityDo);

	}

	/**
	 *
	 * 保存多个AreaCityDO
	 */
	@Override
	public void saveList(List<AreaCityDO> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		areaCityDao.saveList(list);
	}
}