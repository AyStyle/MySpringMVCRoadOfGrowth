package ankang.custom.springmvc.handler;

import ankang.custom.springmvc.annotations.Security;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-30
 */
public class Handler {

    /**
     * 控制器对象，也是调用method的对象
     */
    private Object controller;

    /**
     * 处理方法
     */
    private Method method;

    /**
     * spring中url是支持正则的
     */
    private Pattern pattern;

    /**
     * 存储参数顺序，进行参数绑定，key是参数名，value代表是第几个参数
     */
    private Map<String, Integer> paramIndexMap;

    public Handler(Object controller , Method method , Pattern pattern) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
        this.paramIndexMap = new HashMap<>();

        init();
    }

    public Object handle(HttpServletRequest req , HttpServletResponse resp) throws Exception {
        if (!authority(req)) {
            resp.getWriter().println("404, no auth");
            return null;
        }

        final Object[] args = new Object[paramIndexMap.size()];

        // 向参数数组中塞值，而且还得保证参数的顺序和方法中形参顺序一致
        final Map<String, String[]> parameterMap = req.getParameterMap();
        // 填充普通参数
        parameterMap.forEach((name , values) -> {
            // name=1&name=2 name [1,2]
            final String value = StringUtils.join(values , ",");

            // 如果参数和方法中的参数匹配上了，填充数据
            if (paramIndexMap.containsKey(name)) {
                final Integer index = paramIndexMap.get(name);
                if (index != null) {
                    args[index] = value;
                }
            }
        });

        Integer index = paramIndexMap.get(HttpServletRequest.class.getSimpleName());
        if (index != null) {
            args[index] = req;
        }

        index = paramIndexMap.get(HttpServletResponse.class.getSimpleName());
        if (index != null) {
            args[index] = resp;
        }

        // 调用handler方法
        return method.invoke(controller , args);
    }

    /**
     * 实现权限校验的功能
     *
     * @return 有权限返回true，没有权限返回false
     */
    private boolean authority(HttpServletRequest req) {
        // 没有@Security注解，则：有权限访问
        if (!method.isAnnotationPresent(Security.class) && !method.getDeclaringClass().isAnnotationPresent(Security.class)) {
            return true;
        }

        // 获取访问者
        final String[] usernames = req.getParameterValues("username");

        // usernames的个数只有一个时，才能进行权限校验，否则都是没有权限
        if (Objects.isNull(usernames) || usernames.length != 1) {
            return false;
        }

        // 获取有权限访问的人
        final Security methodAnnotation = method.getAnnotation(Security.class);
        final Security classAnnotation = method.getDeclaringClass().getAnnotation(Security.class);
        final List<String> authorities = Stream.of(methodAnnotation , classAnnotation).filter(Objects::nonNull).map(Security::value)
                .flatMap(Arrays::stream).filter(authority -> Objects.nonNull(authority) && !authority.isBlank())
                .collect(Collectors.toList());

        // 如果usernames在authorities里面，说明有权限访问
        return authorities.contains(usernames[0]);
    }


    private void init() {
        // 计算方法的参数位置
        final Parameter[] parameters = method.getParameters();
        for (int i = 0 ; i < parameters.length ; i++) {
            final Parameter parameter = parameters[i];

            // 如果参数对象是request和response，那么参数名称直接写HttpServletRequest和HttpServletResponse
            if (parameter.getType() == HttpServletRequest.class || parameter.getType() == HttpServletResponse.class) {
                paramIndexMap.put(parameter.getType().getSimpleName() , i);
            } else {
                paramIndexMap.put(parameter.getName() , i);
            }
        }
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public Map<String, Integer> getParamIndexMap() {
        return paramIndexMap;
    }

    public void setParamIndexMap(Map<String, Integer> paramIndexMap) {
        this.paramIndexMap = paramIndexMap;
    }
}
