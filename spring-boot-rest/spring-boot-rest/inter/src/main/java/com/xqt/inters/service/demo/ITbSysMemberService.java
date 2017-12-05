package com.xqt.inters.service.demo;

import com.xqt.base.page.PageInfo;
import com.xqt.entity.sys.SysMember;
import com.xqt.inters.service.base.InterService;
import com.xqt.param.sys.SysMemberParam;
import com.xqt.vo.SysmemberVo;

/**
 * @author 123
 */
public interface ITbSysMemberService  extends InterService<SysMember> {

    PageInfo<SysmemberVo> selectPageVoList(SysMemberParam param);
}
