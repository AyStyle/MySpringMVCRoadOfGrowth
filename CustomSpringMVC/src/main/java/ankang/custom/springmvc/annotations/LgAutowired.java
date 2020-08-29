package ankang.custom.springmvc.annotations;

import java.lang.annotation.*;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-29
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LgAutowired {
    String value() default "";
}