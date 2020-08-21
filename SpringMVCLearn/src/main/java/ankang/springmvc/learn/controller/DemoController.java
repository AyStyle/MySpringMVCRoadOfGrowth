package ankang.springmvc.learn.controller;

import ankang.springmvc.learn.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

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
        modelAndView.addObject("datetime" , datetime);
        modelAndView.addObject("date" , date);

        // 视图信息
        // 封装跳转的页面的信息
        // 物理视图名
        // modelAndView.setViewName("/jsp/success.jsp");
        // 逻辑视图名
        modelAndView.setViewName("success");

        System.out.println("=========>modelMap: " + modelAndView + ", class: " + modelAndView.getClass());

        return modelAndView;
    }

    /**
     * 测试用例：SpringMVC 对原生servlet api的支持
     * 如果要在SpringMVC中使用servlet原生对象，比如：HttpServletRequest、HttpServletResponse和HttpSession，直接在Handler方法形参中声明使用即可
     * http://localhost:8080/demo/handle02?id=1
     */
    @RequestMapping("/handle02")
    public ModelAndView handle02(HttpServletRequest request , HttpServletResponse response , HttpSession session) {
        final String id = request.getParameter("id");
        final Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(id);
        parameterMap.forEach((String k , String[] v) -> {
            System.out.println(k);
            System.out.println(Arrays.toString(v));
        });


        final String datetime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T' , ' ');
        final Date date = new Date();

        // 模型视图

        // 模型信息
        // 封装了数据和页面信息
        final ModelAndView modelAndView = new ModelAndView();
        // addObject向请求域中添加信息与request.setAttribute("datetime", datetime)一样
        modelAndView.addObject("datetime" , datetime);
        modelAndView.addObject("date" , date);

        // 视图信息
        // 封装跳转的页面的信息
        // 物理视图名
        // modelAndView.setViewName("/jsp/success.jsp");
        // 逻辑视图名
        modelAndView.setViewName("success");

        System.out.println("=========>modelMap: " + modelAndView + ", class: " + modelAndView.getClass());

        return modelAndView;
    }


    /**
     * 测试用例：SpringMVC 接收简单数据类型参数
     * 测试用例：SpringMVC 使用@RequestParam 接收简单数据类型参数(形参名和参数名不一致)
     * 接受简单数据类型参数，直接在handler方法的形参中声明即可，框架会自动取出参数值然后绑定到对应的参数上
     * 要求：传递的参数名和声明的形参名保持一致，如果参数名与声明的形参名不一致，那么使用@Requestparam注解来转换一下
     * http://localhost:8080/demo/handle03?id=1
     */
    @RequestMapping("/handle03")
    public ModelAndView handle03(@RequestParam("id") Integer id) {
        System.out.println(id);

        final String datetime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T' , ' ');
        final Date date = new Date();

        // 模型视图

        // 模型信息
        // 封装了数据和页面信息
        final ModelAndView modelAndView = new ModelAndView();
        // addObject向请求域中添加信息与request.setAttribute("datetime", datetime)一样
        modelAndView.addObject("datetime" , datetime);
        modelAndView.addObject("date" , date);

        // 视图信息
        // 封装跳转的页面的信息
        // 物理视图名
        // modelAndView.setViewName("/jsp/success.jsp");
        // 逻辑视图名
        modelAndView.setViewName("success");

        System.out.println("=========>modelMap: " + modelAndView + ", class: " + modelAndView.getClass());

        return modelAndView;
    }

    /**
     * 测试用例：SpringMVC接收pojo类型参数
     * 接受pojo类型参数，直接形参声明即可，类型就是Pojo的类型，形参名称无所谓，
     * 但是要求传递的参数名必须和Pojo的属性名保持一致
     * http://localhost:8080/demo/handle04?name=ankang&age=18
     */
    @RequestMapping("/handle04")
    public ModelAndView handle04(User user) {
        System.out.println(user);

        final String datetime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T' , ' ');
        final Date date = new Date();

        // 模型视图

        // 模型信息
        // 封装了数据和页面信息
        final ModelAndView modelAndView = new ModelAndView();
        // addObject向请求域中添加信息与request.setAttribute("datetime", datetime)一样
        modelAndView.addObject("datetime" , datetime);
        modelAndView.addObject("date" , date);

        // 视图信息
        // 封装跳转的页面的信息
        // 物理视图名
        // modelAndView.setViewName("/jsp/success.jsp");
        // 逻辑视图名
        modelAndView.setViewName("success");

        System.out.println("=========>modelMap: " + modelAndView + ", class: " + modelAndView.getClass());

        return modelAndView;
    }

    /**
     * 测试用例：SpringMVC接收pojo包装类型参数
     * 无论是否为包装对象，只要是一个Pojo，那么就可以按照Pojo的要求来传值：
     * 1. 绑定参数时，直接形参声明即可
     * 2. 传参参数名和Pojo属性保持一致，如果不能够定位数据项，那么通过属性名 + "."的方式进一步锁定数据
     * http://localhost:8080/demo/handle05?innerUser.name=ankang&innerUser.age=18
     */
    @RequestMapping("/handle05")
    public ModelAndView handle05(User user) {
        System.out.println(user);

        final String datetime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T' , ' ');
        final Date date = new Date();

        // 模型视图

        // 模型信息
        // 封装了数据和页面信息
        final ModelAndView modelAndView = new ModelAndView();
        // addObject向请求域中添加信息与request.setAttribute("datetime", datetime)一样
        modelAndView.addObject("datetime" , datetime);
        modelAndView.addObject("date" , date);

        // 视图信息
        // 封装跳转的页面的信息
        // 物理视图名
        // modelAndView.setViewName("/jsp/success.jsp");
        // 逻辑视图名
        modelAndView.setViewName("success");

        System.out.println("=========>modelMap: " + modelAndView + ", class: " + modelAndView.getClass());

        return modelAndView;
    }

    /**
     * 测试用例：SpringMVC接收日期类型参数
     * 定义一个SpringMVC的类型转换器：
     * 转换器就是一个接口: {@link org.springframework.core.convert.converter.Converter}
     * http://localhost:8080/demo/handle06?birthday=2020-10-01
     */
    @RequestMapping("/handle06")
    public ModelAndView handle06(Date birthday) {
        System.out.println(birthday);

        final String datetime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T' , ' ');
        final Date date = new Date();

        // 模型视图

        // 模型信息
        // 封装了数据和页面信息
        final ModelAndView modelAndView = new ModelAndView();
        // addObject向请求域中添加信息与request.setAttribute("datetime", datetime)一样
        modelAndView.addObject("datetime" , datetime);
        modelAndView.addObject("date" , date);

        // 视图信息
        // 封装跳转的页面的信息
        // 物理视图名
        // modelAndView.setViewName("/jsp/success.jsp");
        // 逻辑视图名
        modelAndView.setViewName("success");

        System.out.println("=========>modelMap: " + modelAndView + ", class: " + modelAndView.getClass());

        return modelAndView;
    }

    /**
     * 测试用例：SpringMVC对Restful风格url的支持
     * GET：获取资源
     *
     * @PathVariable：解析RESTful风格url中的参数 http://localhost:8080/demo/handle/{id}
     */
    @RequestMapping(value = "/handle/{id}", method = RequestMethod.GET)
    public ModelAndView handleGet(@PathVariable("id") Integer id) {
        System.out.println(id)
        ;
        final String datetime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T' , ' ');
        final Date date = new Date();

        // 模型视图

        // 模型信息
        // 封装了数据和页面信息
        final ModelAndView modelAndView = new ModelAndView();
        // addObject向请求域中添加信息与request.setAttribute("datetime", datetime)一样
        modelAndView.addObject("datetime" , datetime);
        modelAndView.addObject("date" , date);

        // 视图信息
        // 封装跳转的页面的信息
        // 物理视图名
        // modelAndView.setViewName("/jsp/success.jsp");
        // 逻辑视图名
        modelAndView.setViewName("success");

        System.out.println("=========>modelMap: " + modelAndView + ", class: " + modelAndView.getClass());

        return modelAndView;
    }

    /**
     * 测试用例：SpringMVC对Restful风格url的支持
     * POST：添加资源
     * http://localhost:8080/demo/handle
     */
    @RequestMapping(value = "/handle", method = RequestMethod.POST)
    public ModelAndView handlePost(@RequestParam("username") String username) {
        System.out.println(username)
        ;
        final String datetime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T' , ' ');
        final Date date = new Date();

        // 模型视图

        // 模型信息
        // 封装了数据和页面信息
        final ModelAndView modelAndView = new ModelAndView();
        // addObject向请求域中添加信息与request.setAttribute("datetime", datetime)一样
        modelAndView.addObject("datetime" , datetime);
        modelAndView.addObject("date" , date);

        // 视图信息
        // 封装跳转的页面的信息
        // 物理视图名
        // modelAndView.setViewName("/jsp/success.jsp");
        // 逻辑视图名
        modelAndView.setViewName("success");

        System.out.println("=========>modelMap: " + modelAndView + ", class: " + modelAndView.getClass());

        return modelAndView;
    }

    /**
     * 测试用例：SpringMVC对Restful风格url的支持
     * PUT：更新资源
     * http://localhost:8080/demo/handle/{id}/{name}
     */
    @RequestMapping(value = "/handle/{id}/{name}", method = RequestMethod.PUT)
    public ModelAndView handlePut(@PathVariable("id") Integer id , @PathVariable("name") String name) {
        System.out.println(id);
        System.out.println(name);
        ;
        final String datetime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T' , ' ');
        final Date date = new Date();

        // 模型视图

        // 模型信息
        // 封装了数据和页面信息
        final ModelAndView modelAndView = new ModelAndView();
        // addObject向请求域中添加信息与request.setAttribute("datetime", datetime)一样
        modelAndView.addObject("datetime" , datetime);
        modelAndView.addObject("date" , date);

        // 视图信息
        // 封装跳转的页面的信息
        // 物理视图名
        // modelAndView.setViewName("/jsp/success.jsp");
        // 逻辑视图名
        modelAndView.setViewName("success");

        System.out.println("=========>modelMap: " + modelAndView + ", class: " + modelAndView.getClass());

        return modelAndView;
    }

    /**
     * 测试用例：SpringMVC对Restful风格url的支持
     * DELETE：删除资源
     * http://localhost:8080/demo/handle/{id}
     */
    @RequestMapping(value = "/handle/{id}", method = RequestMethod.DELETE)
    public ModelAndView handleDelete(@PathVariable("id") Integer id) {
        System.out.println("this is delete method");
        System.out.println(id);
        ;
        final String datetime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T' , ' ');
        final Date date = new Date();

        // 模型视图

        // 模型信息
        // 封装了数据和页面信息
        final ModelAndView modelAndView = new ModelAndView();
        // addObject向请求域中添加信息与request.setAttribute("datetime", datetime)一样
        modelAndView.addObject("datetime" , datetime);
        modelAndView.addObject("date" , date);

        // 视图信息
        // 封装跳转的页面的信息
        // 物理视图名
        // modelAndView.setViewName("/jsp/success.jsp");
        // 逻辑视图名
        modelAndView.setViewName("success");

        System.out.println("=========>modelMap: " + modelAndView + ", class: " + modelAndView.getClass());

        return modelAndView;
    }

    /*
     * SpringMVC在handle方法上传入参数Map、Model和ModelMap，并向参数中保存数据（放入到请求域中），那么都可以在页面中展示
     * Map、Model和ModelMap的关系：
     *     运行时都是org.springframework.validation.support.BindingAwareModelMap，相当于在BindingAwareModelMap中保存的数据都会在请求域中
     *         Map：JDK中的接口
     *         Model：Spring的接口
     *         ModelMap：Spring的class对象，继承了LinkedHashMap，实现了Map接口
     *         BindingAwareModelMap：Spring中的class，继承了ExtendedModelMap，而ExtendedModelMap继承了ModelMap并实现了Model接口
     * */

    /**
     * 使用参数ModelMap封装数据，返回视图名字符串
     * http://localhost:8080/demo/handle11
     */
    @RequestMapping("/handle11")
    public String handle11(ModelMap modelMap) {
        final String datetime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T' , ' ');
        final Date date = new Date();

        // 使用参数ModelMap封装结果集对象
        modelMap.addAttribute("datetime" , datetime);
        modelMap.addAttribute("date" , date);

        System.out.println("=========>modelMap: " + modelMap + ", class: " + modelMap.getClass());

        // 直接返回视图名
        return "success";
    }

    /**
     * 使用参数Model封装数据，返回视图名字符串
     * http://localhost:8080/demo/handle12
     */
    @RequestMapping("/handle12")
    public String handle12(Model model) {
        final String datetime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T' , ' ');
        final Date date = new Date();

        // 使用参数ModelMap封装结果集对象
        model.addAttribute("datetime" , datetime);
        model.addAttribute("date" , date);

        System.out.println("=========>modelMap: " + model + ", class: " + model.getClass());

        // 直接返回视图名
        return "success";
    }

    /**
     * 使用参数Map封装数据，返回视图名字符串
     * http://localhost:8080/demo/handle13
     */
    @RequestMapping("/handle13")
    public String handle13(Map map) {
        final String datetime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME).replace('T' , ' ');
        final Date date = new Date();

        // 使用参数ModelMap封装结果集对象
        map.put("datetime" , datetime);
        map.put("date" , date);

        System.out.println("=========>modelMap: " + map + ", class: " + map.getClass());

        // 直接返回视图名
        return "success";
    }


}
