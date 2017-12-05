package com.xqt.service.demo;

import com.xqt.base.dao.BaseDao;
import com.xqt.dao.MemberDemoDao;
import com.xqt.entity.demo.MemberDemo;
import com.xqt.inters.service.demo.IMemberDemoService;
import com.xqt.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberDemoService extends BaseService<MemberDemo> implements IMemberDemoService {

	@Autowired
	private MemberDemoDao memberDemoDao;

	
	@Override
	public BaseDao<MemberDemo> getDao() {
		return memberDemoDao;
	}


}
