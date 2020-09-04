package ankang.custom.springmvc.servlet;

import ankang.custom.springmvc.annotations.LgAutowired;
import ankang.custom.springmvc.annotations.LgController;
import ankang.custom.springmvc.annotations.LgRequestMapping;
import ankang.custom.springmvc.annotations.LgService;
import ankang.custom.springmvc.handler.Handler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Pattern;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-29
 */
public class LgDispatcherServlet extends HttpServlet {

    /**
     * 缓存配置文件
     */
    private Properties properties = new Properties();

    /**
     * 缓存加载的全限定类名
     */
    private List<String> classNames = new ArrayList<>();

    /**
     * ioc容器
     */
    private Map<String, Object> ioc = new HashMap<>();

    /**
     * handler
     */
    private List<Handler> handlerMapping = new ArrayList<>();

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
        // 获取处理的handler
        final Handler handler = getHandler(req);

        if (handler == null) {
            resp.getWriter().write("404 not found");
        } else {
            try {
                handler.handle(req , resp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private Handler getHandler(HttpServletRequest req) {
        final String uri = req.getRequestURI();
        for (Handler handler : handlerMapping) {
            // url和handler匹配，则：返回
            if (handler.getPattern().matcher(uri).find()) {
                return handler;
            }
        }
        return null;
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
        doScan(properties.getProperty("scaPackage"));

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
        ioc.forEach((beanId , bean) -> {
            final Class<?> cls = bean.getClass();
            if (cls.isAnnotationPresent(LgController.class)) {
                // 获取class上@LgRequestMapping中的url
                final String baseUrl = cls.isAnnotationPresent(LgRequestMapping.class) ? cls.getDeclaredAnnotation(LgRequestMapping.class).value() : "";

                final Method[] methods = cls.getDeclaredMethods();
                for (Method method : methods) {
                    // 获取方法中的url，方法有@LgRequestMapping注解才处理
                    if (method.isAnnotationPresent(LgRequestMapping.class)) {
                        final String tailUrl = method.getDeclaredAnnotation(LgRequestMapping.class).value();

                        // 请求的完整url
                        final String url = baseUrl + tailUrl;
                        final Handler handler = new Handler(bean , method , Pattern.compile(url));
                        handlerMapping.add(handler);
                    }
                }
            }
        });
    }

    /**
     * 4.实现依赖注入
     */
    private void doAutowired() {
        // 遍历ioc中的所有对象，查看对象中的字段，是否有@LgAutowired注解，如果有需要维护依赖注入关系
        ioc.forEach((beanId , bean) -> {
            final Field[] fields = bean.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(LgAutowired.class)) {
                    final LgAutowired annotation = field.getAnnotation(LgAutowired.class);
                    // 获取beanId，如果value没有值，按照接口类型注入
                    final String autowiredBeanId = "".equals(annotation.value()) ? field.getType().getCanonicalName() : annotation.value();
                    try {
                        field.set(bean , ioc.get(autowiredBeanId));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    /**
     * 3.初始化相应Bean（实现ioc容器，基于注解）
     * 基于classNames缓存的类的全限定类名，以及反射技术，完成对象创建和管理
     */
    private void doInstance() {
        for (String className : classNames) {
            // 反射创建对象
            Class<?> cls = null;
            try {
                cls = Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // 区分Controller和Service
            if (cls.isAnnotationPresent(LgController.class)) {
                // controller的id此处不做过多处理，不取value了，直接使用类的首字母小写作为id，保存到ioc中
                final String simpleName = cls.getSimpleName();

                final String beanId = lowerFirst(simpleName);
                Object bean = null;
                try {
                    bean = cls.getConstructor().newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ioc.put(beanId , bean);
            } else if (cls.isAnnotationPresent(LgService.class)) {
                final LgService annotation = cls.getAnnotation(LgService.class);
                // 此处指定了id就使用id，没有指定就是用首字母小写
                final String beanId = "".equals(annotation.value()) ? lowerFirst(cls.getSimpleName()) : annotation.value();
                Object bean = null;
                try {
                    bean = cls.getConstructor().newInstance();
                    ioc.put(beanId , bean);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // service层往往是有接口的，面向接口开发，所以再以接口名为id放入一份对象到ioc中，便于后期根据接口类型注入
                final Class<?>[] interfaces = cls.getInterfaces();
                for (Class<?> inter : interfaces) {
                    final String interfaceName = inter.getCanonicalName();
                    ioc.put(interfaceName , bean);
                }
            }
        }
    }

    /**
     * 将字母小写
     *
     * @param s
     * @return
     */
    private String lowerFirst(String s) {
        final String first = s.substring(0 , 1).toLowerCase();
        final String tail = s.substring(1);
        return first + tail;
    }

    /**
     * 2.扫描相关的类，扫描注解
     */
    private void doScan(String pkg) {
        final String pkgPath = this.getClass().getClassLoader().getResource("").getPath() + pkg.replaceAll("\\." , "/");
        final File pkgDir = new File(pkgPath);

        final File[] files = pkgDir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                doScan(pkg + "." + file.getName());
            } else if (file.getName().endsWith(".class")) {
                final String className = pkg + "." + file.getName().replaceAll(".class" , "");
                classNames.add(className);
            }
        }
    }

    /**
     * 1.加载配置文件：springmvc.properties
     *
     * @param contextConfigLocation
     */
    private void doLoadConfig(String contextConfigLocation) {
        final InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
