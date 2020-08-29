package ankang.custom.springmvc.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-29
 */
public class LgDispatcherServlet extends HttpServlet {

    /**
     * 接受处理请求
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
        doPost(req , resp);
    }

    /**
     * 处理请求
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req , HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req , resp);
    }

    /**
     * 加载配置文件
     *
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        final ServletConfig servletConfig = getServletConfig();

        // 1.加载配置文件：springmvc.properties
        final String contextConfigLocation = servletConfig.getInitParameter("contextConfigLocation");
        doLoadConfig(contextConfigLocation);

        // 2.扫描相关的类，扫描注解
        doScan();

        // 3.初始化相应Bean（实现ioc容器，基于注解）
        doInstance();

        // 4.实现依赖注入
        doAutowired();

        // 5.初始化SpringMVC相关组件：构造一个HandlerMapping处理器映射器，将配置好的url和Method建立映射关系
        initHandlerMapping();

        System.out.println("mvc 初始化完成。。。");

        // 6.等待请求进入，处理请求
    }

    /**
     * 5.初始化SpringMVC相关组件：构造一个HandlerMapping处理器映射器，将配置好的url和Method建立映射关系
     */
    private void initHandlerMapping() {
    }

    /**
     * 4.实现依赖注入
     */
    private void doAutowired() {
    }

    /**
     * 3.初始化相应Bean（实现ioc容器，基于注解）
     */
    private void doInstance() {
    }

    /**
     * 2.扫描相关的类，扫描注解
     */
    private void doScan() {
    }

    /**
     * 1.加载配置文件：springmvc.properties
     *
     * @param contextConfigLocation
     */
    private void doLoadConfig(String contextConfigLocation) {
    }
}
