package com.arror.code_factory.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.arror.code_factory.dao.AreaCityDAO;
import com.arror.code_factory.model.AreaCityDO;

/**
 *
 * ���л������ݱ�
 *
 */
@Service
public class AreaCityServiceImpl implements AreaCityService {

	@Resource
	private AreaCityDAO areaCityDao;

	/**
	 *
	 * ����id��ȡ����AreaCityDO
	 */
	@Override
	public AreaCityDO getById(Integer id) {

		return areaCityDao.getById(id);

	}

	/**
	 *
	 * ����id���ϻ�ȡ����listAreaCityDO
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
	 * ����AreaCityDO
	 */
	@Override
	public void save(AreaCityDO AreaCityDo) {

		areaCityDao.save(AreaCityDo);

	}

	/**
	 *
	 * ������AreaCityDO
	 */
	@Override
	public void saveList(List<AreaCityDO> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		areaCityDao.saveList(list);
	}
}