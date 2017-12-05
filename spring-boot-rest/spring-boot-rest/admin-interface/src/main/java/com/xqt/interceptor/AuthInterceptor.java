package com.xqt.interceptor;

import com.xqt.annotation.Auth;
import com.xqt.base.entity.Operator;
import com.xqt.enums.CommonEnum;
import com.xqt.util.SessionLocalUtils;
import com.xqt.util.SessionManager;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {

        // 仅拦截对方法的访问，对于其他静态资源不进行拦截
        if (handler instanceof HandlerMethod) {

            HandlerMethod method = (HandlerMethod) handler;
            Auth auth = method.getMethod().getAnnotation(Auth.class);

            // 如果访问方法需要登录，则进行拦截
            if (auth != null && auth.verifyLogin()) {

                // 获取redis存储的用户数据
                Operator operator = (Operator) SessionManager.getOperator(request, CommonEnum.cookie_type.TYPE_01.getValue());

                // 如果存储的登录信息已经丢失，则表示已经登录过期。跳转到登录页面
                if (operator == null) {
                    String basePath = request.getScheme() + "://"
                            + request.getServerName() + ":"
                            + request.getServerPort()
                            + request.getContextPath();
                    response.sendRedirect(basePath + "/login");
                    return false;
                }

                // 验证菜单权限，防止越权访问
//                List<String> menuList = operator.getMember().getPermissionsList();
//                if (menuList==null || (!StringUtil.isEmpty(auth.permissions()) && !menuList.contains(auth.permissions()))) {
//                    HtmlUtil.writerJson(response,"you do not have permission to access");
//                    return false;
//                }

                // 存入本服务器缓存中，用于提高访问速度
                SessionLocalUtils.setOperator(operator);
                // 重新设置session失效时间
                SessionManager.resetExpire(request, CommonEnum.cookie_type.TYPE_01.getValue());
            }
        }

        return super.preHandle(request, response, handler);
    }
}
