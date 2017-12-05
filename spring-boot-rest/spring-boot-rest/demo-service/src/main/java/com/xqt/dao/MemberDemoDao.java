package com.xqt.dao;
import com.xqt.base.dao.BaseDao;
import com.xqt.entity.demo.MemberDemo;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDemoDao extends BaseDao<MemberDemo> {

	@Override
	public String getReadSql() {
		return "read.MemberDemo.";
	}

	@Override
	public String getWriteSql() {
		return "write.MemberDemo.";
	}
	
}