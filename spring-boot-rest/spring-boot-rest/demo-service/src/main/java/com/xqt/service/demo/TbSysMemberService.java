package com.xqt.service.demo;

import com.xqt.base.dao.BaseDao;
import com.xqt.base.page.PageInfo;
import com.xqt.dao.SysMemberDao;
import com.xqt.entity.sys.SysMember;
import com.xqt.inters.service.demo.ITbSysMemberService;
import com.xqt.param.sys.SysMemberParam;
import com.xqt.service.BaseService;
import com.xqt.vo.SysmemberVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 123
 */
public class TbSysMemberService extends BaseService<SysMember> implements ITbSysMemberService {

	@Autowired
	private SysMemberDao sysMemberDao;

	
	@Override
	public BaseDao<SysMember> getDao() {
		return sysMemberDao;
	}

	@Override
	public PageInfo<SysmemberVo> selectPageVoList(SysMemberParam param) {
		return sysMemberDao.selectPageInfoCustom("selectEntityCount","selectPageVoList",param);
	}
}
