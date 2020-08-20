package ankang.springmvc.learn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-18
 */
@Controller
// @RequestMapping：映射url使用那个方法来处理
@RequestMapping("/demo")
public class DemoController {

    /**
     * http://localhost:8080/demo/handle01
     */
    @RequestMapping("/handle01")
    public ModelAndView handle01() {
        final String datetime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T' , ' ');
        final Date date = new Date();

        // 模型视图

        // 模型信息
        // 封装了数据和页面信息
        final ModelAndView modelAndView = new ModelAndView();
        // addObject向请求域中添加信息与request.setAttribute("datetime", datetime)一样
        modelAndView.addObject("datetime", datetime);
        modelAndView.addObject("date", date);

        // 视图信息
        // 封装跳转的页面的信息
        // 物理视图名
        // modelAndView.setViewName("/jsp/success.jsp");
        // 逻辑视图名
        modelAndView.setViewName("success");

        return modelAndView;
    }

}
