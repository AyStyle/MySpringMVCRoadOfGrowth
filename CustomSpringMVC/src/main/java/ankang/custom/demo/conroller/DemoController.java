package ankang.custom.demo.conroller;

import ankang.custom.demo.service.DemoService;
import ankang.custom.springmvc.annotations.LgAutowired;
import ankang.custom.springmvc.annotations.LgController;
import ankang.custom.springmvc.annotations.LgRequestMapping;
import ankang.custom.springmvc.annotations.Security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-30
 */
@Security("root")
@LgController
@LgRequestMapping("/demo")
public class DemoController {

    @LgAutowired
    private DemoService demoService;

    @LgRequestMapping("/query")
    public String query(HttpServletRequest request , HttpServletResponse response , String name) {
        return demoService.get(name);
    }

    @Security("ankang")
    @LgRequestMapping("/ankang")
    public String queryWithAnkang(HttpServletRequest request , HttpServletResponse response , String name) {
        return demoService.get(name);
    }

    @Security({"ankang" , "wangdong"})
    @LgRequestMapping("/wangdong")
    public String queryWithAnkangAndWangdong(HttpServletRequest request , HttpServletResponse response , String name) {
        return demoService.get(name);
    }

}
