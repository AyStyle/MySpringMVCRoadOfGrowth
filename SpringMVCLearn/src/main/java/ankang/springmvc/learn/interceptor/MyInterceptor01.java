package ankang.springmvc.learn.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义SpringMVC拦截器
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-26
 */
public class MyInterceptor01 implements HandlerInterceptor {

    /**
     * 该方法会在handler业务逻辑执行之前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request , HttpServletResponse response , Object handler) throws Exception {
        return false;
    }

    /**
     *该方法会在handler业务逻辑执行之后，但跳转页面之前执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request , HttpServletResponse response , Object handler , ModelAndView modelAndView) throws Exception {

    }

    /**
     *该方法会在handler业务逻辑执行之后，并且跳转完了页面之后执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request , HttpServletResponse response , Object handler , Exception ex) throws Exception {

    }
}
