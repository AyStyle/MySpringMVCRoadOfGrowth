package ankang.ssm.mapper;

import ankang.ssm.pojo.Account;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-30
 */
public interface AccountMapper {

    @Select("SELECT * FROM db_spring.account")
    List<Account> selectAll();

}
