package com.xqt.dao;
import com.xqt.base.dao.BaseDao;
import com.xqt.entity.demo.InformationDemo;
import org.springframework.stereotype.Repository;

@Repository
public class InformationDemoDao extends BaseDao<InformationDemo> {

	@Override
	public String getReadSql() {
		return "read.InformationDemo.";
	}

	@Override
	public String getWriteSql() {
		return "write.InformationDemo.";
	}
	
}