package com.xqt.controller;

import com.xqt.base.page.PageInfo;
import com.xqt.entity.sys.SysMember;
import com.xqt.inters.service.demo.ITbSysMemberService;
import com.xqt.param.sys.SysMemberParam;
import com.xqt.vo.SysmemberVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 用户控制器
 *
 * @author andy
 * @create 2017-11-17 15:25
 **/

@RestController
@RequestMapping("/demo/member")
public class DemoSysMemberController extends BaseController {

    @Autowired
    private ITbSysMemberService sysMemberService;

    @RequestMapping("/pageInfo")
    public void getPageInfo(){
        SysMemberParam param = (SysMemberParam)getJsonParams(SysMemberParam.class);
        param.setOrderField("a.create_date DESC");
        PageInfo<SysmemberVo> pageInfo = sysMemberService.selectPageVoList(param);

        sendListData(response,pageInfo.getRowCount(),pageInfo.getPageCount(),pageInfo.getData());
    }

    @RequestMapping("/add")
    public void add(){
        SysMember entity = (SysMember)getJsonParams(SysMember.class);
        entity.setCreate_date(new Date());
        sysMemberService.insertEntity(entity);

        sendSuccessMessage(response,"插入数据成功");

    }


    @RequestMapping("/update")
    public void update(){
        SysMember entity = (SysMember)getJsonParams(SysMember.class);

        sysMemberService.updateEntity(entity);

        sendSuccessMessage(response,"修改数据成功");
    }

    @RequestMapping("/remove")
    public void remove(){
        JSONObject jo = getJsonObject();
        String id = jo.getString("id");
        SysMember entity = new SysMember();
        entity.setId(id);
        sysMemberService.deleteEntity(entity);

        sendSuccessMessage(response,"删除数据成功");

    }

}
