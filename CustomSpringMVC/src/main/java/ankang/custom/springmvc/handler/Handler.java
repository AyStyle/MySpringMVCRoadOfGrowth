package ankang.custom.springmvc.handler;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

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

    public Object handle(HttpServletRequest req , HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException {
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
       return method.invoke(controller , args);
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
