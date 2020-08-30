package ankang.custom.demo.service.impl;

import ankang.custom.demo.service.DemoService;
import ankang.custom.springmvc.annotations.LgService;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-30
 */
@LgService
public class DemoServiceImpl implements DemoService {
    @Override
    public String get(String name) {
        System.out.println("service 实现类中的name参数：" + name);
        return name;
    }
}
