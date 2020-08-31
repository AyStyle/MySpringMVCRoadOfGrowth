package ankang.ssm.service.impl;

import ankang.ssm.mapper.AccountMapper;
import ankang.ssm.pojo.Account;
import ankang.ssm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-30
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> queryAll() {
        return accountMapper.selectAll();
    }
}
