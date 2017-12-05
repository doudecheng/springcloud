package com.xqt.controller;

import com.xqt.annotation.Auth;
import com.xqt.base.entity.Operator;
import com.xqt.common.PcsResult;
import com.xqt.entity.demo.DemoMenu;
import com.xqt.entity.sys.SysMember;
import com.xqt.enums.CommonEnum;
import com.xqt.enums.EnumPcsResultCode;
import com.xqt.inters.service.demo.ITbSysMemberService;
import com.xqt.param.sys.SysMemberParam;
import com.xqt.util.SessionLocalUtils;
import com.xqt.util.SessionManager;
import com.xqt.util.StringUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 登录demo
 *
 * @author andy
 * @create 2017-11-17 10:42
 **/
@RestController
@RequestMapping("/demo/user")
public class DemoLoginController extends BaseController {

    @Autowired
    private ITbSysMemberService sysMemberService;

    private Lock lock = new ReentrantLock();

    /**
     * 登录demo
     */
    @RequestMapping("/login")
    public void login(){
        JSONObject jo = getJsonObject();
        String username = jo.getString("username");
        String password = jo.getString("password");
        // 用户名与密码必输
        if (StringUtil.isEmpty(username)|| StringUtil.isEmpty(password)) {
            sendFailureMessage(response, "请输入用户名和密码", EnumPcsResultCode.LOGIN_FAIL);
            return;
        }
        SysMemberParam param = new SysMemberParam();
        param.setMem_name(username);
        SysMember member = sysMemberService.selectEntity(param);

        if(null == member){
            sendFailureMessage(response, "用户不存在", EnumPcsResultCode.LOGIN_FAIL);
            return;
        }
          //保存登录信息实体类
        Operator operator = new Operator();
        operator.setMember(member);
        // 保存登录信息
        String token = SessionManager.set(request, response, operator, CommonEnum.cookie_type.TYPE_01.getValue());
        // 存入本服务器缓存中，用于提高访问速度
        SessionLocalUtils.setOperator(operator);
        PcsResult pcsResult = new PcsResult(EnumPcsResultCode.SUCCESS,"登录成功");
        pcsResult.setToken(token);
        sendResultMessage(response,pcsResult);
    }

    /**
     * 拉取用户信息
     * 包含菜单
     * demo
     * Mock
     */
    @Auth()
    @RequestMapping("/info")
    public void info(){

        Map<String,Object> infoMap = new HashMap <>(3);
        SysMember member = SessionLocalUtils.getOperator().getMember();
        infoMap.put("name",member.getMem_name());
        infoMap.put("img",member.getMem_headimg_url());

        List<DemoMenu> list = new ArrayList <>();
        List<DemoMenu> childList = new ArrayList <>();

        DemoMenu menu = new DemoMenu();
        menu.setName("系统管理");
        menu.setPath("/quanxian");
        menu.setIcon("fa-bar-chart");
        DemoMenu childmenu1 = new DemoMenu();
        childmenu1.setName("角色管理");
        childmenu1.setPath("index");
        //childmenu1.setIcon("fa-bar-chart");
        //三级菜单
        List<DemoMenu> thirdMenuList = new ArrayList <>();
        DemoMenu thirdmenu1 = new DemoMenu();
        thirdmenu1.setName("分配角色");
        thirdmenu1.setPath("index");
        thirdmenu1.setIcon("fa-bar-chart");
        thirdMenuList.add(thirdmenu1);
        DemoMenu thirdmenu2 = new DemoMenu();
        thirdmenu2.setName("编辑角色");
        thirdmenu2.setPath("index");
        //thirdmenu2.setIcon("fa-bar-chart");
        thirdMenuList.add(thirdmenu2);

        childmenu1.setChildren(thirdMenuList);

        childList.add(childmenu1);
        DemoMenu childmenu2 = new DemoMenu();
        childmenu2.setName("用户管理");
        childmenu2.setPath("editor");
        //childmenu2.setIcon("fa-bar-chart");
        childList.add(childmenu2);
        menu.setChildren(childList);
        list.add(menu);

        infoMap.put("role",list);

        sendSuccessMessage(response,"拉取用户信息",infoMap);
    }


    /**
     * 登出
     * demo
     */
    @Auth()
    @RequestMapping("/loginout")
    public void loginout(){

        // 清除redis存储的用户数据
        String sessionId = SessionManager.getSessionIdByToken(request);
        SessionManager.del(sessionId);

        // 清除cookie
        Cookie cookie = new Cookie(SessionManager.COOKIE_KEY+ CommonEnum.cookie_type.TYPE_01.getValue(), "");
        response.addCookie(cookie);

        // 过期回话
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }

        sendSuccessMessage(response,"登出成功");

    }

}
