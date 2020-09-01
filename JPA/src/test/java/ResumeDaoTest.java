import ankang.jpa.dao.ResumeDao;
import ankang.jpa.pojo.Resume;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

/**
 * @author: ankang
 * @email: dreedisgood@qq.com
 * @create: 2020-09-02
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class ResumeDaoTest {

    /**
     * 注入测试的dao
     */
    @Autowired
    private ResumeDao resumeDao;

    /*
     * dao层接口调用：
     *     1.基础的增删改查
     *     2.专门针对查询的详细分析使用
     */

    /**
     * 根据id查询
     */
    @Test
    public void testFindById(){
        final Optional<Resume> byId = resumeDao.findById(1);
        System.out.println(byId.get());
    }

}
