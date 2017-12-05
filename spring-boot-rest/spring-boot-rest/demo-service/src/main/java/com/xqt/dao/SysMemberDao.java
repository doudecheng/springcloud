package com.xqt.dao;

import com.xqt.base.dao.BaseDao;
import com.xqt.entity.sys.SysMember;
import org.springframework.stereotype.Repository;

/**
 * @author 123
 */

@Repository
public class SysMemberDao extends BaseDao<SysMember> {

	@Override
	public String getReadSql() {
		return "read.TbSysMember.";
	}

	@Override
	public String getWriteSql() {
		return "write.TbSysMember.";
	}

}