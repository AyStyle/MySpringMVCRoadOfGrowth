package ankang.custom.springmvc.annotations;

import java.lang.annotation.*;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-09-05
 */
@Documented
@Target({ElementType.METHOD , ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Security {

    String[] value() default "";

}
