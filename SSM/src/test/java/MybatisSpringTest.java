import ankang.ssm.pojo.Account;
import ankang.ssm.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-08-30
 */
// @ExtendWith修改JunitEngine，使用SpringExtension
// @ContextConfiguration配置Spring启动文件
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class MybatisSpringTest {

    @Autowired
    private AccountService accountService;

    @Test

    public void testMybatisSpring() {
        accountService.queryAll().forEach(System.out::println);
    }
}
