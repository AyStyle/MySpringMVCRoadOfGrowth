package ankang.custom.demo.conroller;

import ankang.custom.demo.service.DemoService;
import ankang.custom.springmvc.annotations.LgAutowired;
import ankang.custom.springmvc.annotations.LgController;
import ankang.custom.springmvc.annotations.LgRequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-30
 */
@LgController
@LgRequestMapping("/demo")
public class DemoController {

    @LgAutowired
    private DemoService demoService;

    private String query(HttpServletRequest request , HttpServletResponse response , String name) {
        return demoService.get(name);
    }

}
