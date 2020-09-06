package ankang.ssm.controller;

import ankang.ssm.pojo.Account;
import ankang.ssm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-31
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    /*
     * Spring和SpringMVC是有层次的（父子容器）
     * Spring容器管理：service和dao对象
     * SpringMVC容器管理：controller对象，且SpringMVC容器可以引用Spring容器中的对象
     */

    @Autowired
    private AccountService accountService;

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Account> queryAll() {
        return accountService.queryAll();
    }

}
