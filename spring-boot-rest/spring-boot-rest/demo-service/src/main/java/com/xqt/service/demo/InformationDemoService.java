package com.xqt.service.demo;

import com.xqt.base.dao.BaseDao;
import com.xqt.dao.InformationDemoDao;
import com.xqt.entity.demo.InformationDemo;
import com.xqt.inters.service.demo.IInformationDemoService;
import com.xqt.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public class InformationDemoService extends BaseService<InformationDemo> implements IInformationDemoService {

	@Autowired
	private InformationDemoDao informationDemoDao;

	
	@Override
	public BaseDao<InformationDemo> getDao() {
		return informationDemoDao;
	}


}
