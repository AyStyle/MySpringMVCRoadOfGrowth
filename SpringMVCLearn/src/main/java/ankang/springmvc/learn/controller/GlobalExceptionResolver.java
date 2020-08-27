package ankang.springmvc.learn.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常处理器
 *
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-28
 */
@ControllerAdvice
public class GlobalExceptionResolver {

    /**
     * SpringMVC异常处理机制（异常处理器）
     * 注意：写在这里只会对当前类生效
     */
    @ExceptionHandler({ArithmeticException.class})
    public void handleException(Exception exception , HttpServletResponse response) {
        // 异常处理逻辑
        try {
            response.getWriter().write("=====>" + exception.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 可以返回其他页面也可以不返回
    }

}
