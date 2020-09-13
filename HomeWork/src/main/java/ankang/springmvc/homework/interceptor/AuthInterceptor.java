package ankang.springmvc.homework.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-09-06
 */
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request , HttpServletResponse response , Object handler) throws Exception {
        final String requestURI = request.getRequestURI();


        // 访问登录页面
        if ("/resume/login".equals(requestURI) || "/resume/unLogin".equals(requestURI)) {
            return true;
        }

        final HttpSession session = request.getSession();
        final Boolean isAuth = (Boolean) session.getAttribute("isAuth");

        // 认证通过
        if (isAuth != null && isAuth) {
            return true;
        }

        request.getRequestDispatcher("/resume/unLogin").forward(request , response);
        return false;
    }

}
